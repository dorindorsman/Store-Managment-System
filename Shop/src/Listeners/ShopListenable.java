/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Listeners;

import java.util.Map.Entry;
import java.util.Set;

import Model.Client;

public interface ShopListenable {
	
	void printMessageFromModelEvent(String str);
	void printTextFromModelEvent(String str);
	void printMessages(Set<Entry<Client, String>> result);
	void lastProductDeleted();
	void productDeletedView();

}
