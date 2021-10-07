
package Command;

import Model.Shop;

public class ShowMessagesCommand implements Command {
	
	private Shop shop;
	
	public ShowMessagesCommand(Shop shop) {
	this.shop = shop;
	}

	@Override
	public void execute() throws Exception {
		shop.printMessages();
	}

}
