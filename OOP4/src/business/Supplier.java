/* Name: James Nguyen
 * Supplier class uses warehouse class to do most of its work. */
package business;

import java.util.ArrayList;

public class Supplier {
    protected String __Name__; // name of supplier
    protected Warehouse __warehouse__;

    public Supplier(String supplierName) {
        __Name__ = supplierName;
        __warehouse__ = new Warehouse(__Name__);
    }

    public String getName() {
        return __Name__;
    }

    public ArrayList<Item> getItemsInInventory() {
        return __warehouse__.useBrainToGetItemList();
    }

    public ArrayList<Order> callBrainToGetOrders() {
        return __warehouse__.useBrainToGetOrders();
    }

    public void addOrderToBrain(Order order) {
        __warehouse__.addOrderUsingBrain(order);
    }
}
