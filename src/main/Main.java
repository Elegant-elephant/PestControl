package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pestcontrol");
    static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        
        
        List<String> valinnat = Arrays.asList("Lisää", "Poista", "Lopeta");
        Scanner lukija = new Scanner(System.in);
        String valinta = "";
        do {
            for (int i = 0; i < valinnat.size(); i++) {
                System.out.println((i+1) + ". " + valinnat.get(i));
            }
            
            valinta = lukija.nextLine();
        } while (!valinta.equals("" + (valinnat.size())));
        
        em.close();
        emf.close();
    }
    
}
