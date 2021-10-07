# Stroe-Managment-System
A program that uses javafx to manage the system according to a template MVC and uses design patterns.

#System description:
All operations on the system that run at the user's request will be performed according to the Command template.
The system will manage the products sold in the store. 
Each product has the following features: 
Product name, 
Price of product cost per store( int)
Sales price per customer (int)
(Price can't be negative and the type is int. if there is no price has been entered, so the price is 0.)

A customer type object — which contains the name of the customer who purchased the product, their mobile number, and whether they want to receive updates on promotions. (I mean there is also a class for a client)
The system will allow: product reception. Each product will be absorbed in appropriate boxes, with each product also entering a string type ID. If the same ID is re-entered, the new details will update the same product that already exists in the system. The input boxes will be used only to receive data. Do not use them to display already absorbed data or other results.

Receives the various product data into Map objects, with only the product ID always used as the key. the system will support the following options as the user chooses: 

-Saves the products in Map in ascending order (Alpha Homes) according to the ID.

-Saves the products in Map descending order (Alpha Homes) according to the ID. 

-Saves the products in Map in the order of the products were enter. 

Any option once selected is valid until the program is finished.

Display the details of a product according to its ID. View all products that exist so far in the system as saved in Map.

When each product is checked in, all data in Map has is automatically downloaded to a binary file named " products. txt" so that the file displays the update map status.  

With the Memento template, you can restore map mode before you add the last product, and automatically update the contents of the file. Only one cancellation is supported, i.e. only the last product at a time.

According to the Iterator format, a File Iterator is created that will work only on the file itself, and, according to the user's request, will support the following options:

- Read the contents of the file back to the corresponding Map(i.e. by activating the next method of the iterator, etc.). From there it will be possible to continue the work as usual, for example displaying map content.
Delete will be done by iterator in the file itself, without the use of any collection reference array or accessory, etc. that is, records must be moved in the file itself, 
etc. Do not copy to a new file or use an accessory file. After, the system will automatically read the file back to Map using the iterator as described below.

- Delete all products from the file. (by activating a remove of the iterator on all products in the file.) After deletion, the Map will be updated accordingly.

When the system upload, if there is data in the file, it automatically reads it to the corresponding Map, (depending on the map from which they were saved), and the system allows for continued working properly from the same point as the products were saved to the file. The data from the file is always read, as mentioned above, only with the File Iterator. 

The system will allow / display the profit of the store for each product, and the total profit from all products.

Using the Observer template, the system will allow the user to send promotional notifications to all customers who were interested in it (see Set the client type above). 
In an automated response to each message, each such customer will send their name back to the sender. (As a receipt confirmation) 
Using the Singleton template, you must allow only one or more creation of a sender type object and create it using the template.

The system will allow, at the user's request, to print the names of the customers who have approved, in such a way that the names will be printed with a delay of 2 seconds between them. Make sure that the panel doesn't hang during printing, and you can continue working normally, which means working with the thread.


