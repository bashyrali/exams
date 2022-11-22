public class SaleState extends State{

    @Override
    void startSale(Product product) throws Exception {
        throw new Exception("Товар уже участвует в торгах");
    }
    @Override
    void raisePrice(Product product,int addPrice) throws Exception {
        product.setPrice(addPrice);
    }

    @Override
    void withdraw(Product product) throws Exception  {

        if (product.getOldPrice() == product.getPrice() || product.getOldPrice() == 0 ) {
            product.setProductState(new StockState());
            product.setState("in_stock");
        }
        else{
            throw new Exception("Товар уже в резерве, можно только выдать");
        }
        
    }

    @Override
    void giveToTheWinner(Product product) throws Exception {
        double price = product.getPrice();
        if (price == 0) {
            throw new Exception("Нельзя отдать товар бесплатно");
        }
        else{
            product.setProductState(new SoldState());
            product.setState("sold");
        }
    }
    
}
