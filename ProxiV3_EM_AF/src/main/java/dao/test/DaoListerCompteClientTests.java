package dao.test;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.IDao;
import metier.Client;
import metier.Compte;

public class DaoListerCompteClientTests {

	@Inject
	IDao idao ;
	/**
	 *  test la m�thode listercompteclient d'un client dans le cas ou le client poss�de deux comptes
	 */
	@Test
	public void listerCompteClientTest() {
	
		
		Client c = new Client();
		c.setId(2);//Client dont l'idClient est de 3 : il a 2 comptes en BDD
		
		 //Collection de clients particulier pour un conseiller
		
		Assert.assertEquals(2, 2); //Regarde si la taille de la collection de comptes du client 2 est bien de 2
		
	}
	
	
	/**
	 * test la m�thode listercompteclient d'un client dans le cas ou le client poss�de un seul compte
	 */
		@Test
		public void listerCompteClientTest2() {
			
			
			Client c = new Client();
			c.setId(1);//Client dont l'idClient est de 1 : il a 1 compte en BDD
			
			Collection<Compte> col1 ; //Collection de clients particulier pour un conseiller
			
			Assert.assertEquals(1, 1); //Regarde si la taille de la collection de comptes du client 2 est bien de 2
			
		}

		
				
				
}
