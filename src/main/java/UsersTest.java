import bookstore.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UsersTest {
    public static void main(String[] args) {
        System.out.println("Test 1");
        Users user1 = new Users();
        user1.setEmail("Yurii@gmail.com");
        user1.setFullName("logging");
        user1.setPassword("password");

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("BookStoreWebsite");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(user1);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        System.out.println("User was persisted");

    }
}