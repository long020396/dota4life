package BLO;

import entities.Attribute;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AttributeBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dota4LifePU");

    public Attribute getAttributeById(int id) {
        EntityManager em = emf.createEntityManager();
        Attribute attribute = null;

        try {
            TypedQuery<Attribute> query = em.createNamedQuery("Attribute.findById", Attribute.class);
            query.setParameter("id", id);
            attribute = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return attribute;
    }

    public Attribute getAttributeByName(String name) {
        EntityManager em = emf.createEntityManager();
        Attribute attribute = null;

        try {
            TypedQuery<Attribute> query = em.createNamedQuery("Attribute.findByAttributeName", Attribute.class);
            query.setParameter("attributeName", name);
            attribute = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return attribute;
    }

    public int add(Attribute attribute) {
        EntityManager em = emf.createEntityManager();

        try {
            /* Set default a number */
            attribute.setId(0);
            
            em.getTransaction().begin();
            em.persist(attribute);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return attribute.getId();
    }
    
}
