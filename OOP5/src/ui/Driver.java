package ui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import business.persistence.Item;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.persistence.Manager;
import business.persistence.Warehouse;
import java.util.List;

/**
 *
 * @author james
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public Manager manager;

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.manager = new Manager();
        int warehouseid = driver.letUserPickSupplier(); // get the supplier
        driver.displayerItemSupplier(warehouseid); // display the items from that supplier
    }

    public int letUserPickSupplier() {
        List<Warehouse> supplierNames = manager.getSupplierNames();
        System.out.println("Enter a number to pick a supplier.");
        Scanner reader = new Scanner(System.in);
        for(int i=0; i<supplierNames.size(); i++) {
            System.out.print(i+1 + ":");
            System.out.println(supplierNames.get(i).getSupplierName());
        }
        int warehouseid = reader.nextInt();
        reader.close();
        return warehouseid;
    }

    public void displayerItemSupplier(int warehouseid) {
        List<Item> supplierItems = manager.getItemNames();
        System.out.println("These are the items from the supplier that you picked.");
        for(int i=0; i<supplierItems.size(); i++) {
            System.out.println(supplierItems.get(i).getName());
        }
    }
}
