
public class StockState extends State {
    

    @Override
    void startSale(Product product) throws Exception  {
        product.setProductState(new SaleState());
        product.setState("for_sale");
        
        
    }

    @Override
     void raisePrice(Product product,int addPrice) throws Exception {
        throw new Exception("Товар еще не участвует в торгах");
    }

    @Override
    void withdraw(Product product) throws Exception {
        throw new Exception("Нельзя снять с торгов товар, который в них не участвует");
        
    }

    @Override
    void giveToTheWinner(Product product) throws Exception {
        throw new Exception("Нельзя отдать товар сразу со склада");
        
    }
    
}
