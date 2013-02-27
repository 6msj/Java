/* Name: James Nguyen
 * Order class takes care of orders and saves/reads information from/to a file. */
package business;
import java.util.ArrayList;

public class Order {
    protected ArrayList<Integer> quantity;
    protected ArrayList<Item> itemIDs;
    protected int totalPrice;
    protected boolean orderDelivered;
    protected boolean orderPaidFor;
    protected String dateOrdered;
    protected String dateReceived;

    public Order() {
        quantity = new ArrayList<>();
        itemIDs = new ArrayList<>();
        totalPrice = 0;
        orderDelivered = false;
        orderPaidFor = false;
    }

    public String returnForFileFormat() {
        StringBuffer orderInFileFormat = new StringBuffer();
        for(int i=0; i<itemIDs.size(); i++) {
            Item itemString = itemIDs.get(i);
            orderInFileFormat.append(itemString.getID() + " ' ");
            orderInFileFormat.append(itemString.getName() + " ' ");
            orderInFileFormat.append(itemString.getDescription() + " ' ");
            orderInFileFormat.append(itemString.getPrice() + " ' ");
            orderInFileFormat.append(quantity.get(i) + " ' ");
        }
        orderInFileFormat.append(" itemEnd ' "); // append this for reading
        if(orderPaidFor) {
            orderInFileFormat.append(" True\n");
        } else {
            orderInFileFormat.append(" False\n");
        }

        String newString = orderInFileFormat.toString();
        return newString;
    }

    public ArrayList<Item> returnListOfItems() {
        return itemIDs;
    }

    public ArrayList<Integer> returnListQuantity() {
        return quantity;
    }

    public void addItem(Item _item, int _quantity) {
        itemIDs.add(_item);
        quantity.add(_quantity);
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        totalPrice = 0;
        for(int i=0; i<itemIDs.size(); i++) {
            for(int j=0; j<quantity.get(i); j++) {
                Item orderItem = itemIDs.get(i);
                totalPrice += orderItem.getPrice();
            }
        }
    }

    public int getTotalPrice() {
    	updateTotalPrice();
        return totalPrice;
    }

    public void checkOrderDelivered() {
        if(orderPaidFor) {
            orderDelivered = true;
            dateReceived = "3 days after " + dateOrdered;
        }
    }

    public String returnDateRecieved() {
	    return dateReceived;
    }

    public void checkPaidFor(boolean paidFor) {
        orderPaidFor = paidFor;
    }

    public void recordDate(String date) {
        dateOrdered = date;
    }
}
