/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Memento;

public class OriginatorClass implements Originator {
	
	private ProductMemento productMemento;

	public void setProduct(ProductMemento productMemento) {
		this.productMemento = productMemento;
	}

	@Override
	public MementoClass saveMemento() {
		return new MementoClass(productMemento);
	}

	public ProductMemento getProductMemento() {
		return productMemento;
	}

	public void getProductFromMemento(MementoClass memento) {
		productMemento = memento.restoreMemento();
	}

}
