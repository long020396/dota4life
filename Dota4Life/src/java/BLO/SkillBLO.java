package BLO;

import entities.Skill;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SkillBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dota4LifePU");

    public Skill getSkillById(int id) {
        EntityManager em = emf.createEntityManager();
        Skill role = null;

        try {
            TypedQuery<Skill> query = em.createNamedQuery("Skill.findById", Skill.class);
            query.setParameter("id", id);
            role = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return role;
    }

    public Skill getSkillByName(String id) {
        EntityManager em = emf.createEntityManager();
        Skill role = null;

        try {
            TypedQuery<Skill> query = em.createNamedQuery("Skill.findById", Skill.class);
            query.setParameter("id", id);
            role = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return role;
    }

    public int add(Skill skill) {
        EntityManager em = emf.createEntityManager();

        try {
            /* Set default a number */
            skill.setId(0);

            em.getTransaction().begin();
            em.persist(skill);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return skill.getId();
    }
}
