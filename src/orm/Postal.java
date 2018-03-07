package orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postal")
class Postal {
    private String postalCode;
    private String postRegion;
    
    @Id
    @Column(name = "PostalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @Column(name = "PostRegion")
    public String getPostRegion() {
        return postRegion;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPostRegion(String postRegion) {
        this.postRegion = postRegion;
    }
    
}
