package lanceur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.IDao;
import dao.daoJPA;
import metier.Client;
import metier.Conseiller;

public class lanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*Conseiller c1 = new Conseiller();
		c1.setAdresse("rue A");
		c1.setNom("A");
		c1.setPrenom("A");
		c1.setEmail("aaaaaa");
		c1.setPwd("test1");
		c1.setCodePostal("aaaaaaaa");
		c1.setLogin("test1");
		
		
		Client c2= new Client();
		c2.setAdresse("rue B");
		c2.setNom("B");
		c2.setPrenom("B");
		c2.setEmail("bbbbbbbbbb");
		c2.setCodePostal("bbbbbbbbbb");
		c2.setConseiller(c1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("proxibanquev3-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(c1);
		em.persist(c2);
		tx.commit();
		em.close();
		*/
		
		IDao idao = new daoJPA();
		Conseiller c = idao.verificationLogin("test1", "test1");
		System.out.println(c.getLogin() + c.getPwd());
		System.out.println(c);
		
		
		
		
	}

}
