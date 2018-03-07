package orm;

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

@Entity
@Table(name = "customervisit")
public class CustomerVisit {
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
    public Date getDatetime() {
        return datetime;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Customer getCustomer() {
        return customer;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Address getAddress() {
        return address;
    }
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "customervisit_pest",
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
    
}
