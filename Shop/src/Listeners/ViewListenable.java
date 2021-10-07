/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Listeners;

import Model.FileIterator.savingMethod;

public interface ViewListenable {
	
	void viewAsksToCreateShop(savingMethod save)throws Exception;
	
	public void viewAsksToAddProduct(String idOfProduct, String nameOfProduct , int priceForShop, int priceForClient
			, String nameOfClient , String phoneOfClient, boolean salesUpdates) throws Exception;	
	
	public void viewAsksToPrintAllProducts() throws Exception;
	
	public void viewAsksToFindProductByID(String idOfProduct) throws Exception;

	public void viewAsksToDeleteAllProducts()throws Exception;

	public void viewAsksToDeleteLastProduct()throws Exception;

	public void viewAsksToDeleteProductByID(String idOfProduct) throws Exception;
		
	public void viewAsksToPrintProfits() throws Exception ;

	void viewAsksToNotifyClients(String string) throws Exception;

	void viewAsksToReceiveMessages() throws Exception;
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	