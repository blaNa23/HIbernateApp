import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class HibernateUtil {

    private static final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("SDAORM");

    private EntityManager em = emf.createEntityManager();

    public EntityManager getEm() {
        if (this.em == null || (this.em != null && !this.em.isOpen())){
            this.em = emf.createEntityManager();
        }

        return this.em;
    }


    public void save(Object e) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(e);
        et.commit();
        em.close();
    }


    public <Entity extends Serializable> List<Entity> readAll(EntityManager manager, Class<Entity> entityClass) {

        List<Entity> entities = null;

        // Create an EntityManager
       // EntityManager manager = emf.createEntityManager();

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(); //=>"SELECT "
        Root<Entity> root = query.from(entityClass); //=> "SELECT  from E"
        query.select(root); // "SELECT * from E e"

        entities = manager.createQuery(query).getResultList();
      //  manager.close();

        return entities;
    }



    public <Entity extends Serializable> List<Entity> findByName(EntityManager manager, Class<Entity> entityClass, String s) {

        List<Entity> entities = null;

        // Create an EntityManager
 //       EntityManager manager = emf.createEntityManager();

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(); //=>"SELECT "
        Root<Entity> root = query.from(entityClass); //=> "SELECT  from E"
        query.select(root); // "SELECT * from E e"

        query.where(builder.equal(root.get("lastName"), s));

        entities = manager.createQuery(query).getResultList();
//        manager.close();

        return entities;
    }

    public <Entity extends Serializable> Entity findByKey(Class<Entity> entityClass, int n) {
        EntityManager em = emf.createEntityManager();
        Entity entity = em.find(entityClass, n);

        return entity;
    }


    public void closeEMF() {
        emf.close();
    }


    public void closeEM() {
        this.em.close();
    }
}
