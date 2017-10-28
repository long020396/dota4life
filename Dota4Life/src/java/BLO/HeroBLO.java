package BLO;

import DTO.HeroListDTO;
import DTO.SimplifiedHeroDTO;
import entities.Hero;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import utilities.Utils;

public class HeroBLO {

    @Resource(name = "jdbc/ds_demo")
    private DataSource ds;

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

    public void update(Hero hero) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Hero blo = em.find(Hero.class, hero.getId());

            blo.setRoleOfHeroList(hero.getRoleOfHeroList());
//            blo.setSkillList(hero.getSkillList());
            blo.setBadAgainstIDs(hero.getBadAgainstIDs());
            blo.setGoodAgainstIDs(hero.getGoodAgainstIDs());

            em.persist(blo);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public HeroListDTO getAllHeroes() throws SQLException {
//        EntityManager em = emf.createEntityManager();
//        try {
//            HeroListDTO heroListDTO = new HeroListDTO();
//            List<Hero> result = new ArrayList<Hero>();
//            TypedQuery<Hero> query = em.createNamedQuery("Hero.findAll", Hero.class);
//            for (Hero hero : query.getResultList()) {
//                result.add(hero);
//            }
//            heroListDTO.setHero(result);
//            return heroListDTO;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        return null;

        Connection conn = null;
        Statement stmt = null;

        SimplifiedHeroDTO spfHeroDTO = null;
        List<SimplifiedHeroDTO> result = null;
        HeroListDTO heroListDTO = null;
        try {
            conn = Utils.getDBConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, Name, Img, Attribute FROM Hero");

            while (rs.next()) {
                spfHeroDTO = new SimplifiedHeroDTO(rs.getInt("ID"), rs.getString("Name"), rs.getString("Img"), rs.getString("Attribute"));

                if (result == null) {
                    result = new ArrayList<SimplifiedHeroDTO>();
                }
                result.add(spfHeroDTO);
            }
            heroListDTO = new HeroListDTO();
            heroListDTO.setHero(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return heroListDTO;
    }

    public HeroListDTO getHeroesByMultipleIds(String ids) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        SimplifiedHeroDTO spfHeroDTO = null;
        List<SimplifiedHeroDTO> result = null;
        HeroListDTO heroListDTO = null;
        try {
            conn = Utils.getDBConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, Name, Img, Attribute FROM Hero WHERE ID IN (" + ids.replaceAll(";", ",") + ")");
            while (rs.next()) {
                spfHeroDTO = new SimplifiedHeroDTO(rs.getInt("ID"), rs.getString("Name"), rs.getString("Img"), rs.getString("Attribute"));

                if (result == null) {
                    result = new ArrayList<SimplifiedHeroDTO>();
                }
                result.add(spfHeroDTO);
            }
            heroListDTO = new HeroListDTO();
            heroListDTO.setHero(result);
        } catch (Exception e) {
            e.printStackTrace();;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return heroListDTO;
    }

    public SimplifiedHeroDTO getSimplifiedHeroById(int id) {
        Connection conn = null;
        Statement stmt = null;

        SimplifiedHeroDTO spfHeroDTO = null;
        try {
            conn = Utils.getDBConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, Name, Img, Attribute FROM Hero WHERE ID=" + id);

            while (rs.next()) {
                spfHeroDTO = new SimplifiedHeroDTO(rs.getInt("ID"), rs.getString("Name"), rs.getString("Img"), rs.getString("Attribute"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return spfHeroDTO;
    }
}
