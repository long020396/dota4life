package BLO;

import entities.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class RoleBLO {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dota4LifePU");
    
    public Role getRoleById(int id) {
        EntityManager em = emf.createEntityManager();
        Role role = null;

        try {
            TypedQuery<Role> query = em.createNamedQuery("Role.findById", Role.class);
            query.setParameter("id", id);
            role = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return role;
    }
    
    public Role getRoleByName(String name) {
        EntityManager em = emf.createEntityManager();
        Role role = null;

        try {
            TypedQuery<Role> query = em.createNamedQuery("Role.findByRoleName", Role.class);
            query.setParameter("roleName", name);
            role = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return role;
    }
    
    public int add(Role role) {
        EntityManager em = emf.createEntityManager();

        try {
            /* Set default a number */
            role.setId(0);
            
            em.getTransaction().begin();
            em.persist(role);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return role.getId();
    }
    
}
