package dao.test;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Compte;

public class DaoListerCompteClientTests {

	
	IDao idao = new DaoJPA();
	/**
	 *  test la m�thode listercompteclient d'un client dans le cas ou le client poss�de deux comptes
	 */
	@Test
	public void listerCompteClientTest() {
	
		Collection<Compte> col = idao.listerComptes();
		
		Assert.assertEquals(col.size(), 9); //Regarde si la taille de la collection de comptes du client 2 est bien de 2
		
	}
	
	
	

		
				
				
}
