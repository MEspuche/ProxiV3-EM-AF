package dao;

import java.util.Collection;

import metier.Client;
import metier.Compte;
import metier.Conseiller;

public interface IDao {
/**
 * Modifie un compte dans la base de donn�es
 * @param compte Compte modifi�
 * 
 */
	public void modifierCompte(Compte compte);
/**
 * methode qui creer un conseiller dans la base de donn�es
 * @param conseiller conseiller a inserer dans la base de donn�es
 *
 */
 
	public void creerConseiller(Conseiller conseiller);
/**
 * Modifie un conseiller dans la base de donn�es
 * @param conseiller conseiller modifi�
 * 
 */
	public void modifierConseiller(Conseiller conseiller);
/**
 * M�thode qui verifie le login et le passWord d'un utilisateur dans la base de donn�es
 * @param login identifiant
 * @param pwd	mots de passe
 * @return retourne le conseiller si trouver en base de donn�es
 */
	public Conseiller verificationLogin(String login, String pwd);
/**
 * supprime un conseiller de la base de donn�es
 * @param conseiller conseiller � supprimer
 * 
 */

	public void supprimerConseiller(Conseiller conseiller);
/**
 * creer un compte dans la base de donn�es
 * @param compte compte � cr�er
 * 
 */
 
	public void creerCompte(Compte compte);
/**
 * retourne un compte selectionner par sont id
 * @param id identifiant du compte a retourner
 * 
 */
	public Compte getCompteParId(int id);
/**
 * supprime le compte de la base de donn�es
 * @param compte compte � supprimer
 * 
 */
	public void supprimerCompte(Compte compte);
/**
 * creer un client dans la base de donn�es
 * @param client client � creer
 * 
 */
	public void creerClient(Client client);
	
/**
 * modifie le client dans la base de donn�es
 * @param client client a modifi�
 * 
 */
	public void modifierClient(Client client);
/**
 * supprime un client dans la base de donn�es
 * @param client client � supprimer
 * 
 */

	public void supprimerClient(Client client);
/**
 * Retourne un client correspondant a l'Id
 * @param idClient id du client � retourner
 * @return client correspondant � l'id
 */
	public Client retourneClientParId(int idClient);
	
	/**
	 * retourne la liste de clients d'un conseiller
	 * @param idConseiller  identifiant du conseiller dont on veux la liste de clients
	 * @return la liste de client du conseiller
	 */

	public Collection<Client> listerClientsParConseiller(int idConseiller);
	
	/**
	 * R�cup�re tous les comptes de l'agence
	 * @return une collection de compte
	 */
	public Collection<Compte> listerComptes();
	
	/**
	 * retourne le conseiller corespondant a l'Id
	 * @param idConseiller id du conseiller � retourner
	 * @return conseiller corespondant a l'Id
	 */
	public Conseiller afficherConseiller(int idConseiller);
	/**
	 * Compter le nombre de clients reliés à un conseiller
	 * @param idcon identifiant du conseiller
	 * @return
	 */
	public int compterNombreClient(int idcon);

}
