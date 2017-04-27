package service;

import java.util.Collection;

import metier.Client;
import metier.Compte;
import metier.Conseiller;

public interface IConseillerService {

	/**
	 * R�cup�re tous les clients d'un conseiller
	 * 
	 * @param conseiller
	 *            conseiller dont on veut les clients
	 * @return la collection de clients du conseiller
	 */
	public Collection<Client> listerClients(Conseiller conseiller);

	/**
	 * Retourne le client correspondant � sont identifiant si le conseiller g�re ce dernier
	 * 
	 * @param conseiller
	 *            conseiller qui demande le client
	 * @param id
	 *            identifiant du client a retourner
	 * @return retourne le client correspondantFs
	 */
	public Client afficherClient(Conseiller conseiller, int id);

	/**
	 * Modifie un client sp�cifique d'un conseiller
	 * 
	 * @param conseiller
	 *            Le conseiller qui g�re le client
	 * @param idClient
	 *            l'identifiant du client
	 * @param nom
	 *            le nom du client
	 * @param prenom
	 *            le pr�nom du client
	 * @param email
	 *            l'email du client
	 * @param adresse
	 *            l'adresse du client
	 * @param codePostal
	 *            le code postal du client
	 * @param ville
	 *            la ville du client
	 * @param telephone
	 *            le num�ro de t�l�phone du client
	 * @return true si le client a bien �t� modifi� et false en cas contraire
	 */
	public void modifierClient(Conseiller conseiller, int idClient, String nom, String prenom, String email,
			String adresse, String codePostal, String ville, String telephone);

	/**
	 * Methode qui permet de realiser des virement de compte � compte
	 * 
	 * @param conseiller
	 *            conseiller qui realise le virement
	 * @param client
	 *            client a qui appartient le compte � d�biter
	 * @param compteCred
	 *            Montant du virement
	 * @param comptedeb
	 *            Compte a crediter
	 * @param montant
	 *            Compte a debiter
	 * @return vrai si le virement a �t� effectu� faux dans le cas contraire
	 */
	public boolean effectuerVirement(Conseiller conseiller, Client client, Compte compteCred, Compte comptedeb,
			double montant);

	/**
	 * R�cup�re un compte par son identifiant
	 * 
	 * @param id
	 *            identifiant du compte
	 * @return le Compte r�cup�r�
	 */
	public Compte recupererCompteParId(int id);

	/**
	 * R�cup�re tous les comptes de l'agence sauf le compte donn� en argument
	 * 
	 * @param compte
	 *            Compte � ne pas r�cup�rer
	 * @return La collection de tous les comptes sauf le compte donn�
	 */
	public Collection<Compte> recupererAutresComptes(Compte compte);

	/**
	 * Retourne le conseiller corespondant a l'idConseiller
	 * 
	 * @param idConseiller
	 *            identifiant du conseiller a retourner
	 * @return le conseiller correspondant
	 */
	public Conseiller afficherConseiller(int idConseiller);

	/**
	 * Cr�ation d'un client si le conseiller n'a pas atteint son nombre de
	 * clients maximum
	 * 
	 * @param conseiller
	 *            conseiller qui creer le client
	 * @param client
	 *            client a cr�er
	 * @return retourne true si le client a pu etre creer sinon retourne faux
	 */
	public boolean creerClient(Conseiller conseiller, Client client);

	/**
	 * supprime un client
	 * 
	 * @param conseiller
	 *            conseiller qui supprime le client
	 * @param client
	 *            client a supprimer
	 */
	public void supprimerClient(Conseiller conseiller, Client client);
}
