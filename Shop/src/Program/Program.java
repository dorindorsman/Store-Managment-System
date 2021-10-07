/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Program;

import Command.AddProductCommand;
import Command.CommandManager;
import Command.CreateShopCommand;
import Command.DeleteAllProductsCommand;
import Command.DeleteLastProductCommand;
import Command.DeleteProductByIDCommand;
import Command.FindProductByIDCommand;
import Command.NotifyClientsCommand;
import Command.PrintAllProductsCommand;
import Command.PrintProfitsCommand;
import Command.ShowMessagesCommand;
import Controller.Controller;
import Model.Shop;
import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	
		Shop theModel= new Shop();
		boolean isDataEmpty = theModel.checkIfDataIsEmpty();
		MainView theView2 = new MainView(primaryStage , isDataEmpty);
		AddProductCommand add = new AddProductCommand(theModel);
		CreateShopCommand create = new CreateShopCommand(theModel);
		DeleteAllProductsCommand deleteAll = new DeleteAllProductsCommand(theModel);
		DeleteLastProductCommand deleteLast = new DeleteLastProductCommand(theModel);
		DeleteProductByIDCommand deleteBy = new DeleteProductByIDCommand(theModel);
		FindProductByIDCommand find = new FindProductByIDCommand(theModel);
		PrintAllProductsCommand printAll = new PrintAllProductsCommand(theModel);
		PrintProfitsCommand productsProfit = new PrintProfitsCommand(theModel);
		NotifyClientsCommand notify = new NotifyClientsCommand(theModel);
		ShowMessagesCommand showMessages = new ShowMessagesCommand(theModel);
		CommandManager cM = new CommandManager(add , create , deleteAll , deleteLast ,deleteBy
				,find , printAll, productsProfit , notify , showMessages);
		Controller theController = new Controller(theView2, theModel , cM);
	}

	
	
}