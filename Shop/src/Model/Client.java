/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Model;
import java.util.Stack;
import Observer.Receiver;
import Observer.Sender;
import Observer.ShopObservable;

public class Client implements Receiver {

	private String name;
	private String phone;
	private boolean saleUpdates;
	private Stack<String> allMessages;
	
	public Client(String name, String phone, boolean saleUpdates) {
		this.name = name;
		this.phone = phone;
		this.saleUpdates = saleUpdates;
		this.allMessages= new Stack<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getSaleUpdates() {
		return saleUpdates;
	}

	public void setSaleUpdates(boolean saleUpdates) {
		this.saleUpdates = saleUpdates;
	}
	
	@Override
	public String toString() {
		return "Client [name=" + name + ", phone=" + phone + ", saleUpdates=" + saleUpdates + "]";
	}
	
	public boolean equals(Client c) {
		return this.name.equals(c.getName()) &&
				this.phone.equals(c.getPhone()) && this.saleUpdates == c.getSaleUpdates();
	}

	
	@Override
	public void update(ShopObservable sender, String message) {
		allMessages.push(message);
		Sender.getInstance().receiveMessage(name , this);
	}

}
