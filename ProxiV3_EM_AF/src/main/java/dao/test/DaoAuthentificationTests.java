package dao.test;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Conseiller;

public class DaoAuthentificationTests {

	
	IDao idao = new DaoJPA() ;
	
	/**
	 * Test l'authentification d'un conseiller dans le cas o� le conseiller rentre le bon login et le bon password
	 */
	@Test
	public void testauthentificationConseiller() {
		
		
		String login = "demo1";
		String pwd = "demo1";
		
		Conseiller c = idao.verificationLogin(login, pwd);
		
		Assert.assertEquals(c.getLogin(), login);
	}

	
	/**
	 * Test l'authentification d'un conseiller dans le cas o� le conseiller rentre le bon login et un mauvais password
	 */
	@Test
	public void testauthentificationConseiller2() {
		
		String login = "demo1";
		String pwd = "dem";
		
		Conseiller c = idao.verificationLogin(login, pwd);
		
		Assert.assertEquals("", c.getLogin());
	}
	
	/**
	 * Test l'authentification d'un conseiller dans le cas o� le conseiller rentre un mauvais login 
	 */
	@Test
	public void testauthentificationConseiller3() {
	
		String login = "dem";
		String pwd = "demo1";
		
		Conseiller c = idao.verificationLogin(login, pwd);
		
		Assert.assertEquals("", c.getLogin());
	}
	
	
	
	
}
