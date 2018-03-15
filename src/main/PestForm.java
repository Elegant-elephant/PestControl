/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.PestDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import orm.Pest;

/**
 *
 * @author royja
 */
public class PestForm implements FormIF<Pest> {
    private PestDAO dao;
    private Scanner lukija;

    public PestForm(EntityManager em) {
        this.lukija = new Scanner(System.in);
        this.dao = new PestDAO(em);
    }
    
    @Override
    public Pest create() {
        Pest pest = new Pest();
        
        System.out.println("Anna nimi:");
        String name = lukija.nextLine();
        pest.setName(name);
        
        System.out.println("Syötä kuvaus tai jätä tyhjäksi:");
        String description = lukija.nextLine();
        if (!description.equals("")) {
            pest.setDescription(description);
        }
        
        dao.addPest(pest);
        System.out.println("Luotu: " + pest);
        
        return pest;
    }

    @Override
    public void delete(Pest pest) {
        dao.deletePest(pest);
    }

    @Override
    public List<Pest> getList() {
        return dao.getPestList();
    }

    @Override
    public Pest update(Pest pest) {
        System.out.println("Ei toteutusta vielä");
        // TODO: Add pest editing menu
        return pest;
    }
    
    public Set<Pest> update(Set<Pest> pests) {
        System.out.println("Ei toteutusta vielä");
        // TODO: Add pest set editing menu
        return pests;
    }
    
}
