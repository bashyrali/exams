import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class ReadFile {
    Product[] products;
    public String readJsonFile(String filePath) throws IOException {
        try(FileReader reader = new FileReader(filePath)) {
            BufferedReader buffer = new BufferedReader(reader);
            return buffer.readLine();
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException();
        }
    }
    public void DeserilizeJson(String json) {
        try{
            Gson builder = new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation()
                            .create();
            products = builder.fromJson(json, Product[].class);
        } catch (JsonParseException ex) {
            System.out.println("Ошибка считывания json файла");
        }              
    }
    public String SerializeJson()
    {
        Gson builder = new GsonBuilder().create();
        return builder.toJson(products);
    }
    public void addProduct(Product product) {
        Product[] tempProducts = new Product[products.length + 1];
        int index = 0;
        for(Product t : products) {
            tempProducts[index++] = t;
        }
        tempProducts[products.length] = product;
        products = tempProducts;
    }
    public void addToJsonFile(String textJson, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.append(textJson);
        writer.close();
    }
    public void printProductsTable() {
        System.out.println("| Id |    Name   | Price |   State  |");
        System.out.println("-------------------------------");
        String format = "| %2s | %9s | %4s | %8s |";
        for(Product products: products) {
            String mesage = String.format(format,products.getId(),products.getName(), products.getPrice(), products.getState());
            System.out.println(mesage);
        }
    }
    public int printById(int id) throws FileNotFoundException {
        String format = "| %2s | %9s | %4s | %8s |";
        int index = 0;
        for(Product product: products) {
            if(product.getId() == id) {
                String mes = String.format(format,product.getId(),product.getName(),product.getPrice(),product.getState() );
                System.out.println(mes);
                return index ;
            }     
            index++;
        }
        throw new FileNotFoundException();
    }
    
}
