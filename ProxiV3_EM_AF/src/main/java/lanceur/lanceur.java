package lanceur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.Conseiller;
import service.IServiceCryptageDecryptgage;
import service.Services;

public class lanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
 * 

		IServiceCryptageDecryptgage iscd = new Services();
		
		Conseiller c1 = new Conseiller();
		c1.setNom("Robichet");
		c1.setPrenom("Robert");
		c1.setAdresse("24 rue du Chène");
		c1.setCodePostal("38420");
		c1.setVille("Le Versoud");
		c1.setTelephone("0478458596");
		c1.setLogin("demo1");
		c1.setPwd("demo1");
		String pwdvrai1 = iscd.encrypt(c1.getPwd(), c1.getLogin());
		c1.setPwd(pwdvrai1);
		
		

		Conseiller c2 = new Conseiller();
		c2.setNom("Patoulatchi");
		c2.setPrenom("Marcel");
		c2.setAdresse("5 avenue du Chateau");
		c2.setCodePostal("45789");
		c2.setVille("Ville sur marne");
		c2.setTelephone("0745859632");
		c2.setLogin("demo2");
		c2.setPwd("demo2");
		String pwdvrai2 = iscd.encrypt(c2.getPwd(), c2.getLogin());
		c2.setPwd(pwdvrai2);

		Client cl2 = new Client();
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

		Client cl3 = new Client();
		cl3.setNom("Doe");
		cl3.setPrenom("Jane");
		cl3.setAdresse("chemin du pré");
		cl3.setCodePostal("38000");
		cl3.setVille("Grenoble");
		cl3.setTelephone("0145789632");
		cl3.setEntreprise(false);
		cl3.setNomEntreprise(null);
		cl3.setEmail("jdoe@example.fr");
		cl3.setConseiller(c1);

		Client cl4 = new Client();
		cl4.setNom("Sinatra");
		cl4.setPrenom("Frank");
		cl4.setAdresse("hollywood boulevard");
		cl4.setCodePostal("14587");
		cl4.setVille("Los Angeles");
		cl4.setTelephone("555-4548");
		cl4.setEntreprise(false);
		cl4.setNomEntreprise(null);
		cl4.setEmail("fsinatra@star.com");
		cl4.setConseiller(c1);

		Client cl5 = new Client();
		cl5.setNom("Ayraud");
		cl5.setPrenom("Florent");
		cl5.setAdresse("gabriel péri");
		cl5.setCodePostal("69100");
		cl5.setVille("Villeurbanne");
		cl5.setTelephone("98754121");
		cl5.setEntreprise(false);
		cl5.setNomEntreprise(null);
		cl5.setEmail("fayraud@email.com");
		cl5.setConseiller(c1);

		Client cl6 = new Client();
		cl6.setNom("William");
		cl6.setPrenom("David");
		cl6.setAdresse("chemin de la vie");
		cl6.setCodePostal("12000");
		cl6.setVille("Paris");
		cl6.setTelephone("01458796");
		cl6.setEntreprise(false);
		cl6.setNomEntreprise(null);
		cl6.setEmail("wd@test.com");
		cl6.setConseiller(c2);
		
		Client cl7 = new Client();
		cl7.setNom("Espuche");
		cl7.setPrenom("Marine");
		cl7.setAdresse("rue alexis Perroncel");
		cl7.setCodePostal("69100");
		cl7.setVille("Villeurbanne");
		cl7.setTelephone("0606060606");
		cl7.setEntreprise(false);
		cl7.setNomEntreprise(null);
		cl7.setEmail("me@email.com");
		cl7.setConseiller(c2);

		Compte co1 = new CompteCourant();
		co1.setNumeroCompte(14598563);
		co1.setSolde(524689.56);
		co1.setDateOuverture("2012-12-01");
		co1.setClient(cl2);

		Compte co2 = new CompteEpargne();
		co2.setNumeroCompte(85749852);
		co2.setSolde(75000);
		co2.setDateOuverture("2013-04-12");
		co2.setClient(cl2);

		Compte co3 = new CompteCourant();
		co3.setNumeroCompte(87895244);
		co3.setSolde(2587.23);
		co3.setDateOuverture("2010-05-06");
		co3.setClient(cl3);

		Compte co4 = new CompteEpargne();
		co4.setNumeroCompte(98765421);
		co4.setSolde(25045.23);
		co4.setDateOuverture("2013-04-12");
		co4.setClient(cl4);

		Compte co5 = new CompteEpargne();
		co5.setNumeroCompte(48521547);
		co5.setSolde(9658.23);
		co5.setDateOuverture("2017-05-14");
		co5.setClient(cl5);

		Compte co6 = new CompteCourant();
		co6.setNumeroCompte(1254785);
		co6.setSolde(568.12);
		co6.setDateOuverture("2016-05-23");
		co6.setClient(cl5);

		Compte co7 = new CompteCourant();
		co7.setNumeroCompte(1254879);
		co7.setSolde(50.01);
		co7.setDateOuverture("2009-05-23");
		co7.setClient(cl6);
		
		Compte co8 = new CompteCourant();
		co8.setNumeroCompte(968574);
		co8.setSolde(45000);
		co8.setDateOuverture("2008-08-02");
		co8.setClient(cl7);
 
		Compte co9 = new CompteEpargne();
		co9.setNumeroCompte(254563);
		co9.setSolde(458792.25);
		co9.setDateOuverture("2003-07-01");
		co9.setClient(cl7);
 

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("proxibanquev3-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(c1);
		em.persist(c2);
		em.persist(cl2);
		em.persist(cl3);
		em.persist(cl4);
		em.persist(cl5);
		em.persist(cl6);
		em.persist(cl7);
		em.persist(co1);
		em.persist(co2);
		em.persist(co3);
		em.persist(co4);
		em.persist(co5);
		em.persist(co6);
		em.persist(co7);
		em.persist(co8);
		em.persist(co9);
		tx.commit();
		em.close();
		
		 */
	

	}

}
