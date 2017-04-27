package dao.test;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Client;

public class DaoModifierClientTests {
	
	IDao idao = new DaoJPA() ;
	/**
	 * Test la modification d'un client
	 */
	@Test
	public void modificationClientTest() {
		
		Client c= new Client();
		c.setId(4);
		c.setNom("aaaaaaaaaaaaa");
		c.setPrenom("b");
		c.setEmail("a.b@gmail.com");
		
		
		Client c2 = c;
		idao.modifierClient(c2);
		Assert.assertEquals(c.getEmail(), c2.getEmail());
		
		
	}

}
