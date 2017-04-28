package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.Conseiller;
import metier.Personne;
import service.IServiceCryptageDecryptgage;
import service.Services;

//@TypeDaoQualificateur(TypeDAO.V3)
public class DaoJPA implements IDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("proxibanquev3-pu");
	
	//testée
	@Override
	public void modifierCompte(Compte compte) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(compte);
		tx.commit();
		em.close();
	}
	
	//testée
	@Override
	public void creerConseiller(Conseiller conseiller) {
	
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(conseiller);
		tx.commit();
		em.close();
	}

	//testée
	@Override
	public void modifierConseiller(Conseiller conseiller) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(conseiller);
		tx.commit();
		em.close();
	}
	
	//testée
	@Override
	public Conseiller verificationLogin(String login, String pwd) {
		IServiceCryptageDecryptgage iscd = new Services();
		Conseiller c2 = new Conseiller();
		c2.setLogin("");
		c2.setPwd("");
		String pwdfaux = iscd.encrypt(pwd, login);
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.login = :lelogin AND c.pwd = :lemdp");
		q.setParameter("lelogin", login);
		q.setParameter("lemdp", pwdfaux);
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
		
		String pwdvrai = iscd.decrypt(c2.getPwd(), c2.getLogin());
		c2.setPwd(pwdvrai);
		em.close();
		return c2;
}
		

	//testée
	@Override
	public void supprimerConseiller(Conseiller conseiller) {
		EntityManager em = emf.createEntityManager();
		int id = conseiller.getId();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Personne cl = em.find(Personne.class, id);
		em.remove(cl);
		tx.commit();
		em.close();
	}
	
	
	//testée
	@Override
	public void creerCompte(Compte compte) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(compte);
		tx.commit();
		em.close();
	}
	
	//testée
	@Override
	public Compte getCompteParId(int id) {
		double x = 1000;
		CompteEpargne ce = new CompteEpargne();
		ce=null;
		CompteCourant cc = new CompteCourant();
		cc=null;
		Collection <CompteEpargne> cel = new ArrayList<>();
		Collection <CompteCourant> col = new ArrayList<>();
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Compte c WHERE c.idCompte = :lid AND c.remuneration= :rem");
		q.setParameter("lid", id);
		q.setParameter("rem", 0.03);
		cel= q.getResultList();
		for(CompteEpargne ce2 : cel)
		{
			ce= ce2;
		}
		if(ce!=null)
		{
			return ce;
		}
		else
		{
		Query q3 = em.createQuery("SELECT a FROM Compte a WHERE a.idCompte = :lid AND a.decouvert = :decouvert");
		q3.setParameter("lid", id);
		q3.setParameter("decouvert", x);
		col = q3.getResultList();
		for(CompteCourant cc2 : col)
		{
			cc = cc2;
		}
		
		return cc;
		}
	
	}

	//testée
	@Override
	public void supprimerCompte(Compte compte) {
		EntityManager em = emf.createEntityManager();
		int id = compte.getIdCompte();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Compte cl = em.find(Compte.class, id);
		em.remove(cl);
		tx.commit();
		em.close();
	}

	//testée
	@Override
	public void creerClient(Client client) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(client);
		tx.commit();
		em.close();
	}

	
	//testée
	@Override
	public void modifierClient(Client client) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(client);
		tx.commit();
		em.close();
		
	}

	//testée
	@Override
	public void supprimerClient(Client client) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		int id = client.getId();
		tx.begin();
		Personne cl = em.find(Personne.class, id);
		em.remove(cl);
		tx.commit();
		em.close();
	}

	//testée
	@Override
	public Client retourneClientParId(int idClient) {

		Client c = new Client();
		c=null;
		CompteCourant cc = new CompteCourant();
		cc=null;
		CompteEpargne ce = new CompteEpargne();
		ce=null;
		Collection<Compte> colcomptes = new ArrayList();
		EntityManager em = emf.createEntityManager();
		double x = 1000;
		
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.id = :lid");
		q.setParameter("lid", idClient);
		Collection<Client> col = q.getResultList();
		for(Client cl : col)
		{
			c = cl;
		}
		
		Query q2 = em.createQuery("SELECT a FROM Compte a WHERE a.client.id = :lid AND a.decouvert = :decouvert");
		q2.setParameter("lid", idClient);
		q2.setParameter("decouvert", x);
		Collection<CompteCourant> cc2 = q2.getResultList();
		for(CompteCourant cc3 : cc2)
		{
			cc = cc3;
		}
		
		
		
		Query q3 = em.createQuery("SELECT a FROM Compte a WHERE a.client.id = :lid AND a.remuneration= :rem");
		q3.setParameter("lid", idClient);
		q3.setParameter("rem", 0.03);
		Collection<CompteEpargne>ce2 = q3.getResultList();
		
		for(CompteEpargne ce3 : ce2)
		{
			ce= ce3;
		}
		if(cc!=null)
		{
			colcomptes.add(cc);
			if(ce!=null)
			{
				colcomptes.add(ce);
			}
			
		}

		c.setComptes(colcomptes);
		return c;
	}

	//testée
	@Override
	public Collection<Client> listerClientsParConseiller(int idConseiller) {
		
		Collection<Client> clients = new ArrayList<Client>();
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.conseiller.id= :lidconseiller");
		q.setParameter("lidconseiller", idConseiller);
		clients = q.getResultList();
		em.close();
		return clients;
	}
		

	//testée
	@Override
	public Collection<Compte> listerComptes() {
		Collection<Compte> comptes = new ArrayList<Compte>();
		Collection<CompteCourant> cc = new ArrayList<CompteCourant>();
		Collection<CompteEpargne> ce = new ArrayList<CompteEpargne>();
		double x = 1000;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Compte c WHERE c.remuneration= :rem");
		q.setParameter("rem", 0.03);
		cc=q.getResultList();
		for(Compte compte: cc)
		{
			comptes.add(compte);
		}
		Query q2 = em.createQuery("SELECT c FROM Compte c WHERE c.decouvert= :decouvert");
		q2.setParameter("decouvert", x);
		ce=q2.getResultList();
		for(Compte compte2: ce)
		{
			comptes.add(compte2);
		}
		return comptes;
		
	}

	//testée
	@Override
	public Conseiller afficherConseiller(int idConseiller) {
		Conseiller c = new Conseiller();
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT c FROM Personne c WHERE c.id= :lid");
		q.setParameter("lid", idConseiller);
		List<Conseiller> listconseiller = q.getResultList();
		for(Conseiller conseiller :listconseiller)
		{
			c=conseiller;
		}
		em.close();
		return c;
		
	}

	//testée
	@Override
	public int compterNombreClient(int idcon) {
		int i = 0;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT COUNT(c) FROM Personne c WHERE c.id= :lid");
		q.setParameter("lid", idcon);
		i=(int) q.getSingleResult();
		return i;
	}

}
