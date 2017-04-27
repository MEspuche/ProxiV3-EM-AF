package dao.test;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Client;
import metier.Conseiller;

public class DaoAjouterClientTest {
	
	IDao idao = new DaoJPA();
	
	@Test
	public void test()  {
		
		Conseiller c2 = new Conseiller();
		c2.setNom("Patoulatchi");
		c2.setPrenom("Marcel");
		c2.setAdresse("5 avenue du Chateau");
		c2.setCodePostal("45789");
		c2.setVille("Ville sur marne");
		c2.setTelephone("0745859632");
		c2.setLogin("demo2");
		c2.setPwd("demo2");
		Client c= new Client();
		String nom = "a";
		String prenom = "b";
		String a = "rue a";
		String cp ="69100";
		String ville ="villeurbanne";
		String email ="a.b@gmail.com";
		String telephone = "0202020202";
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setAdresse(a);
		c.setCodePostal(cp);
		c.setVille(ville);
		c.setTelephone(telephone);
		c.setEmail(email);
		c.setConseiller(c2);
		idao.creerClient(c);
		
		Client client = idao.retourneClientParId(9);
		
		Assert.assertEquals(client.getAdresse(),a);
		
	}

}
