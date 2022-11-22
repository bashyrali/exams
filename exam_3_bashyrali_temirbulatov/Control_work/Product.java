import com.google.gson.annotations.*;

public class Product {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private double price;
    @Expose
    private String honorary_code;
    @Expose
    private String state;
    private State productState;
    private double oldPrice;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.oldPrice = this.price;
        this.price = price;
    }
    public void setHonoraryCode(String honorary_code) {
        this.honorary_code = honorary_code;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setProductState(State productState) {
        this.productState = productState;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getOldPrice() {
        return oldPrice;
    }
    public String getHonoraryCode() {
        return honorary_code;
    }
    public String getState() {
        return state;
    }
    public void startSale() {
        try {
            productState.startSale(this);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    } 
    public void raisePrice() {

    }
    public void withdraw() {

    }
    public void giveToTheWinner() {

    }
    
}