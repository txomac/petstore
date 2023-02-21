package epsi.petstore;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import epsi.petstore.entities.*;
import epsi.petstore.entities.enumtype.FishLivEnv;
import epsi.petstore.entities.enumtype.ProdType;

import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product1 = new Product("1337", "croquette", ProdType.ACCESSORY, 15.00);
        Product product2 = new Product("1338", "friandises", ProdType.FOOD, 18.00);
        Product product3 = new Product("0667", "produit", ProdType.CLEANING, 10.00);

        Address address1 = new Address("05", "rue de la tortue", "01337", "Nantes");
        Address address2 = new Address("06", "avenue des chats", "01373", "Nantes");
        Address address3 = new Address("07", "avenue des chiens", "06670", "Nantes");
        PetStore petStore1 = new PetStore("AnimalStore1", "Thomas Henaff", address1);
        petStore1.addProduct(product1);
        petStore1.addProduct(product2);
        petStore1.addProduct(product3);

        PetStore petStore2 = new PetStore("AnimalStore2", "Jeff Bezoz", address2);
        petStore2.addProduct(product1);
        petStore2.addProduct(product2);
        petStore2.addProduct(product3);

        PetStore petStore3 = new PetStore("AnimalStore3", "Elon Musk", address3);
        petStore3.addProduct(product1);
        petStore3.addProduct(product2);
        petStore3.addProduct(product3);

        Animal animal1 = new Animal(LocalDate.now(), "brun", petStore1);
        Cat cat1 = new Cat(LocalDate.now(), "brun", petStore1, "chipid");
        Fish fish1 = new Fish(LocalDate.now(), "brun", petStore1, FishLivEnv.SEA_WATER);

        Animal animal2 = new Animal(LocalDate.now(), "roux", petStore2);
        Cat cat2 = new Cat(LocalDate.now(), "roux", petStore2, "chipid");
        Fish fish2 = new Fish(LocalDate.now(), "roux", petStore2, FishLivEnv.FRESH_WATER);

        Animal animal3 = new Animal(LocalDate.now(), "blond", petStore3);
        Cat cat3 = new Cat(LocalDate.now(), "blond", petStore3, "chipid");
        Fish fish3 = new Fish(LocalDate.now(), "blond", petStore3, FishLivEnv.FRESH_WATER);


        em.persist(product1);
        em.persist(product2);
        em.persist(product3);

        em.persist(petStore1);
        em.persist(petStore2);
        em.persist(petStore3);

        em.persist(animal1);
        em.persist(cat1);
        em.persist(fish1);
        em.persist(animal2);
        em.persist(cat2);
        em.persist(fish2);
        em.persist(animal3);
        em.persist(cat3);
        em.persist(fish3);


        em.getTransaction().commit();

        Query q = em.createQuery("select id, birth, color from Animal where petStore.id = 1");
        List resultList = q.getResultList();
        System.out.println("num of animals:" + resultList.size());
        for (Object next : resultList) {
            System.out.println("next animal: " + next);
        }

        em.close();
        emf.close();
    }
}
