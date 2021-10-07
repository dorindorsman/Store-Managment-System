/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Memento;

public class MementoClass implements Memento{
	private ProductMemento productMemento;
	
	public MementoClass(ProductMemento productMemento) {
		this.productMemento = productMemento;
	}
	@Override
	public ProductMemento restoreMemento() {
		return productMemento;
	}

}
