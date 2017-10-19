package BLO;

import entities.Hero;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class HeroBLO {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dota4LifePU");
    
    public Hero getHeroById(int id) {
        EntityManager em = emf.createEntityManager();
        Hero hero = null;

        try {
            TypedQuery<Hero> query = em.createNamedQuery("Hero.findById", Hero.class);
            query.setParameter("id", id);
            hero = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return hero;
    }
    
    public Hero getHeroByName(String name) {
        EntityManager em = emf.createEntityManager();
        Hero hero = null;

        try {
            TypedQuery<Hero> query = em.createNamedQuery("Hero.findByHeroName", Hero.class);
            query.setParameter("heroName", name);
            hero = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return hero;
    }
    
    public int add(Hero hero) {
        EntityManager em = emf.createEntityManager();

        try {
            /* Set default a number */
            hero.setId(0);
            
            em.getTransaction().begin();
            em.persist(hero);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return hero.getId();
    }
    
}
