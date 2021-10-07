/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Controller;
import java.util.Set;
import java.util.Map.Entry;
import Command.CommandManager;
import Listeners.ShopListenable;
import Listeners.ViewListenable;
import Model.Client;
import Model.FileIterator.savingMethod;
import Model.Product;
import Model.Shop;
import View.MainView;


public class Controller implements ViewListenable , ShopListenable {

	private Shop theModel;
	private MainView theView;
	private CommandManager commandManager;
	
	public Controller(MainView view , Shop shop , CommandManager cM) {
		this.theView = view;
		this.theModel = shop;
		this.commandManager = cM;
		
		theModel.registerListener(this);
		theView.registerListener(this);
	}

	public void viewAsksCheckIfDataIsEmpty() {
		theModel.checkIfDataIsEmpty();
	}
	
	public void viewAsksToCreateShop(savingMethod save) throws Exception {
		commandManager.getCreateShopCommand().setSavingMethod(save);
		commandManager.createShop();
	}
	
	public void viewAsksToAddProduct(String idOfProduct, String nameOfProduct , int priceForShop, int priceForClient
			, String nameOfClient , String phoneOfClient , boolean salesUpdates) throws Exception {
		Client client = new Client(nameOfClient , phoneOfClient , salesUpdates);
		Product product = new Product(nameOfProduct , priceForShop , priceForClient , client);
		commandManager.getAddProductToTheStoreCommand().setProduct(product);
		commandManager.getAddProductToTheStoreCommand().setIdOfProduct(idOfProduct);
		commandManager.addProduct();
	}
	
	public void viewAsksToPrintAllProducts() throws Exception {
		commandManager.printAllProducts();
		
	}
	
	public void viewAsksToFindProductByID(String idOfProduct) throws Exception {
		commandManager.getFindProductByIDCommand().setSerialNumber(idOfProduct);
		commandManager.findProductByID();
	}
	
	public void viewAsksToDeleteAllProducts() throws Exception {
		commandManager.deleteAllProducts();
	}
	
	public void viewAsksToDeleteLastProduct() throws Exception {
		commandManager.deleteLastProduct();
	}
	
	public void viewAsksToDeleteProductByID(String idOfProduct) throws Exception {
		commandManager.getDeleteProductByIDCommand().setSerialNumberToDel(idOfProduct);
		commandManager.deleteProductByID();
		
	}
	
	
	public void viewAsksToPrintProfits() throws Exception {
		commandManager.printProfits();
	}
	
	@Override
	public void printMessageFromModelEvent(String str) {
		theView.printMessageToView(str);
	}

	@Override
	public void viewAsksToNotifyClients(String message) throws Exception {
		commandManager.getNotifyClientsCommand().setMessage(message);
		commandManager.notifyClients();
	}

	@Override
	public void viewAsksToReceiveMessages() throws Exception {
		commandManager.showMessages();
	}
	
	public void printMessages(Set<Entry<Client, String>> messages) {
		theView.printMessagesToThread(messages);
	}

	@Override
	public void printTextFromModelEvent(String str) {
		theView.printingInfo(str);
		
	}

	@Override
	public void lastProductDeleted() {
		theView.NotifyNoMoreProducts();
	}

	@Override
	public void productDeletedView() {
		theView.deleteProductBackToView();
	}
}
