package dao.test;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Client;
import metier.Conseiller;

public class DaoListerClientTests {

	
	IDao idao = new DaoJPA();
	/**
	 * Test la m�thode lister client dans le cas d'un conseiller qui a 4 clients
	 */
	@Test
	public void listerClienttest() {
		
		
		Conseiller co = new Conseiller();
		int idcon = 1;//Conseiller dont l'idConseiller est de 1 : il a 4 clients en BDD
		
		Collection<Client> col1 = idao.listerClientsParConseiller(idcon); //Collection de clients particulier pour un conseiller
		
		Assert.assertEquals(4, col1.size()); //Regarde si la taille de la collection du conseiller 1 est bien �gale � 10
		
	}
	
	
	
}
