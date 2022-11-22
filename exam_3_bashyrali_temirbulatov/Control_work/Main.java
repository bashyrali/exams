import java.io.FileNotFoundException;
import java.io.IOException;




public class Main {
    static String pathRead = "products.json";
    public static void main(String[] args) throws IOException {
        boolean start = true;
        try {          
            ReadFile read = new ReadFile();
            String buffer = read.readJsonFile(pathRead);     
            if(buffer.length() > 1) {
                read.DeserilizeJson(buffer);
                read.printProductsTable();
                while(start){
                    System.out.println("1. Добавить Товар\n2.Пропустить");
                    String choice = System.console().readLine();
                    int menuChoice = Integer.parseInt(choice);
                    showMenu(read, menuChoice);
                    System.out.println("добавить еще товар?(Y/N)");
                    String answer = System.console().readLine();
                    if(answer.equalsIgnoreCase("N")){
                        start = false;
                    }
                    else if (answer.equalsIgnoreCase("Y")) {
                        start = true;
                    }
                    read.printProductsTable();
                    
                    
                }
            }
            
            else {
                System.out.println("Буфер пустой");
                
            }       
        } catch (IOException e) {            
            e.printStackTrace();
        }  
        
        
    }
    public static void menu(ReadFile read, int initialState) throws FileNotFoundException {

        while(initialState == 1) {
            read.printProductsTable();
            System.out.print("Выберите товар(ведите его id)");
            int input = Integer.parseInt(System.console().readLine());
            int index = read.printById(input);
            System.out.println("1.Выставить на аукцион");
            System.out.println("2.Поднять цену");
            System.out.println("3.Выдать победителю");
            System.out.println("4.Снять с торгов");
            System.out.println("5.Отобразить информацию о товаре");
            System.out.println("Что вы хотите сделать?(ведите номер)");
            int key = Integer.parseInt(System.console().readLine());
            switch (key) {
                case 1:
                    read.products[index].startSale();
                    break;
                case 2:
                read.products[index].raisePrice();
                    break;
                case 3:
                read.products[index].giveToTheWinner();
                break;
                case 4:
                read.products[index].withdraw();
                break;

                default:
                    break;
            }
            
        }
        
    }
    public static void showMenu(ReadFile read, int initialState) {
        int menuChoice = initialState;
        if(menuChoice == 1) {
            Product product = new Product();
            System.out.println("Добавить id");
            int id = Integer.parseInt(System.console().readLine());
            product.setId(id);
            System.out.println("Добавить name");
            String name = System.console().readLine();
            product.setName(name);
            System.out.println("Добавить price");
            int price = Integer.parseInt(System.console().readLine());
            product.setPrice(price);
            System.out.println("Добавить state");
            String state = System.console().readLine();
            product.setState(state);
            System.out.println("Добавить code");
            String honorary_code = System.console().readLine();
            product.setHonoraryCode(honorary_code);
            read.addProduct(product);
            String json = read.SerializeJson();
            read.printProductsTable();
            try {
                read.addToJsonFile(json, pathRead);
            } catch (IOException e) {            
                e.printStackTrace();
            }
        }
        
    }
}        



