/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.*;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 *
 * @author james
 */
@Entity
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;
    private int id;
    private String supplierName;
    private Collection<String> location = new ArrayList<>();

	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="WAREHOUSE_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Column(name="SUPPLIER_NAME")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Column(name="ITEM_LOCATION")
    public Collection<String> getLocation() {
        return location;
    }

    public void setLocation(Collection<String> newLocation) {
        this.location = newLocation;
    }

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) id;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Warehouse)) {
			return false;
		}
		Warehouse other = (Warehouse) object;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "business.persistence.Warehouse[ id=" + id + " ]";
	}
	
}
