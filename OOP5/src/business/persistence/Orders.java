/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.*;

/**
 *
 * @author james
 */
@Entity
public class Orders implements Serializable {
    private static final int serialVersionUID = (int) 1L;
    private int id;
    private int warehouse_id;
    private String dateMade;
    private String dateReceived;
    private int quantity;
    private int total;
    private Collection<Item> items = new ArrayList<>();

    public Orders() {
        
    }
    
    public Orders(int id, int warehouse_id, String dateMade, String dateReceived, int quantity, int total, Item item) {
        this.id = id;
        this.warehouse_id = warehouse_id;
        this.dateMade = dateMade;
        this.dateReceived = dateReceived;
        this.quantity = quantity;
        this.total = total;
	//items.add(item);
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="ORDER_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name="WAREHOUSE_ID")
    public int getWareHouseID() {
        return warehouse_id;
    }
    
    public void setWareHouseID(int warehouseid) {
        this.warehouse_id = warehouseid;
    }
    
    @Column(name="DATE_ORDERED")
    public String getDateMade() {
        return dateMade;
    }
    
    public void setDateMade(String dateMade) {
        this.dateMade = dateMade;
    }

    @Column(name="DATE_RECEIVED")
    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Column(name="QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name="TOTAL")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @OneToMany(cascade=ALL, mappedBy="orders")
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> newItem) {
        this.items = newItem;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
	hash += (id != 0 ? System.identityHashCode(id) : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == 0 && other.id != 0) || (this.id != 0 && !(this.id == other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.persistence.Order[ id=" + id + " ]";
    }
    
}
