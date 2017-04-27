package dao.test;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.IDao;

public class DaoCompterNombreClientTest {
	@Inject
	IDao idao ;
	/**
	 * Test si la methoe pour compter le nombre de client d'un conseiller fonctionne avec la BDD implementee initiale
	 */
	@Test
	public void test() {
	
		int x = idao.compterNombreClient(2);// le conseiller 2 a 5 clients 
		Assert.assertEquals(5, x);
		
	}

}
