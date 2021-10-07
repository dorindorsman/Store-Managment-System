/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Command;

public class CommandManager {
	
	private Command addProductCommand;
	private Command createShopCommand;
	private Command deleteAllProductsCommand;
	private Command deleteLastProductCommand;
	private Command deleteProductByIDCommand;
	private Command findProductByIDCommand;
	private Command printAllProductsCommand;
	private Command printProfitsCommand;
	private Command notifyClientsCommand;
	private Command showMessagesCommand;
	
		
	public CommandManager(Command addProductCommand, Command createShopCommand ,
			Command deleteAllProductsCommand , Command deleteLastProductCommand,
			Command deleteProductByIDCommand , Command findProductByIDCommand ,
			Command printAllProductsCommand,
			Command printProfitsCommand, Command notifyClientsCommand , Command showMessagesCommand) {
		this.addProductCommand = addProductCommand;
		this.createShopCommand = createShopCommand;
		this.deleteAllProductsCommand = deleteAllProductsCommand;
		this.deleteLastProductCommand = deleteLastProductCommand;
		this.deleteProductByIDCommand = deleteProductByIDCommand;
		this.findProductByIDCommand = findProductByIDCommand;
		this.printAllProductsCommand = printAllProductsCommand;
		this.printProfitsCommand = printProfitsCommand;
		this.notifyClientsCommand = notifyClientsCommand;
		this.showMessagesCommand = showMessagesCommand;
	}
	
	public void addProduct() throws Exception {
		addProductCommand.execute();
	}

	public void createShop() throws Exception {
		this.createShopCommand.execute();
	}

	public void deleteAllProducts() throws Exception {
		this.deleteAllProductsCommand.execute();
	}

	public void deleteLastProduct() throws Exception {
		this.deleteLastProductCommand.execute();
	}

	public void deleteProductByID() throws Exception {
		this.deleteProductByIDCommand.execute();
	}

	public void findProductByID() throws Exception {
		findProductByIDCommand.execute();
	}

	public void printAllProducts() throws Exception {
		this.printAllProductsCommand.execute();
	}

	public void printProfits() throws Exception {
		this.printProfitsCommand.execute();
	}
	
	public void notifyClients() throws Exception {
		this.notifyClientsCommand.execute();
	}
	
	public void showMessages() throws Exception {
		this.showMessagesCommand.execute();
	}
	
	public AddProductCommand getAddProductToTheStoreCommand() {
		return (AddProductCommand)addProductCommand;
	}
	
	public CreateShopCommand getCreateShopCommand() {
		return (CreateShopCommand)createShopCommand;
	}

	public DeleteAllProductsCommand getDeleteAllProductsCommand() {
		return (DeleteAllProductsCommand)deleteAllProductsCommand;
	}

	public DeleteLastProductCommand getDeleteLastProductCommand() {
		return (DeleteLastProductCommand)deleteLastProductCommand;
	}


	public DeleteProductByIDCommand getDeleteProductByIDCommand() {
		return (DeleteProductByIDCommand)deleteProductByIDCommand;
	}

	public FindProductByIDCommand getFindProductByIDCommand() {
		return (FindProductByIDCommand)findProductByIDCommand;
	}

	public PrintAllProductsCommand getPrintAllProductsCommand() {
		return (PrintAllProductsCommand)printAllProductsCommand;
	}

	public PrintProfitsCommand getPrintProfitsCommand() {
		return (PrintProfitsCommand)printProfitsCommand;
	}
	
	public NotifyClientsCommand getNotifyClientsCommand() {
		return (NotifyClientsCommand)notifyClientsCommand;
	}
	
	public ShowMessagesCommand getShowMessagesCommand() {
		return (ShowMessagesCommand)showMessagesCommand;
	}

}
