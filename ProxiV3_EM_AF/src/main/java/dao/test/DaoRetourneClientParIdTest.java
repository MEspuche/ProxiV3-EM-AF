package dao.test;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Client;

public class DaoRetourneClientParIdTest {

	IDao idao = new DaoJPA() ;
	
	@Test
	public void testretourneClientParId()
	{
		Client c = idao.retourneClientParId(3);
		Assert.assertEquals(c.getComptes().size(), 2);
		
	}
	
}
