package BLO;

import entities.RoleOfHero;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RoleOfHeroBLO {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dota4LifePU");
    
    public int add(RoleOfHero roleOfHero) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(roleOfHero);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return roleOfHero.getId();
    }
}
