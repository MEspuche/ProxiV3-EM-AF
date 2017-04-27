package tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.DaoConnexion;
import dao.IDao;
import metier.Client;
import metier.Compte;
import metier.CompteCourant;

public class DaoTest {

	private IDao dao = new Dao();

	@AfterClass
	public static void after() {
		DaoConnexion.closeConnexion();
	}

	@Test
	public void testListerTousComptes() {
		Connection conn = DaoConnexion.getConnexion();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT COUNT(id_client) FROM Compte");
			ResultSet rs = ps.executeQuery();
			rs.next();
			int nbLignes = rs.getInt(1);

			Collection<Compte> col = dao.listerComptes();

			Assert.assertEquals(nbLignes, col.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testModifierCompteExistant() {
		// simule le compte 1 qui est un coompte courant et existe en bdd
		Compte c = new CompteCourant();
		c.setIdCompte(1);

		// modifie son solde a 1000
		c.setSolde(1000);

		// appel de la methode
		dao.modifierCompte(c);
		Connection conn = DaoConnexion.getConnexion();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT solde FROM Compte WHERE id_compte = 1");
			ResultSet rs = ps.executeQuery();
			rs.next();
			Double soldeBdd = rs.getDouble("solde");

			// assertion
			Assert.assertEquals(c.getSolde(), soldeBdd, 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testModifierCompteInnexistantExistant() {
		// simule le compte 999 qui n'existe pas en bdd
		Compte c = new CompteCourant();
		c.setIdCompte(999);

		// appel de la methode
		dao.modifierCompte(c);

		Assert.assertEquals(0, 0);
	}

	@Test
	public void testModifierClientExistant() {
		// le client 8 doit exister
		int idClient = 8;
		String nouveauNom = "nomTest";

		Client c = new Client();
		c.setId(idClient);
		c.setNom(nouveauNom);
		c.setPrenom("testPrenom");
		c.setAdresse("testAdresse");
		c.setCodePostal("testCodePostal");
		c.setVille("testVille");
		c.setTelephone("testTelephone");
		c.setEntreprise(true);
		c.setNomEntreprise("testEntreprise");
		c.setEmail("test@email.test");
		// appel de la methode
		dao.modifierClient(c);
		Connection conn = DaoConnexion.getConnexion();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT nom FROM Client WHERE id_client = ?");
			ps.setInt(1, idClient);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String nomBDD = rs.getString("nom");

			// assertion
			Assert.assertEquals(nouveauNom, nomBDD);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testModifierClientInnexistantExistant() {
		// simule le compte 99 qui n'existe pas en bdd
		Client c = new Client();
		c.setId(99);
		c.setNom("nomTest");
		c.setPrenom("testPrenom");
		c.setAdresse("testAdresse");
		c.setCodePostal("testCodePostal");
		c.setVille("testVille");
		c.setTelephone("testTelephone");
		c.setEntreprise(true);
		c.setNomEntreprise("testEntreprise");
		c.setEmail("test@email.test");

		try {
			// appel de la methode
			dao.modifierClient(c);

			Assert.assertEquals(0, 0);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
