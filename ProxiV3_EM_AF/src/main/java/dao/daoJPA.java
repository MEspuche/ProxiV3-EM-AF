package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.TypeDaoQualificateur.TypeDAO;
import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.Conseiller;

@TypeDaoQualificateur(TypeDAO.V3)
public class daoJPA implements IDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("proxibanquev3-pu");
	
	@Override
	public void modifierCompte(Compte compte) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(compte);
		tx.commit();
		em.close();
		
	
	}

	@Override
	public void creerConseiller(Conseiller conseiller) {
	
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(conseiller);
		tx.commit();
		em.close();
		
		
		
	}

	@Override
	public int modifierConseiller(Conseiller conseiller) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Conseiller verificationLogin(String login, String pwd) {
		
		Conseiller c2 = new Conseiller();
		c2.setLogin("");
		c2.setPwd("");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.login = :lelogin AND c.pwd = :lemdp");
		q.setParameter("lelogin", login);
		q.setParameter("lemdp", pwd);
		List<Conseiller> listpwd = q.getResultList();
		for(Conseiller conseiller :listpwd)
		{
			c2.setId(conseiller.getId());
			c2.setNom(conseiller.getNom());
			c2.setPrenom(conseiller.getPrenom());
			c2.setAdresse(conseiller.getAdresse());
			c2.setCodePostal(conseiller.getCodePostal());
			c2.setVille(conseiller.getVille());
			c2.setLogin(conseiller.getLogin());
			c2.setPwd(conseiller.getPwd());
			
			}
		em.close();
		return c2;
}
		


	@Override
	public int supprimerConseiller(Conseiller conseiller) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int creerCompte(Compte compte) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Compte getCompteParId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int supprimerCompte(Compte compte) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int creerClient(Client client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modifierClient(Client client) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(client);
		tx.commit();
		em.close();
		
	}

	@Override
	public int supprimerClient(Client client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client retourneClientParId(int idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Client> listerClientsParConseiller(int idConseiller) {
		
		Collection<Client> clients = new ArrayList<Client>();
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.conseiller_id= :lidconseiller");
		q.setParameter("lidconseiller", idConseiller);
		clients = q.getResultList();
		em.close();
		return clients;
	}
		
		
	@Override
	public Collection<Compte> listerComptes() {
		Collection<Compte> comptes = new ArrayList<Compte>();
		Collection<CompteCourant> cc = new ArrayList<CompteCourant>();
		Collection<CompteEpargne> ce = new ArrayList<CompteEpargne>();
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Compte c WHERE c.Type_Compte= :lecompte");
		q.setParameter("lecompte", "CompteCourant");
		cc=q.getResultList();
		comptes.addAll(cc);
		Query q2 = em.createQuery("SELECT c FROM Compte c WHERE c.Type_Compte= :lecompte2");
		q2.setParameter("lecompte2", "CompteEpargne");
		ce=q2.getResultList();
		comptes.addAll(ce);
		return comptes;
		
	}

	@Override
	public Conseiller afficherConseiller(int idConseiller) {
		// TODO Auto-generated method stub
		return null;
	}

}
