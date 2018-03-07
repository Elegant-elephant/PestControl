package main;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
class Address {
    private String streetAddress;
    private Postal postalCode;
    
    @Id
    @Column(name = "StreetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Postal getPostalCode() {
        return postalCode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(Postal postalCode) {
        this.postalCode = postalCode;
    }    
}
