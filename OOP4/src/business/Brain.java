/* Name: James Nguyen
 * Brain class is the logic behind the warehouse class. */
package business;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Brain {
    private ArrayList<Item> itemList;
    private ArrayList<Order> orderList;
    private String __fileName__;

    public Brain(String __Name__) {
        __fileName__ = __Name__;
        itemList = new ArrayList<>();
	InputStream input; 
	Scanner newScanner;
	Scanner sc;

	try {
		newScanner = new Scanner(new File(__fileName__ + ".txt"));
	} catch (FileNotFoundException ex) {
		input = getClass().getClassLoader().getResourceAsStream(__fileName__ + ".txt"); // if no file found, use the default file loaded inside the jar
		newScanner = new Scanner(input);	
	}
	

	sc = newScanner.useDelimiter("\\s*\'\\s*"); // use a single quote to delimit
	while(sc.hasNext()) {
	    String itemID = sc.next();
	    String itemName = sc.next();
	    String itemDesc = sc.next();
	    int itemPrice = sc.nextInt();
	    Item newItem = new SupplierItem(itemID, itemName, itemDesc, itemPrice);
	    itemList.add(newItem); // each supplier holds an arraylist of its own items
	}
		newScanner.close();
        sc.close();
		retrieveOrderList(); 
    }

    public void retrieveOrderList() {
        if(orderList != null) {
		    return;
		} // if not null, orderList was already retrieved
        
        orderList = new ArrayList<>();
		InputStream input;
        Scanner newScanner;
		Scanner sc;

		try {
			newScanner = new Scanner(new File(__fileName__ + "Orders.txt"));
		} catch (FileNotFoundException ex) {
			input = getClass().getClassLoader().getResourceAsStream(__fileName__ + "Orders.txt");
			newScanner = new Scanner(input);
		}

		sc = newScanner.useDelimiter("\\s*\'\\s*"); // use a single quote to delimit
        boolean foundEndString = false; // there's an end string in the file to signify when the file's items end

        Order retrievedOrder = new Order();
        while(sc.hasNext()) {

            String itemID = sc.next();
            while(!foundEndString) {
                String itemName = sc.next();
                String itemDesc = sc.next();
                int itemPrice = sc.nextInt();
                int itemQuantity = sc.nextInt();
                Item newItem = new SupplierItem(itemID, itemName, itemDesc, itemPrice);
                String wordCheck = sc.next();
                
                if(wordCheck.equals("itemEnd")) {
                    foundEndString = true;
                    //System.out.println("found itemEnd"); // for debugging
                } else {
                    itemID = wordCheck; 
                }

                retrievedOrder.addItem(newItem, itemQuantity);
            }
            String __PaidFor = sc.next();
            //System.out.println("debug;;");
            boolean orderPaidFor = checkTrue(__PaidFor);

            retrievedOrder.checkPaidFor(orderPaidFor);
            retrievedOrder.checkOrderDelivered();
            orderList.add(retrievedOrder);
        }

        newScanner.close();
        sc.close();
    }

    protected boolean checkTrue(String checkString) {
        // after reading the file, program needs to check for the meaning
        // of true or false
        if( "True".equals(checkString)) {
            return true;
        } else {
            // else == "False"
            return false;
        }
    }

    protected ArrayList<Item> getItemList() {
        return itemList;
    }

    protected ArrayList<Order> getOrderList() {
        return orderList;
    }

    protected void addOrder(Order order) {
        orderList.add(order);
    }
}


