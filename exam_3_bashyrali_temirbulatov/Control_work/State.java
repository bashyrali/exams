abstract class State {
    
    abstract void startSale(Product product) throws Exception; 
    abstract void raisePrice(Product product, int addPrice) throws Exception;
    abstract void withdraw(Product product) throws Exception;
    abstract void giveToTheWinner(Product product) throws Exception;
}
