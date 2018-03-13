package orm;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CustomerVisit")
public class CustomerVisit implements Serializable {
    private int id;
    private Date datetime;
    private Customer customer;
    private Address address;
    private Set<Pest> pests;
    
    @Id
    @GeneratedValue
    @Column(name = "CustomerVisitId")
    public int getId() {
        return id;
    }

    @Column(name = "Datetime")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatetime() {
        return datetime;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Customer")
    public Customer getCustomer() {
        return customer;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Address")
    public Address getAddress() {
        return address;
    }
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "CustomerVisitPest",
        joinColumns=@JoinColumn(name = "CustomerVisitId", referencedColumnName = "CustomerVisitId"),
        inverseJoinColumns=@JoinColumn(name = "PestId", referencedColumnName = "PestId"))
    public Set<Pest> getPests() {
        return pests;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setPests(Set<Pest> pests) {
        this.pests = pests;
    }
    
    @Override
    public String toString() {
        String pestString = "";
        for (Pest pest : pests) {
            if (!pestString.equals("")) pestString += ", ";
            pestString += "{" + pest + "}";
        }
        return id + ", " + datetime + ", " + "Customer: {" + customer + "}, Address: {" + address + "}, Pests: [" + pestString + "]";
    }
}
