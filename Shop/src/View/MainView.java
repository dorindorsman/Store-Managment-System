/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package View;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import Listeners.ViewListenable;
import Model.Client;
import Model.FileIterator.savingMethod;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainView {
	private ArrayList<ViewListenable> allListeners;

	//pages
	private StackPane shopMenu;
	private RadioButton btnSortType1;
	private RadioButton btnSortType2;
	private RadioButton btnSortType3;
	private VBox sortType = new VBox(10);
	private VBox menuOptions = new VBox(10);
	private VBox addProductPage = new VBox(10);
	private VBox findProductByIDPage = new VBox(10);
	private VBox deleteProductByIDPage = new VBox(10);
	private VBox notifyClientsOfSalePage = new VBox(10);
	private Button btnReceiveMessageFromClientsOption;
	public static int chosenSort=-1;
	
	public MainView(Stage primaryStage , boolean isDataEmpty) {
		
		allListeners = new ArrayList<ViewListenable>();
		
		//MainPage
		primaryStage.setTitle("Shop Management System");
		shopMenu = new StackPane();
		Image image = new Image(new File("shop.jpg").toURI().toString());
		BackgroundSize backGroundSize = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backGroundSize);
		Background background = new Background(backgroundImage);
		shopMenu.setBackground(background);
		shopMenu.setPadding(new Insets(10));
		
		
		Label titleLabel = new Label("Shop Management System");
		titleLabel.setTextFill(Color.WHITE);
		titleLabel.setFont(new Font(50));
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
		
		////////////////////////SortPage//////////////////////////////////////////////////////////////////////////////////////////////
		
		Label askSortType = new Label("Choose Sort Product Type: ");
		askSortType.setTextFill(Color.WHITE);
		askSortType.setFont(Font.font("Arial", FontWeight.BOLD, 22));
		askSortType.setAlignment(Pos.CENTER);
		ToggleGroup btnSortTypes = new ToggleGroup();
		btnSortType1 = new RadioButton("Alphabet Ascending");
		btnSortType1.setId("0");
		btnSortType1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSortType2 = new RadioButton("Alphabet Descending");
		btnSortType2.setId("1");
		btnSortType2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSortType3 = new RadioButton("Income Order");
		btnSortType3.setId("2");
		btnSortType3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSortType1.setToggleGroup(btnSortTypes);
		btnSortType2.setToggleGroup(btnSortTypes);
		btnSortType3.setToggleGroup(btnSortTypes);

		btnSortType1.setTextFill(Color.WHITE);
		btnSortType2.setTextFill(Color.WHITE);
		btnSortType3.setTextFill(Color.WHITE);
		btnSortType1.setAlignment(Pos.CENTER);
		btnSortType2.setAlignment(Pos.CENTER);
		btnSortType3.setAlignment(Pos.CENTER);

		sortType.setSpacing(10);
		sortType.setAlignment(Pos.CENTER_LEFT);
		sortType.getChildren().addAll(askSortType, btnSortType1, btnSortType2, btnSortType3);
		sortType.setVisible(isDataEmpty);

		
		
		MyRadioEventHandler sortTypesHandler = new MyRadioEventHandler();
		btnSortType1.setOnAction(sortTypesHandler);
		btnSortType2.setOnAction(sortTypesHandler);
		btnSortType3.setOnAction(sortTypesHandler);
		
		
		menuOptions.setAlignment(Pos.CENTER_LEFT);
		menuOptions.setVisible(!isDataEmpty);
	
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
		
/////////////////////////////////////////////MenuOptions////////////////////////////////////////////////////////////////////
		Button btnAddProductOption = new Button();
		btnAddProductOption.setText("Add Product");
		btnAddProductOption.setTextFill(Color.WHITE);
		btnAddProductOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		btnAddProductOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Show Add Participant Page
				try {
					menuOptions.setVisible(false);
					addProductPage.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		
		Button btnPrintAllProductOption = new Button();
		btnPrintAllProductOption.setText("Print All Products");
		btnPrintAllProductOption.setTextFill(Color.WHITE);
		btnPrintAllProductOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		btnPrintAllProductOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Print All Product
				for (ViewListenable l : allListeners) {
					try {
						l.viewAsksToPrintAllProducts();
					} catch (InputMismatchException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});	
		
		Button btnfindProductByIDOption = new Button();
		btnfindProductByIDOption.setText("Find Product By ID");
		btnfindProductByIDOption.setTextFill(Color.WHITE);
		btnfindProductByIDOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		btnfindProductByIDOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//Show Find Product By ID Page
				try {
					menuOptions.setVisible(false);
					findProductByIDPage.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		//Print Shop Product Profit
		Button btnPrintShopProductProfitOption = new Button();
		btnPrintShopProductProfitOption.setText("Print Profits");
		btnPrintShopProductProfitOption.setTextFill(Color.WHITE);
		btnPrintShopProductProfitOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

		btnPrintShopProductProfitOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				for (ViewListenable l : allListeners) {
					try {
						l.viewAsksToPrintProfits();
					} catch (InputMismatchException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}

		});	
		
		//Notify Clients 
		Button btnNotifyClientsOption = new Button();
		btnNotifyClientsOption.setText("Notify Clients Of Sale");
		btnNotifyClientsOption.setTextFill(Color.WHITE);
		btnNotifyClientsOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

		btnNotifyClientsOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Show Notify Clients Of Sale
				try {
					menuOptions.setVisible(false);
					notifyClientsOfSalePage.setVisible(true);
				} catch (InputMismatchException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});	
		
		//Receive Message From Clients
		btnReceiveMessageFromClientsOption = new Button();
		btnReceiveMessageFromClientsOption.setText("Read Messages");
		btnReceiveMessageFromClientsOption.setTextFill(Color.WHITE);
		btnReceiveMessageFromClientsOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		btnReceiveMessageFromClientsOption.setDisable(true);
		
		//Receive set on action
		btnReceiveMessageFromClientsOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				for(ViewListenable l:allListeners) {
					try {
						l.viewAsksToReceiveMessages();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});	

		
		//DeleteAllProducts
		Button btnDeleteAllProductsOption = new Button();
		btnDeleteAllProductsOption.setText("Delete All Products");
		btnDeleteAllProductsOption.setTextFill(Color.WHITE);
		btnDeleteAllProductsOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

		btnDeleteAllProductsOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//DeleteAllProducts
				for (ViewListenable l : allListeners) {
					try {
						l.viewAsksToDeleteAllProducts();
						menuOptions.setVisible(false);
						btnReceiveMessageFromClientsOption.setDisable(true);
						btnSortType1.setSelected(false);
						btnSortType2.setSelected(false);
						btnSortType3.setSelected(false);
						sortType.setVisible(true);
					} catch (InputMismatchException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}

		});	
		
		// DeleteLastProduct
		Button btnDeleteLastProductOption = new Button();
		btnDeleteLastProductOption.setText("Delete Last Product");
		btnDeleteLastProductOption.setTextFill(Color.WHITE);
		btnDeleteLastProductOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		btnDeleteLastProductOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// DeleteLastProduct
				for (ViewListenable l : allListeners) {
					try {
						l.viewAsksToDeleteLastProduct();
					} catch (InputMismatchException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}

		});
		
		Button btnDeleteProductByIDOption = new Button();
		btnDeleteProductByIDOption.setText("Delete Product By ID");
		btnDeleteProductByIDOption.setTextFill(Color.WHITE);
		btnDeleteProductByIDOption.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		btnDeleteProductByIDOption.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//Show Delete Product By ID Page
				try {
					menuOptions.setVisible(false);
					deleteProductByIDPage.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
/////////////////////////////////////////////////////////AllPages//////////////////////////////////////////////////////////////////
		
		// Add Product

		//ID
		HBox productIdRow = new HBox();
		Label askProductId = new Label("Product ID: ");
		askProductId.setTextFill(Color.WHITE);
		TextField productId = new TextField();
		productIdRow.setSpacing(30);
		productIdRow.setAlignment(Pos.CENTER_LEFT);
		productIdRow.getChildren().addAll(askProductId, productId);

		//Name
		HBox productNameRow = new HBox();
		Label askProductName = new Label("Product Name: ");
		askProductName.setTextFill(Color.WHITE);
		TextField productName = new TextField();
		productNameRow.setSpacing(10);
		productNameRow.setAlignment(Pos.CENTER_LEFT);
		productNameRow.getChildren().addAll(askProductName, productName);

		//PriceForShop
		HBox priceForShopRow = new HBox();
		Label askPriceForShopRow = new Label("Price For Shop: ");
		askPriceForShopRow.setTextFill(Color.WHITE);
		TextField priceForShop = new TextField();
		priceForShopRow.setSpacing(10);
		priceForShopRow.setAlignment(Pos.CENTER_LEFT);
		priceForShopRow.getChildren().addAll(askPriceForShopRow, priceForShop);

		//PriceForClient
		HBox priceForClientRow = new HBox();
		Label askPriceForClient = new Label("Price For Client: ");
		askPriceForClient.setTextFill(Color.WHITE);
		TextField priceForClient = new TextField();
		priceForClientRow.setSpacing(7);
		priceForClientRow.setAlignment(Pos.CENTER_LEFT);
		priceForClientRow.getChildren().addAll(askPriceForClient, priceForClient);

		//NameOfClient
		HBox nameOfClientRow = new HBox();
		Label asknameOfClient = new Label("Name Of Client: ");
		asknameOfClient.setTextFill(Color.WHITE);
		TextField nameOfClient = new TextField();
		nameOfClientRow.setSpacing(6);
		nameOfClientRow.setAlignment(Pos.CENTER_LEFT);
		nameOfClientRow.getChildren().addAll(asknameOfClient, nameOfClient);

		//PhoneOfClient
		HBox phoneOfClientRow = new HBox();
		Label askphoneOfClient = new Label("Phone Of Client: ");
		askphoneOfClient.setTextFill(Color.WHITE);
		TextField phoneOfClient = new TextField();
		phoneOfClientRow.setSpacing(5);
		phoneOfClientRow.setAlignment(Pos.CENTER_LEFT);
		phoneOfClientRow.getChildren().addAll(askphoneOfClient, phoneOfClient);

		//Boolean salesUpdates
		HBox salesUpdatesRow = new HBox();
		CheckBox saleUpadtes= new CheckBox();
		Label asksaleUpadtes = new Label("Sale Updates: ");
		asksaleUpadtes.setTextFill(Color.WHITE);
		salesUpdatesRow.getChildren().addAll(asksaleUpadtes,saleUpadtes);
		salesUpdatesRow.setAlignment(Pos.CENTER_LEFT);

		Button btnAddProduct = new Button();
		btnAddProduct.setText("Add Product");
		btnAddProduct.setTextFill(Color.WHITE);
		btnAddProduct.setAlignment(Pos.CENTER_LEFT);
		btnAddProduct.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

		Button backBtn1=new Button();
		backBtn1.setText("Back");		
		backBtn1.setTextFill(Color.WHITE);		
		backBtn1.setAlignment(Pos.CENTER_LEFT);
		backBtn1.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		backBtn1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				clearAddProductPage();
				addProductPage.setVisible(false);
				menuOptions.setVisible(true);
			}
			private void clearAddProductPage() {
				productId.clear();
				productName.clear();
				priceForShop.clear();
				priceForClient.clear();
				nameOfClient.clear();
				phoneOfClient.clear();
				saleUpadtes.setSelected(false);
			}
			
		});
		
		addProductPage.setVisible(false);
		addProductPage.setAlignment(Pos.CENTER_LEFT);
		addProductPage.getChildren().addAll(productIdRow,productNameRow,priceForShopRow,priceForClientRow,nameOfClientRow,phoneOfClientRow,salesUpdatesRow,btnAddProduct,backBtn1);

		btnAddProduct.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//Add Participant
				for (ViewListenable l : allListeners) {
					try {
						if(priceForShop.getText().equals("") || priceForClient.getText().equals("") ) {
							priceForShop.setText("0");
							priceForClient.setText("0");
						}
						l.viewAsksToAddProduct(productId.getText(),productName.getText(),Integer.parseInt(priceForShop.getText()),Integer.parseInt(priceForClient.getText())
								,nameOfClient.getText(),phoneOfClient.getText(),saleUpadtes.isSelected());
						addProductPage.setVisible(false);
						menuOptions.setVisible(true);
					} catch (InputMismatchException e) {
						clearAddProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						clearAddProductPage();
						JOptionPane.showMessageDialog(null, "Prices are not legit");
					} catch (Exception e) {
						clearAddProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					clearAddProductPage();
				}
			}

			private void clearAddProductPage() {
				productId.clear();
				productName.clear();
				priceForShop.clear();
				priceForClient.clear();
				nameOfClient.clear();
				phoneOfClient.clear();
				saleUpadtes.setSelected(false);;
			}

		});

		//FindProductByID
		HBox findProductByIDRow = new HBox();
		Label askfindProductByID = new Label("Product ID: ");
		askfindProductByID.setTextFill(Color.WHITE);
		TextField findProductByID = new TextField();
		findProductByIDRow.setSpacing(10);
		findProductByIDRow.setAlignment(Pos.CENTER_LEFT);
		findProductByIDRow.getChildren().addAll(askfindProductByID, findProductByID);

		Button btnfindProductByID = new Button();
		btnfindProductByID.setText("Find Product By ID");
		btnfindProductByID.setTextFill(Color.WHITE);
		btnfindProductByID.setAlignment(Pos.CENTER_LEFT);
		btnfindProductByID.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

		Button backBtn2=new Button();
		backBtn2.setText("Back");		
		backBtn2.setTextFill(Color.WHITE);		
		backBtn2.setAlignment(Pos.CENTER_LEFT);
		backBtn2.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		backBtn2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				findProductByID.clear();
				findProductByIDPage.setVisible(false);
				menuOptions.setVisible(true);
			}
		});
	

		findProductByIDPage.getChildren().addAll(findProductByIDRow,btnfindProductByID,backBtn2);
		findProductByIDPage.setAlignment(Pos.CENTER_LEFT);
		findProductByIDPage.setVisible(false);
		
		btnfindProductByID.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Find Product By ID
				for (ViewListenable l : allListeners) {
					try {
						l.viewAsksToFindProductByID(findProductByID.getText());
						findBackToMenu();
					} catch (InputMismatchException e) {
						findBackToMenu();
						clearFindProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						findBackToMenu();
						clearFindProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						findBackToMenu();
						clearFindProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					clearFindProductPage();
				}
			}

			private void clearFindProductPage() {
				findProductByID.clear();
			}

		});	
		
		//Notify Clients Of Sale
		HBox notifyClientsOfSaleRow = new HBox();
		Label askMesseageToSend = new Label("Enter Message: ");
		askMesseageToSend.setTextFill(Color.WHITE);
		TextField messeageToSend = new TextField();
		notifyClientsOfSaleRow.setSpacing(10);
		notifyClientsOfSaleRow.setAlignment(Pos.CENTER_LEFT);
		notifyClientsOfSaleRow.getChildren().addAll(askMesseageToSend, messeageToSend);

		Button btnNotifyClientsOfSale = new Button();
		btnNotifyClientsOfSale.setText("Send Messeage");
		btnNotifyClientsOfSale.setTextFill(Color.WHITE);
		btnNotifyClientsOfSale.setAlignment(Pos.CENTER_LEFT);
		btnNotifyClientsOfSale.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));


		Button backBtn3=new Button();
		backBtn3.setText("Back");		
		backBtn3.setTextFill(Color.WHITE);		
		backBtn3.setAlignment(Pos.CENTER_LEFT);
		backBtn3.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		backBtn3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				messeageToSend.clear();
				notifyClientsOfSalePage.setVisible(false);
				menuOptions.setVisible(true);
			}
		});
		
		
		notifyClientsOfSalePage.getChildren().addAll(notifyClientsOfSaleRow,btnNotifyClientsOfSale,backBtn3);
		notifyClientsOfSalePage.setAlignment(Pos.CENTER_LEFT);
		notifyClientsOfSalePage.setVisible(false);

		//Notify set on action
		btnNotifyClientsOfSale.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				for (ViewListenable l : allListeners) {
					try {
						l.viewAsksToNotifyClients(messeageToSend.getText());
						btnReceiveMessageFromClientsOption.setDisable(false);
						notifyClientsOfSalePage.setVisible(false);
						menuOptions.setVisible(true);
					} catch (InputMismatchException e) {
						clearNotifyPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						clearNotifyPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						clearNotifyPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					clearNotifyPage();
				}
			}

			private void clearNotifyPage() {
				messeageToSend.clear();				
			}

		});	
		
		
		//DeleteProductByID
		HBox deleteProductByIDRow = new HBox();
		Label askDeleteProductByID = new Label("Product ID: ");
		askDeleteProductByID.setTextFill(Color.WHITE);
		TextField deleteProductByID = new TextField();
		deleteProductByIDRow.setSpacing(10);
		deleteProductByIDRow.setAlignment(Pos.CENTER_LEFT);
		deleteProductByIDRow.getChildren().addAll(askDeleteProductByID, deleteProductByID);

		Button btnDeleteProductByID = new Button();
		btnDeleteProductByID.setText("Delete Product By ID");
		btnDeleteProductByID.setTextFill(Color.WHITE);
		btnDeleteProductByID.setAlignment(Pos.CENTER_LEFT);
		btnDeleteProductByID.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

		Button backBtn4=new Button();
		backBtn4.setText("Back");		
		backBtn4.setTextFill(Color.WHITE);		
		backBtn4.setAlignment(Pos.CENTER_LEFT);
		backBtn4.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		backBtn4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				deleteProductByID.clear();
				deleteProductByIDPage.setVisible(false);
				menuOptions.setVisible(true);	
			}
		});
		
		deleteProductByIDPage.getChildren().addAll(deleteProductByIDRow,btnDeleteProductByID,backBtn4);
		deleteProductByIDPage.setAlignment(Pos.CENTER_LEFT);
		deleteProductByIDPage.setVisible(false);
	
		btnDeleteProductByID.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Delete Product By ID 
				for (ViewListenable l : allListeners) {
					try {
						l.viewAsksToDeleteProductByID(deleteProductByID.getText());
						clearDeleteProductPage();
						deleteProductBackToView();
					} catch (InputMismatchException e) {
						deleteProductBackToView();
						clearDeleteProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (NumberFormatException e) {
						deleteProductBackToView();
						clearDeleteProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						deleteProductBackToView();
						clearDeleteProductPage();
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}

			private void clearDeleteProductPage() {
				deleteProductByID.clear();
			}

		});	


		menuOptions.getChildren().addAll(btnAddProductOption,btnfindProductByIDOption,btnPrintAllProductOption,btnPrintShopProductProfitOption,btnDeleteProductByIDOption,btnDeleteAllProductsOption,btnDeleteLastProductOption,btnNotifyClientsOption,btnReceiveMessageFromClientsOption);
		shopMenu.getChildren().addAll(titleLabel,sortType,menuOptions,addProductPage,findProductByIDPage,deleteProductByIDPage,notifyClientsOfSalePage);	
		primaryStage.setScene(new Scene(shopMenu,800,600));
		primaryStage.show();
	}

	

	public void registerListener(ViewListenable listener) {
		allListeners.add(listener);
	}

	class MyRadioEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			chosenSort= Integer.parseInt(((RadioButton)ae.getSource()).getId()); 
			savingMethod save = savingMethod.values()[chosenSort];
			for (ViewListenable l : allListeners) {
				try {
					l.viewAsksToCreateShop(save);
					sortType.setVisible(false);
					menuOptions.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}
	
	public void printMessagesToThread(Set<Entry<Client, String>> messages){
		try {
			RecieveSmsView recieveView = new RecieveSmsView(new Stage(), messages);
			btnReceiveMessageFromClientsOption.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void printMessageToView(String str){
		JOptionPane.showMessageDialog(null, str);
	}
	
	public void printingInfo(String str) {
		Stage printStage = new Stage();
		printStage.setTitle("Information");
		VBox vb = new VBox(10);
	
		Label data=new Label("Your Data");
		data.setAlignment(Pos.CENTER);
		data.setFont(new Font(50));
		data.setFont(Font.font("Arial", FontWeight.BOLD, 22));

		// Scroll
		ScrollPane scroll = new ScrollPane();
		scroll.autosize();
		scroll.setContent(new Label(str));
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(data,scroll);
	
		Scene scene = new Scene(vb, 800, 300);
		printStage.setScene(scene);
		printStage.show();
	}
	
	public void findBackToMenu() {
		findProductByIDPage.setVisible(false);
		menuOptions.setVisible(true);
	}

	public void deleteProductBackToView() {
	deleteProductByIDPage.setVisible(false);
	menuOptions.setVisible(true);
	}
	
	public void NotifyNoMoreProducts() {
			deleteProductByIDPage.setVisible(false);
			menuOptions.setVisible(false);
			btnReceiveMessageFromClientsOption.setDisable(true);
			btnSortType1.setSelected(false);
			btnSortType2.setSelected(false);
			btnSortType3.setSelected(false);
			sortType.setVisible(true);
	}

	
}