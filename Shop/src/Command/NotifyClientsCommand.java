
package Command;

import Model.Shop;

public class NotifyClientsCommand implements Command{

	private Shop shop;
	private String message;
	
	public NotifyClientsCommand(Shop shop) {
		this.shop = shop;
	}

	public void execute() throws Exception {
		shop.sendMessageToClients(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
