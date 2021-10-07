
package Observer;

public interface ShopObservable {
	
	public void Notify(String message);
	
	public void addObserver(Receiver receiver);
}
