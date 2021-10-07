
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
