/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Observer;

public interface Receiver {
	 void update(ShopObservable sender , String message);
}
