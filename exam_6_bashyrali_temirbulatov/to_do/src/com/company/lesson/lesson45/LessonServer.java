package com.company.lesson.lesson45;

import com.company.lesson.server.RouteHandler;
import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import com.company.lesson.server.BasicServer;
import com.company.lesson.server.ContentType;
import com.company.lesson.server.ResponseCodes;


import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LessonServer extends BasicServer {
    private final static Configuration freemarker = initFreeMarker();
    Date date = new Date();
    JsonReader json = new JsonReader();
    Task task;
    Tasks tasks = json.putTasksFromJson("src/tasks.json");
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd");
    Statistics statistics;


    public LessonServer(String host, int port) throws IOException {
        super(host, port);
        registerGet("/tasks",this::viewTasksHandler);
        registerPost("/tasks",this::postTaskHandler);
        registerGet("/task",this::viewTaskHandler);
        registerGet("/done",this::viewDoneTasksHandler);
        registerGet("/procces",this::viewInProccesTasksHandler);
        registerGet("/statistics", this::viewStatisticshandler);
    }

    private void viewStatisticshandler(HttpExchange exchange){
        Tasks tasks = json.putTasksFromJson("src/tasks.json");
        statistics = new Statistics(tasks);
        renderTemplate(exchange, "statistics.html",statistics);
    }

    private void viewInProccesTasksHandler(HttpExchange exchange){
        Tasks tasks = json.putTasksFromJson("src/tasks.json");
        renderTemplate(exchange,"procces.html", tasks);
    }

    private void viewDoneTasksHandler(HttpExchange exchange){
        Tasks tasks = json.putTasksFromJson("src/tasks.json");
        renderTemplate(exchange,"done.html", tasks);
    }


    protected void registerPost(String route, RouteHandler handler){
        getRoutes().put("POST " + route, handler);
    }

    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            // путь к каталогу в котором у нас хранятся шаблоны
            // это может быть совершенно другой путь, чем тот, откуда сервер берёт файлы
            // которые отправляет пользователю
            cfg.setDirectoryForTemplateLoading(new File("data"));

            // прочие стандартные настройки о них читать тут
            // https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewTaskHandler(HttpExchange exchange){
        renderTemplate(exchange, "task.html", task);
    }
    private void postTaskHandler(HttpExchange exchange){
        String raw = getBody(exchange);
        Map<String,String> parsed = LessonServer.parseUrlEncoded(raw, "&");
            task = tasks.getTasks().stream()
                    .filter(a->a.getId() == Integer.parseInt(parsed.get("id"))).findAny().orElse(null);
            redirect303(exchange,"/task");


        }




    private void viewTasksHandler(HttpExchange exchange){
        String queryParams = getQueryParams(exchange);
        Map<String, String> params = LessonServer.parseUrlEncoded(queryParams, "&");
        Tasks tasks = json.putTasksFromJson("src/tasks.json");
        if (params.get("title") != null) {
            task = new Task((tasks.getTasks().get(tasks.getTasks().size()-1).getId() + 1),params.get("title"), false, params.get("name"),formatForDateNow.format(date),params.get("description"));
            tasks.addTask(task);
            json.addTasksToJson(tasks,"src/tasks.json");
        }
        else if (params.get("idDelete") != null){
             tasks.getTasks().removeIf(b->b.getId()== Integer.parseInt(params.get("idDelete")));
             json.addTasksToJson(tasks,"src/tasks.json");
        }
        else if (params.get("idDo") != null){
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                if (tasks.getTasks().get(i).getId() == Integer.parseInt(params.get("idDo"))){
                    tasks.getTasks().get(i).setStatus(true);
                }
                json.addTasksToJson(tasks, "src/tasks.json");
            }
        }
        renderTemplate(exchange,"/tasks.html",tasks);
    }

    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            // загружаем шаблон из файла по имени.
            // шаблон должен находится по пути, указанном в конфигурации
            Template temp = freemarker.getTemplate(templateFile);

            // freemarker записывает преобразованный шаблон в объект класса writer
            // а наш сервер отправляет клиенту массивы байт
            // по этому нам надо сделать "мост" между этими двумя системами

            // создаём поток который сохраняет всё, что в него будет записано в байтовый массив
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // создаём объект, который умеет писать в поток и который подходит для freemarker
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {

                // обрабатываем шаблон заполняя его данными из модели
                // и записываем результат в объект "записи"
                temp.process(dataModel, writer);
                writer.flush();

                // получаем байтовый поток
                var data = stream.toByteArray();

                // отправляем результат клиенту
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    protected String getBody(HttpExchange exchange){
        InputStream input = exchange.getRequestBody();
        Charset utf8 = StandardCharsets.UTF_8;
        InputStreamReader isr = new InputStreamReader(input, utf8);
        try(BufferedReader reader = new BufferedReader(isr)){
            return reader.lines().collect(Collectors.joining(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected String getQueryParams(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        return Objects.nonNull(query) ? query : "";
    }

    public static Map<String,String> parseUrlEncoded(String rawLines, String delimeter){
        String[] pairs = rawLines.split(delimeter);
        Stream<Map.Entry<String, String>> stream = Arrays.stream(pairs)
                .map(LessonServer::decode)
                .filter(Optional::isPresent)
                .map(Optional::get);
        return stream.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

    }
    public static  Optional<Map.Entry<String, String>> decode(String kv){
        if (!kv.contains("=")){
            return Optional.empty();
        }
        String[] pair = kv.split("=");
        if(pair.length != 2){
            return Optional.empty();
        }
        Charset utf8 = StandardCharsets.UTF_8;
        String key = URLDecoder.decode(pair[0], utf8);
        String value = URLDecoder.decode(pair[1],utf8);
        return Optional.of(Map.entry(key, value));

    }
    protected void redirect303(HttpExchange exchange,
                               String path) {
        try {
            exchange.getResponseHeaders().add("Location", path);
            exchange.sendResponseHeaders(303, 0);
            exchange.getResponseBody().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
