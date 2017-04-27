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
import metier.Personne;

//@TypeDaoQualificateur(TypeDAO.V3)
public class DaoJPA implements IDao {

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
	public void modifierConseiller(Conseiller conseiller) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(conseiller);
		tx.commit();
		em.close();
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
	public void supprimerConseiller(Conseiller conseiller) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Personne cl = em.find(Personne.class, conseiller.getId());
		em.remove(cl);
		tx.commit();
		em.close();
	}
	
	

	@Override
	public void creerCompte(Compte compte) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(compte);
		tx.commit();
		em.close();
	}

	@Override
	public Compte getCompteParId(int id) {
		String typeCompte;
		CompteEpargne ce = new CompteEpargne();
		CompteCourant cc = new CompteCourant();
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Compte c WHERE c.client_id = :lid AND a.Type_Compte = :letype");
		q.setParameter("lid", id);
		q.setParameter("letype", "CompteCourant");
		cc=(CompteCourant) q.getSingleResult();
		
		Query q3 = em.createQuery("SELECT a FROM Compte a WHERE a.client_id = :lid AND a.Type_Compte = :letype2");
		q3.setParameter("lid", id);
		q3.setParameter("letype2", "CompteEpargne");
		ce = (CompteEpargne) q3.getSingleResult();
		
		if(cc!=null)
		{
			return cc;
		}
		if(ce!=null)
		{
			return ce;
		}
		
		return cc;
	}

	@Override
	public void supprimerCompte(Compte compte) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Compte cl = em.find(Compte.class, compte.getIdCompte());
		em.remove(cl);
		tx.commit();
		em.close();
	}

	@Override
	public void creerClient(Client client) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(client);
		tx.commit();
		em.close();
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
	public void supprimerClient(Client client) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Personne cl = em.find(Personne.class, client.getId());
		em.remove(cl);
		tx.commit();
		em.close();
	}

	@Override
	public Client retourneClientParId(int idClient) {

		Client c = new Client();
		CompteCourant cc = new CompteCourant();
		CompteEpargne ce = new CompteEpargne();
		Collection<Compte> colcomptes = new ArrayList();
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.id = :lid");
		q.setParameter("lid", idClient);
		
		Query q2 = em.createQuery("SELECT a FROM Compte a WHERE a.client_id = :lid AND a.Type_Compte = :letype");
		q2.setParameter("lid", idClient);
		q2.setParameter("letype", "CompteCourant");
		cc = (CompteCourant) q2.getSingleResult();
		
		Query q3 = em.createQuery("SELECT a FROM Compte a WHERE a.client_id = :lid AND a.Type_Compte = :letype2");
		q3.setParameter("lid", idClient);
		q3.setParameter("letype2", "CompteEpargne");
		ce = (CompteEpargne) q3.getSingleResult();
		
		if(cc!=null)
		{
			colcomptes.add(cc);
		}
		
		if(ce!=null)
		{
			colcomptes.add(ce);
		}
		
		c.setComptes(colcomptes);
		return c;
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
		Conseiller c = new Conseiller();
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.id= :lid");
		q.setParameter("lid", idConseiller);
		List<Conseiller> listconseiller = q.getResultList();
		for(Conseiller conseiller :listconseiller)
		{
			c.setId(conseiller.getId());
			c.setNom(conseiller.getNom());
			c.setPrenom(conseiller.getPrenom());
			c.setAdresse(conseiller.getAdresse());
			c.setCodePostal(conseiller.getCodePostal());
			c.setVille(conseiller.getVille());
			c.setTelephone(conseiller.getTelephone());
			c.setLogin(conseiller.getLogin());
			c.setPwd(conseiller.getPwd());
			}
		em.close();
		return c;
		
	}

}
