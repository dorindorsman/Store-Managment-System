/*
 * Dorin Dorsman - 315827014
 * Yehiel Butael - 315016774
 */

package View;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import java.util.Set;
import Model.Client;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RecieveSmsView {
	private Set<Entry<Client, String>> set;
	private int index;
	private Text[] messagesText;
	private Text[] numbersText;
	private GridPane smsPage=new GridPane();
	
	
	public RecieveSmsView(Stage stage, Set<Entry<Client, String>> messages) throws Exception {
		showReceivedMessages(messages);
	
		Text labelMsg=new Text("Messages");
		Text numbersOfClients=new Text("Phone Numbers");
		smsPage.setPadding(new Insets(10));
		smsPage.setAlignment(Pos.CENTER);
		smsPage.setGridLinesVisible(true);
		smsPage.setHgap(0);
		smsPage.setVgap(0);
		smsPage.add(labelMsg, 0, 0);
		smsPage.add(numbersOfClients, 1, 0);
		
		
		if(set!=null) 
		{
			index=0;
			Thread t = new Thread(() -> {
				try {
					for (Entry<Client, String> entryTemp: set) {
						Thread.sleep(2000);
						Platform.runLater(() -> {
							messagesText[index].setVisible(true);
							numbersText[index].setVisible(true);
							index++;
						});
					}
				} catch (InterruptedException e) {
				}

			});
			t.start();
		}
		else {
			JOptionPane.showMessageDialog(null, "There is No Client who get Messeage");
			}
		stage.setTitle("Messeages Window Approve");
		stage.setScene(new Scene(smsPage,250,250));
		stage.show();
	}
	
	public void showReceivedMessages(Set<Entry<Client, String>> entrySet) {
		this.set=entrySet;
		messagesText = new Text[set.size()];
		numbersText = new Text[set.size()];
		int i = 0;
		for(Entry<Client, String> entryTemp: set) {
			messagesText[i]=new Text();
			messagesText[i].setText(entryTemp.getValue());
			messagesText[i].setVisible(false);
			smsPage.add(messagesText[i], 0, i+1);
			numbersText[i]=new Text();
			numbersText[i].setText(entryTemp.getKey().getPhone());
			numbersText[i].setVisible(false);
			smsPage.add(numbersText[i], 1, i+1);
			i++;
			
		}

	}
	
	public void setVisible(int i) {
		messagesText[i].setVisible(true);
		numbersText[i].setVisible(true);

	}



}
