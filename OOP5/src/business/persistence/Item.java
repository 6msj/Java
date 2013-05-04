package business.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

/**
 *
 * @author james
 */
@Entity
public class Item implements Serializable {
    private static final int serialVersionUID = (int) 1L;
    private int id;
    private String name;
    private String description;
    private int price;
    private Orders orders;
    private int warehouseid;

    public Item() {
        id = 12345;
        name = "Default Item";
        description = "Default Description";
        price = 12345;
    }
    
    public Item(int _id, String _name, String _description, int _price) {
        this.id = _id;
        this.name = _name;
        this.description = _description;
        this.price = _price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    @ManyToOne()
    @JoinColumn(name="ORDER_ID")
    public Orders getOrders() {
        return orders;
    }
    
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Column(name="WAREHOUSE_ID")
    public int getWarehouseId() {
        return warehouseid;
    }

    public void setWarehouseId(int warehouseid) {
        this.warehouseid = warehouseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
	hash += (id != 0 ? System.identityHashCode(id) : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == 0 && other.id != 0) || (this.id != 0 && !(this.id == other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.persistence.Item[ id=" + id + " ]";
    }

    
}
