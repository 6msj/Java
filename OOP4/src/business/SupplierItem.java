/* Name: James Nguyen
 * Extends from Item class, creates a way to instantiate an item. */
package business;

class SupplierItem extends Item {
    public SupplierItem (String __itemId, String __name, String __description, int __price) {
        itemID = __itemId;
        name = __name;
        description = __description;
        price = __price;
    }
    
    public SupplierItem(SupplierItem another) {
        this.itemID = another.itemID; // you can access  
        this.name = another.name;
        this.description = another.description;
        this.price = another.price;
      }

}
