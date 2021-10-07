/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import Listeners.ShopListenable;
import Memento.CareTaker;
import Memento.OriginatorClass;
import Memento.ProductMemento;
import Model.FileIterator.savingMethod;
import Observer.Sender;

public class Shop { 
	
	private Map<String,Product> map;
	private ArrayList<ShopListenable> allListeners;

	private final String fileName = "products.txt";
	private CareTaker careTaker;
	private OriginatorClass oC;
	private FileIterator fileIterator;
	private boolean isDataEmpty;
	private Sender sender;

	


	public Shop() throws Exception {
		allListeners = new ArrayList<ShopListenable>();
		try {
			this.sender = Sender.getInstance();
			map = new TreeMap<>(); //added
			fileIterator = new FileIterator(fileName);
			careTaker = new CareTaker();
			oC = new OriginatorClass();
			InitDataFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	private void InitDataFromFile() throws Exception {
		if(fileIterator.getComparator() != null) {
			while(fileIterator.hasNext()) {
				Entry<String,Product> entry = fileIterator.next();
				addProductFromFile(entry);
				updateMessagesList(entry.getKey() , entry.getValue());
				isDataEmpty = false;
			}
		}
		else {
			isDataEmpty = true;
		}
	}
	
	public boolean checkIfDataIsEmpty() {
		return isDataEmpty;
	}
	
	//We need both FileIterator or only the Iterator in the constructor
	public void ChooseDatabaseShop(savingMethod method) throws IOException {
		fileIterator = new FileIterator(fileName , method);
		if(fileIterator.getComparator() == null)
			map = new LinkedHashMap<>();
		else
		map = new TreeMap<String,Product>(fileIterator.getComparator());
	}

	//Register listener
	public void registerListener(ShopListenable l) {
		allListeners.add(l);
	}	
	

	public void addProduct(String idOfProduct, Product product) throws Exception {
		if(idOfProduct.equals(""))
			throw new Exception("ID of product is not valid");
		if(product.getPriceForShop() < 0 || product.getPriceForClient() < 0)
			throw new Exception("price for shop/client is not valid");
		if (map.containsKey(idOfProduct)) {
			if(map.get(idOfProduct).equals(product))
				throw new Exception("Product is already exist!");
			
			Product p = checkIfProductExist(idOfProduct);
			map.replace(idOfProduct, p, product);
					
			firePrintMessageEvent("The product has been replaced successfully\n");
			return;
		}
		
		//adding to map
		map.put(idOfProduct, product);
		//saving the map in memento:
		oC.setProduct(new ProductMemento(product, idOfProduct));
		careTaker.save(oC.saveMemento());
		//adding to file
		Entry<String,Product> entry = new java.util.AbstractMap.SimpleEntry<String,Product>(idOfProduct, product);
		fileIterator.writeProduct(entry);
		updateMessagesList(idOfProduct,product);
		firePrintMessageEvent("The product has been added successfully\n");
	}
	
	public void addProductFromFile(Entry<String,Product> entry) throws Exception {
		oC.setProduct(new ProductMemento(entry.getValue() , entry.getKey()));
		careTaker.save(oC.saveMemento());
		map.put(entry.getKey(), entry.getValue());
		updateMessagesList(entry.getKey() ,entry.getValue());
		
	}

	public String printProduct(String idOfProduct)throws Exception {
		if(idOfProduct.equals(""))
			throw new Exception("ID of product is not valid\n");
		if(checkIfProductExist(idOfProduct) == null)
			throw new Exception("Product was not found\n");
		return "Serial number: " + idOfProduct + "\n Product: " + map.get(idOfProduct).toString();
	}
	
	public void printAllProducts()throws Exception {
		StringBuffer sb = new StringBuffer();
		String idOfProduct;
		Iterator<Map.Entry<String, Product>> it = map.entrySet().iterator();
		sb.append("All Products: \n");
		while(it.hasNext()) {
			idOfProduct = it.next().getKey();
			sb.append(printProduct(idOfProduct) + "\n");
		}
		firePrintTextEvent(sb.toString());
	}
	
	public void findProductByID(String id) throws Exception {
		String product;
		Iterator<Map.Entry<String, Product>> it = map.entrySet().iterator();
		Entry<String,Product> entryTemp;
		while(it.hasNext()) {
			entryTemp = it.next();
			if(entryTemp.getKey().equals(id)) {
				product = printProduct(id);
				firePrintTextEvent(product);
				return;
			}
		}
		 throw new Exception("Product was not found");
	}
	
	public Product checkIfProductExist(String id) throws Exception {
		Iterator<Map.Entry<String, Product>> it = map.entrySet().iterator();
		Entry<String,Product> entryTemp;
		while(it.hasNext()) {
			entryTemp = it.next();
			if(entryTemp.getKey().equals(id)) {
				return entryTemp.getValue();
			}
		}
		 return null;
	}
	
	public Client checkIfClientExist(Client client) throws Exception {
		Iterator<Map.Entry<String, Product>> it = map.entrySet().iterator();
		Entry<String,Product> entryTemp;
		while(it.hasNext()) {
			entryTemp = it.next();
			if(entryTemp.getValue().getClient().equals(client)) {
				return entryTemp.getValue().getClient();
			}
		}
		 return null;
	}
	
	
	
	public void deleteAllProducts() throws Exception {
		if(map.size() == 0)
			throw new Exception("Data is empty\n");
		map.clear();
		sender.clearSender();
		fileIterator.removeAllProduct();
		fileIterator.deleteSortFromFile();
		firePrintMessageEvent("All products have been removed! system has been restarted!");
	}
	
	public void deleteLastProduct() throws Exception {
		oC.getProductFromMemento(careTaker.restore());
		ProductMemento pro = oC.getProductMemento();
		if (pro == null) {
			firePrintMessageEvent("There are no products!");
			return;
		}
			if(map.size() == 1) {
				sender.clearSender();
				fileIterator.removeAllProduct();
				map.remove(pro.getIdOfProduct());
				sender.addAllObservers(map);
				fileIterator.writeAllProducts(map);	
				fileIterator.deleteSortFromFile();
				fireLastProductDeleted();
				firePrintMessageEvent("There are not more products! system has been restarted!");
				return;
			}
			sender.clearSender();
			fileIterator.removeAllProduct();
			map.remove(pro.getIdOfProduct());
			sender.addAllObservers(map);
			fileIterator.writeAllProducts(map);	
			firePrintMessageEvent("Product has been deleted successfully!");
	}
	

	public void deleteProductByID(String id) throws Exception {
		if(checkIfProductExist(id) != null) {
			if(map.size() == 1) {
				fileIterator.removeAllProduct();
				fileIterator.deleteSortFromFile();
				sender.clearSender();
				map.remove(id);
				fireLastProductDeleted();
				firePrintMessageEvent("There are not more products! system has been restarted!");
				return;
			}
				fireDeleteProductView();
				sender.removeObserver(map.get(id).getClient());
				map.remove(id);
				fileIterator.removeProduct(id);
				firePrintMessageEvent("Product has been deleted!");
				return;
		}
		throw new Exception("Product Not Exist!");
	}
	

	public void printProfits() throws Exception {
		if(map.size() == 0)
			throw new Exception("Data is empty\n");
		StringBuffer sb = new StringBuffer();
		int totalProfit = 0, productProfit = 0;
		for (Map.Entry<String, Product> products : map.entrySet()) {
			sb.append("The Makat is: " + products.getKey());
			productProfit = products.getValue().profitOfShopForProduct();
			totalProfit += productProfit;
			sb.append(" Product name is: " + products.getValue().getName() + " The profit from selling it is: "
					+ productProfit + "\n");
		}
		sb.append("The Total Profit From All The Product Is: " + totalProfit + "\n");
		firePrintTextEvent(sb.toString());
	}

	public void printMessages() {
		firePrintMessages(sender.getMessages());
	}
	
	
	public void sendMessageToClients(String message) {
		sender.Notify(message);
		firePrintMessageEvent("Message Has Been Sent!");
	}
	
	public boolean hasClientToNotify() {
		return sender.hasClient();
	}
	
	private void updateMessagesList(String idProsuct, Product product) throws Exception {
		Product productFound= checkIfProductExist(idProsuct);
		if(productFound != null)
			sender.removeObserver(productFound.getClient());
		if(product.getClient().getSaleUpdates())
			sender.addObserver(product.getClient());
	}
	
	public void firePrintMessages(Set<Entry<Client,String>> result) {
		for (ShopListenable l : allListeners) {
			l.printMessages(result);
		}
	}
	
	private void firePrintMessageEvent(String str) {
		for (ShopListenable l : allListeners) {
			l.printMessageFromModelEvent(str);
		}
	}
	
	private void firePrintTextEvent(String str) {
		for (ShopListenable l : allListeners) {
			l.printTextFromModelEvent(str);
		}
	}	
	
	public void fireLastProductDeleted() {
		for (ShopListenable l : allListeners) {
			l.lastProductDeleted();
		}
	}
	
	public void fireDeleteProductView() {
		for (ShopListenable l : allListeners) {
			l.productDeletedView();
		}
	}
}
