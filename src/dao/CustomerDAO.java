/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.*;
import orm.Customer;

/**
 *
 * @author laurijko
 */
public class CustomerDAO extends DAO {
    
    public CustomerDAO(EntityManager em) {
        super(em);
    }
    
    public List<Object[]> getCustomerList(){
        Query q = em.createQuery("SELECT c.id, c.lastname, c.firstname FROM Customer c");
        List<Object[]> list = q.getResultList();
        return list;
    }
    
    public Customer getCustomerById(int id){
        return em.find(Customer.class, id);
    }
    
    public void addCustomer(Customer customer){
        em.persist(customer);
    }
    
    public void deleteCustomer(Customer customer) {
        em.remove(customer);
    }
}
