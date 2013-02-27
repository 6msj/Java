package business;

public abstract class Item {
    protected String itemID;
    protected String name;
    protected String description;
    protected int price;

    public void appendToID(String idAppend) {
        // this function appends unique ids for items, with the supplier id being the most general id
        itemID = itemID + idAppend;

    }

    public String getID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
