package orm;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {
    private int id;
    private String streetAddress;
    private Postal postalCode;
    
    @Id
    @GeneratedValue
    @Column(name = "AddressId")
    public int getId() {
        return id;
    }
    
    @Column(name = "StreetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PostalCode")
    public Postal getPostalCode() {
        return postalCode;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(Postal postalCode) {
        this.postalCode = postalCode;
    }
    
    @Override
    public String toString() {
        return id + ", " + streetAddress + ", " + "Postal: {" + postalCode + "}";
    }
}
