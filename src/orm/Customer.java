package orm;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private Address billingAddress;
    
    @Id
    @Column(name = "CustomerId")
    @GeneratedValue
    public int getId() {
        return id;
    }

    @Column(name = "Firstname")
    public String getFirstname() {
        return firstname;
    }
    
    @Column(name = "Lastname")
    public String getLastname() {
        return lastname;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBillingAddress(Address address) {
        this.billingAddress = address;
    }
    
}
