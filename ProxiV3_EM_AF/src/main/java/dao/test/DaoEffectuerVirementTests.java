package dao.test;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoJPA;
import dao.IDao;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;

public class DaoEffectuerVirementTests {

	IDao dao = new DaoJPA() ;
	
	/**
	 * Methode de test pour tester le virement d'un compte � un autre compte
	 * @throws SQLException
	 */
	@Test
	public void EffectuerUnVirementTest() {
		
		
		Compte c1 = new CompteEpargne();
		c1.setIdCompte(2); //le solde du compte est de 75 000 euros
		Compte c2 = new CompteCourant();
		c2.setIdCompte(7); //le solde du compte est de 50 euros
		
		int montant = 300; // montant du virement
		
		Compte c3 = dao.getCompteParId(2);//récupération du compte d'id 2
		Compte c4 = dao.getCompteParId(7); // récupération du compte d'id 7
	
		double solde1 = c3.getSolde()-montant; // nouveau montant du compte d'id2
		c3.setSolde(solde1); 
		double solde2 = c4.getSolde()+montant; // calcul du nouveau montant du compte d'id 7
		c4.setSolde(solde2); 
			dao.modifierCompte(c3); // association de ce montant au compte d'id 2 en bdd
			dao.modifierCompte(c4);// association de ce montant au compte d'id 7 en BDD
		Compte c5 = dao.getCompteParId(2);
		Compte c6 = dao.getCompteParId(7);
		double solde1bis = c5.getSolde();
		double solde2bis = c6.getSolde();
		
		Assert.assertEquals(true, (solde1bis==solde1 && solde2bis==solde2));
		
	}

}
