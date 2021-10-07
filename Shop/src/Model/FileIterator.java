/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FileIterator implements Iterator<Entry<String, Product>> {
		public enum savingMethod {
			Ascending, Descending , IncomeOrder // change enum to what we have in the view
		}
		private RandomAccessFile raf;
		private Comparator<String> comparator;
		private long previousLocation = 0;
		private boolean didNext = false;
		private savingMethod sortMethod;
	

		public FileIterator(String fileName) throws IOException {
			raf = new RandomAccessFile(new File(fileName), "rw");
			comparator = null;
			sortMethod = null;
			if(hasNext()) {
				String compareString = next().getKey();
				try {
						initComparator(savingMethod.valueOf(compareString));
				} catch (Exception e) {
						closeIterator();
						return;
				}
			}
			else {
				closeIterator(); //file is empty
				return;
			}
		}
		
		//empty file
		public FileIterator(String fileName , savingMethod method) throws IOException {
			raf = new RandomAccessFile(new File(fileName) , "rw");
			initComparator(method);
			writeProduct(new java.util.AbstractMap.SimpleEntry<String,Product>(method.toString(),
					new Product("" , 0 , 0 , new Client("" , "" , false))));
		}
		
		public void initComparator(savingMethod method) {
			setSortMethod(method);
			switch (method) {
			case Ascending:
				comparator = new Comparator<String>() {
					
						public int compare(String arg0 , String arg1) {
							return arg0.compareTo(arg1);
						}
				};
				break;
			case Descending:
				comparator = new Comparator<String>() {

					@Override
					public int compare(String arg0, String arg1) {
						return -arg0.compareTo(arg1);
					}
					
				};
				break;
			case IncomeOrder:
					comparator = null;
			}
		}
		

		@Override
		public boolean hasNext() {
			try {
					return raf.length() - raf.getFilePointer() != 0;
			} catch (IOException e) {
					return false;
			}
		}
		
		public void closeIterator() throws IOException {
			raf.close();
		}
		
		@Override
		public Entry<String, Product> next() {
			if(!hasNext()) 
				return null;
			try {
				previousLocation = raf.getFilePointer();
				didNext = true;
				return readTheProduct();
			} catch (IOException e) {
				return null;
			}
		}
		
		public void deleteSortFromFile() throws IOException {
			raf.seek(0);
			next();
			remove();
		}
	
		public void writeProduct(Entry<String,Product> product) throws IOException {
			raf.seek(0);
			next();
			long currentPosition = raf.getFilePointer();
			while(hasNext()) {
				Entry<String,Product> next = next();
				if(comparator != null && comparator.compare(next.getKey(), product.getKey()) >= 1) {
					break;
				}
				currentPosition = raf.getFilePointer();
			}
			raf.seek(currentPosition);
			byte[] restOfFile = new byte[(int) (raf.length() - raf.getFilePointer())];
			raf.read(restOfFile);
			raf.seek(currentPosition);
			writeTheProduct(product);
			raf.write(restOfFile);
		}
		
		
		//IF WE DELETE PRODUCTS FROM FILE TILL ITS EMPTY, WE HAVE TO RESET THE COMPARATOR AS WELL
		public void remove() {
			if(!didNext) 
				return;
			try {
				byte[] restOfFile = new byte[(int) (raf.length() - raf.getFilePointer())];
				raf.read(restOfFile);
				raf.seek(previousLocation);
				raf.write(restOfFile);
				raf.setLength(raf.getFilePointer());
				didNext = false;
			} catch (IOException e) {
				return;
			}
		}
		
		public void removeProduct(String ID) throws IOException {
			raf.seek(0);
			next();
			didNext = false;
			while(hasNext()) {
				Entry<String , Product> next = next();
				if(next.getKey().equals(ID)) {
					break;
				}
				didNext = false;
			}
			remove();
		}
		
		public void removeAllProduct() throws IOException {
			raf.seek(0);
			next();
			long location = raf.getFilePointer();
			while(hasNext()) {
				next();
				remove();
				raf.seek(location);
			}		
			comparator = null;
		}
		
		public void writeTheProduct(Entry<String,Product> product) throws IOException {		
			raf.writeUTF(product.getKey());
			raf.writeUTF(product.getValue().getName());
			raf.writeInt(product.getValue().getPriceForShop());
			raf.writeInt(product.getValue().getPriceForClient());
			raf.writeUTF(product.getValue().getClient().getName());
			raf.writeUTF(product.getValue().getClient().getPhone());
			raf.writeBoolean(product.getValue().getClient().getSaleUpdates());
		}
		
		
		public Entry<String,Product> readTheProduct() throws IOException {
			String id = raf.readUTF();
			String name = raf.readUTF();
			int priceForShop = raf.readInt();
			int priceForClient = raf.readInt();
			String nameOfClient = raf.readUTF();
			String phoneNumber = raf.readUTF();
			Boolean saleUpdates = raf.readBoolean();
			Client c = new Client(nameOfClient , phoneNumber , saleUpdates);
			Product p = new Product(name , priceForShop , priceForClient , c);
			return new java.util.AbstractMap.SimpleEntry<String,Product>(id,p);
		}
		
		public Comparator<String> getComparator() {
			return comparator;
		}
		
		
		public void writeAllProducts(Map<String,Product> map) throws IOException {

			for(Entry<String,Product> entry : map.entrySet()) {
				writeProduct(entry);
			}
		}
		
		
		public void readAllProducts(Map<String,Product> map){
			//while ((line = raf.readLine()) != null)	
		}

		public void setComparator(Comparator<String> comp) {
			comparator = comp;	
		}
		
		public void setSortMethod(savingMethod sortMethod) {
			this.sortMethod = sortMethod;
		}
		
		public savingMethod getSortMethod() {
			return sortMethod;
		}

}
