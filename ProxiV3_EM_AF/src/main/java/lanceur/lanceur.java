package lanceur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import metier.Client;
import metier.Conseiller;


public class lanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conseiller c1 = new Conseiller();
		
		c1.setNom("Robichet");
		c1.setPrenom("Robert");
		c1.setAdresse("24 rue du Chène");
		c1.setCodePostal("38420");
		c1.setVille("Le Versoud");
		c1.setTelephone("0478458596");
		c1.setLogin("demo1");
		c1.setPwd("demo1");
		
		Conseiller c2 = new Conseiller();
		c2.setNom("Patoulatchi");
		c2.setPrenom("Marcel");
		c2.setAdresse("5 avenue du Chateau");
		c2.setCodePostal("45789");
		c2.setVille("Ville sur marne");
		c2.setTelephone("0745859632");
		c2.setLogin("demo2");
		c2.setPwd("demo2");
		

		Client cl2= new Client();
		cl2.setNom("Smithdfsf");
		cl2.setPrenom("John");
		cl2.setAdresse("route du chemin");
		cl2.setCodePostal("78852");
		cl2.setVille("Lille");
		cl2.setTelephone("014458712");
		cl2.setEntreprise(false);
		cl2.setNomEntreprise(null);
		cl2.setEmail("john.smith@test.com");
		cl2.setConseiller(c1);
		
		
		Client cl3= new Client();
		cl3.setNom("Smithdfsf");
		cl3.setPrenom("John");
		cl3.setAdresse("route du chemin");
		cl3.setCodePostal("78852");
		cl3.setVille("Lille");
		cl3.setTelephone("014458712");
		cl3.setEntreprise(false);
		cl3.setNomEntreprise(null);
		cl3.setEmail("john.smith@test.com");
		cl3.setConseiller(c1);


		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("proxibanquev3-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(c1);
		em.persist(c2);
		tx.commit();
		em.close();
		
		

		
		
		
		
	}

}
