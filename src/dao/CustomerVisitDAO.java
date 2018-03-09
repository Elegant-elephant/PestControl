/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import orm.CustomerVisit;
/**
 *
 * @author laurijko
 */
public class CustomerVisitDAO extends DAO{

    public CustomerVisitDAO(EntityManager em) {
        super(em);
    }

    public List<CustomerVisit> getVisits(){
        Query q = em.createQuery("SELECT cv FROM CustomerVisit cv");
        List<CustomerVisit> visits = q.getResultList();
        return visits;
    }
    
    public CustomerVisit getVisitById(int id){
        return em.find(CustomerVisit.class, id);
    }
    
    public void addCustomerVisit(CustomerVisit cv) {
        em.persist(cv);
    }
    
    public void delete(CustomerVisit cv){
        em.remove(cv);
    }
}
