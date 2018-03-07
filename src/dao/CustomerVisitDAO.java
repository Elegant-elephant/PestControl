/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
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

    public CustomerVisit[] getVisits(){
        em.createQuery("SELECT cv FROM CustomerVisit cv");
        return null;
    }
    
    public CustomerVisit getVisitById(int id){
        Query q = em.createQuery("SELECT cv FROM CustomerVisit cv WHERE cv.id = :id");
        q.setParameter("id", id);
        return null;
    }
}
