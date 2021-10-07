/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Observer;

public interface ShopObservable {
	
	public void Notify(String message);
	
	public void addObserver(Receiver receiver);
}
