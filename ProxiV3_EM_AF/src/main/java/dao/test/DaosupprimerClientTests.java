package dao.test;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Client;

public class DaosupprimerClientTests {
	
	IDao idao = new DaoJPA() ;
	/**
	 * Test la modification d'un client
	 */
	@Test
	public void supprimerClientTest() {
		
		Client c= new Client();
		c.setId(5);

		
		
		Client c2 = new Client();
		idao.supprimerClient(c);
		c2 = idao.retourneClientParId(5);
		Assert.assertEquals(null, c2);
		
		
	}

}
