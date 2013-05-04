/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial;
import entity.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author james
 */
public class Demo {
    public static void main(String args[]){
        Person p = new Person();
        p.setName("Hendro Steven");
        p.setAddress("Salatiga, Indonesia");
        p.setPhoneNumber("+6281390989669");

        Demo demo = new Demo();
        demo.persist(p); 
    }
    
    public void persist(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TutorialPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
    
    
