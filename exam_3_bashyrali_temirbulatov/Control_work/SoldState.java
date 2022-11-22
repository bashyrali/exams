public class SoldState extends State {

    @Override
    void startSale(Product product) throws Exception {
        throw new Exception("Нельзя начать продажу, так как товар уже продан");
        
    }

    @Override
    void raisePrice(Product product, int addPrice) throws Exception {
        throw new Exception("Нельзя повысить стоимость, так как товар уже продан");
        
    }

    @Override
    void withdraw(Product product) throws Exception{
        throw new Exception("Нельзя снять с торгов, так как товар уже продан");
        
    }

    @Override
    void giveToTheWinner(Product product) throws Exception{
        throw new Exception("Нельзя выдать покупателю, так как товар уже выдан");

    }
    
}
