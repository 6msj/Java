/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


/**
 *
 * @author james
 */
public class Manager {
	private EntityManagerFactory emf;
	private EntityManager em;

    public Manager() {
        // Create EntityManagerFactory for persistent unit named "OOP5DerbyPU"
        emf = Persistence.createEntityManagerFactory("OOP5DerbyPU");
        //hardCodeItemsAndOrders();
        
    }

    private void hardCodeItemsAndOrders() {
        createTransactionalEntityManager(); // start a transaction
        Item item = new Item(12345, "Mouse", "A mouse used for computers.", 20);
        Orders orders = new Orders(123123, 999, "1/1/10", "1/4/10", 10, 10, item);
		Warehouse warehouse = new Warehouse();
		orders.getItems().add(item);
        item.setOrders(orders);
        em.persist(item);
        em.persist(orders);
        em.persist(warehouse);
        closeTransactionalEntityManager(); // commit and close 
    }

    private void createEntityManager() {
        // Create an EntityManager
        em = emf.createEntityManager();
    }

    private void closeEntityManager() {
        // Close this EntityManager
        em.close();
    }

    private void createTransactionalEntityManager() {
        // Create a new EntityManager
        em = emf.createEntityManager();
        // Begin transaction
        em.getTransaction().begin();
    }

    private void closeTransactionalEntityManager() {
        // Commit the transaction
        em.getTransaction().commit();
        // Close the EntityManager
        em.close();
    }

    public List<Warehouse> getSupplierNames() {
        em = emf.createEntityManager();
        Query query = em.createQuery("select SUPPLIER_NAME from Warehouse SUPPLIER_NAME");
        List<Warehouse> result = (List<Warehouse>) query.getResultList();
        em.close();
        return result;
    }

    public List<Item> getItemNames() {
        em = emf.createEntityManager();
        int warehouseid=1;
        Query q = em.createNativeQuery("select * from Item join Warehouse where warehouseid=1");
        List<Item> result = (List<Item>) q.getResultList();
        em.close();
        return result;
    }

    
}
