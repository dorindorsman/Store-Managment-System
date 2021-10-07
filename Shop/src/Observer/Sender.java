/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import Model.Client;
import Model.Product;

public class Sender implements ShopObservable {
	
	private ArrayList<Receiver> clients;
	private HashMap<Client , String> messages;
	
	public Sender() {
		clients = new ArrayList<>();
		messages = new HashMap<>();
	}
	
	private static Sender INSTANCE;
	
	public static Sender getInstance() {
		if(INSTANCE == null)
			INSTANCE = new Sender();
		
		return INSTANCE;
	}
	
	public void Notify(String message) {
		for(Receiver c : clients) {
			c.update(this, message);
		}
	}
	
	public void addObserver(Receiver receiver) {
		if(!clients.contains(receiver))
			clients.add(receiver);
	}
	
	public void receiveMessage(String message , Receiver client) {
		if(client instanceof Client)
			messages.put((Client) client , message);	
	}
	

	public Set<Entry<Client , String>> getMessages(){
		Set<Entry<Client,String>> result = new HashSet<Entry<Client,String>>();
		result.addAll(messages.entrySet());
		messages.clear();
		return result;
	}
	
	public void removeObserver(Receiver receiver) {
		clients.remove(receiver);
	}
	
	public void clearSender() {
		clients.clear();
		messages.clear();
	}
	
	public boolean hasClient() {
		return clients.isEmpty();
	}

	public void addAllObservers(Map<String, Product> map) {
		for(Entry<String, Product> mapTemp : map.entrySet() ) {
			addObserver(mapTemp.getValue().getClient());
		}
		
	}	

}
