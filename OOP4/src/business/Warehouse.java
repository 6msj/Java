package business;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Warehouse {
    protected ArrayList<Item> itemListBluePrint; // the same ItemList as what the Supplier has
    protected HashMap<String, ArrayList<Item>> warehouseInventory; // location of item, is the index of where it is
    protected Brain __brain__;
    protected String supplierName;
    Random randomGenerator;

    public Warehouse(String __Name__) {
        supplierName = __Name__;
        __brain__ = new Brain(supplierName); // every warehouse uses a brain
        randomGenerator = new Random();
        warehouseInventory = new HashMap<>();
        itemListBluePrint = new ArrayList<>();
        itemListBluePrint = __brain__.getItemList(); // get the item list of the supplier from the brain
        readInventoryInformation();
        for(int itemsSupplier=0; itemsSupplier<itemListBluePrint.size(); itemsSupplier++) {
            String keyValue = itemListBluePrint.get(itemsSupplier).getID(); // use the original itemID to be the key for the hashmap
            warehouseInventory.put(keyValue, fillWarehouseStocks(itemListBluePrint.get(itemsSupplier)));
        }
        itemListBluePrint = __brain__.getItemList();

    }

    public ArrayList<Item> fillWarehouseStocks(Item item) {
        // Requirement 2B: Store the items in your warehouse. You have item ID's defined for the warehouse.
        int stocks = randomGenerator.nextInt(15); // the stocks in the warehouse are random, limited to 15
        ArrayList<Item> stocksOfOneItem = new ArrayList<Item>(); // contains an array of one item, quantity determined randomly
        for(int i=0; i<stocks; i++) {
        	SupplierItem actualItem = new SupplierItem ((SupplierItem) item);
            String appendID = Integer.toString(i); // get a string from i
            actualItem.appendToID(appendID);
            stocksOfOneItem.add(actualItem);
        }
        return stocksOfOneItem;
    }

    public ArrayList<Item> recreateWarehouseStocks(Item item, int quantity) {
        ArrayList<Item> recreatedStockOfOneItem = new ArrayList<Item>();
        for(int i=0; i<quantity; i++) {
            Item actualItem = item;
            String appendID = Integer.toString(i);
            actualItem.appendToID(appendID);
            recreatedStockOfOneItem.add(actualItem);
        }
        return recreatedStockOfOneItem;
    }

    public void printCurrentItemsInInventory() {
        for(int i=0; i<itemListBluePrint.size(); i++) {
            String keyValue = itemListBluePrint.get(i).getID();
            printSetOfItems(Integer.toString(i + i + i + i), warehouseInventory.get(keyValue));
        }
    }

    public void printSetOfItems(String Location, ArrayList<Item> setItems) {
        // Requirement 3B: You also have to identify the location within your warehouse where the item is actually stored.
        // Support printing out a list of the current items you have in your inventory,
        // including how much of each item you have, plus the locations of the items.
        Item item = setItems.get(0);
        System.out.println("There are " + setItems.size() + " of " + item.getName() + " in the inventory. ");
        System.out.print("They are ");
        System.out.println(item.getID() + " located in " + Location + "0");
        for(int i=1; i<setItems.size(); i++) {
            item = setItems.get(i); // get a new unique item
            System.out.println(item.getID() + " located in " + Location + Integer.toString(i));
        }
    }

    public void addOrderUsingBrain(Order order) {
        __brain__.addOrder(order);
    }

    public ArrayList<Order> useBrainToGetOrders() {
        __brain__.retrieveOrderList();
        return __brain__.getOrderList();
    }

    public ArrayList<Item> useBrainToGetItemList() {
    	return itemListBluePrint;
    }

    public void saveInventoryInformation() {
        // Requirement 4B : Save the inventory information for the next run of the program.
        // use itemlistblueprint, take quantity from hashmap
        File file = new File(supplierName + "inventory.txt"); // open file for writing
        FileWriter filewriter;
        BufferedWriter bufferedwriter;
        if (!file.exists()) {
		    try {
			    file.createNewFile();
			    filewriter = new FileWriter(file.getAbsoluteFile());
			    bufferedwriter = new BufferedWriter(filewriter);
			    String content = returnForFileFormat();
			    bufferedwriter.write(content);
			    bufferedwriter.close();
		    } catch (IOException ex) {
			    System.out.println("Warehouse: Couldn't open Inventory Information.");
			    Logger.getLogger(Warehouse.class.getName()).log(Level.SEVERE, null, ex);
		    }
        } 
    }

    public String returnForFileFormat() {
        StringBuffer orderInFileFormat = new StringBuffer();
        for(int i=0; i<itemListBluePrint.size(); i++) {
            Item item = itemListBluePrint.get(i);
            orderInFileFormat.append(item.getID() + " ' ");
            orderInFileFormat.append(item.getName() + " ' ");
            orderInFileFormat.append(item.getDescription() + " ' ");
            orderInFileFormat.append(item.getPrice() + " ' ");
            int itemQuantity = returnItemQuantity(item.getID());
            orderInFileFormat.append(Integer.toString(itemQuantity) + "'");
        }
        String newString = orderInFileFormat.toString();
        return newString;
    }

    public int returnItemQuantity(String keyValue) {
        return warehouseInventory.get(keyValue).size(); // the quantity of one item given a keyValue
    }

    public void readInventoryInformation() {
		InputStream input;
        Scanner newScanner;
        Scanner sc; 

		// if the file is not found, use the default file packaged in
		try {
			newScanner = new Scanner(new File(supplierName + "inventory.txt"));
		} catch (FileNotFoundException ex) {
			input = getClass().getClassLoader().getResourceAsStream(supplierName + "inventory.txt");
			newScanner = new Scanner(input);
		}

		sc = newScanner.useDelimiter("\\s*\'\\s*"); // use a single quote to delimit
        while(sc.hasNext()) {
            String keyValue = sc.next();
            String itemID = keyValue;
            String itemName = sc.next();
            String itemDesc = sc.next();
            int itemPrice = sc.nextInt();
            int itemQuantity = sc.nextInt();
            Item oldItem = new SupplierItem (itemID, itemName, itemDesc, itemPrice);
            ArrayList<Item> recreatedInventory = recreateWarehouseStocks(oldItem, itemQuantity);
            warehouseInventory.put(keyValue, recreatedInventory);
        }
        newScanner.close();
        sc.close();
    }
}
