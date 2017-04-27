package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import dao.TypeDaoQualificateur.TypeDAO;
import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.Conseiller;

//@TypeDaoQualificateur(TypeDAO.V2)
public class Dao implements IDao {

	@Override
	public Collection<Compte> listerComptes() {
		Collection<Compte> comptes = new ArrayList<Compte>();
		try {
			// creer la connexion
			Connection conn = DaoConnexion.getConnexion();

			// cr�er la requ�te
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Compte");

			// executer la requ�te
			ResultSet rs = ps.executeQuery();

			// pr�senter les r�sultats
			while (rs.next()) {
				String typeCompte = rs.getString("type_compte");
				if (typeCompte.equals("CompteCourant")) {
					CompteCourant cc = new CompteCourant();
					cc.setIdCompte(rs.getInt("id_compte"));
					cc.setNumeroCompte(rs.getInt("num_compte"));
					cc.setSolde(rs.getDouble("solde"));
					cc.setDateOuverture(rs.getString("date_ouverture"));
					cc.setDecouvert(rs.getDouble("decouvert"));
					comptes.add(cc);
				} else {
					CompteEpargne ce = new CompteEpargne();
					ce.setIdCompte(rs.getInt("id_compte"));
					ce.setNumeroCompte(rs.getInt("num_compte"));
					ce.setSolde(rs.getDouble("solde"));
					ce.setDateOuverture(rs.getString("date_ouverture"));
					ce.setRemuneration(rs.getDouble("remuneration"));
					comptes.add(ce);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est execut� quelque soit les �tapes pr�c�dentes
			DaoConnexion.closeConnexion();
		}
		return comptes;
	}

	@Override
	public void modifierCompte(Compte compte) {
		int row = 0;
		try {
			// prepare la requete
			PreparedStatement ps = DaoConnexion.getConnexion().prepareStatement(
					"UPDATE Compte SET solde = ?, remuneration = ?, decouvert = ? WHERE id_compte = ?");

			ps.setDouble(1, compte.getSolde());

			if (compte instanceof CompteCourant) {

				ps.setNull(2, java.sql.Types.DOUBLE);
				ps.setDouble(3, (((CompteCourant) compte).getDecouvert()));
			} else {
				ps.setDouble(1, compte.getSolde());
				ps.setDouble(2, (((CompteEpargne) compte).getRemuneration()));
				ps.setNull(3, java.sql.Types.DOUBLE);

			}
			ps.setInt(4, compte.getIdCompte());

			// executer la requ�te pour Coordonnee
			row += ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoConnexion.closeConnexion();
		}
		
	}

	@Override
	public void creerConseiller(Conseiller conseiller) {
		int i = 0;
		try {

			// 2- cr�er la connexion
			Connection conn = DaoConnexion.getConnexion();
			// 3- cr�er la requ�te

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Conseiller(nom, prenom, adresse, code_postal, ville, telephone,login,pwd) VALUES (?, ?, ?, ?,?,?,?,?)");
			ps.setString(1, conseiller.getNom());
			ps.setString(2, conseiller.getPrenom());
			ps.setString(3, conseiller.getAdresse());
			ps.setString(4, conseiller.getCodePostal());
			ps.setString(5, conseiller.getVille());
			ps.setString(6, conseiller.getTelephone());
			ps.setString(7, conseiller.getLogin());
			ps.setString(8, conseiller.getPwd());

			// 4- executer la requ�te
			i = ps.executeUpdate();
			// 5- pr�senter les r�sultats

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est execut� quelque soit les �tapes pr�c�dentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();

		}
		

	}

	@Override
	public void modifierConseiller(Conseiller conseiller) {
		int i = 0;
		try {

			// 2- cr�er la connexion
			Connection conn = DaoConnexion.getConnexion();
			// 3- cr�er la requ�te

			PreparedStatement ps = conn.prepareStatement(
					"UPDATE client SET nom = ? , prenom = ?, adresse = ?,code_postal =?, ville = ?,telephone = ?,login = ?, pwd = ?, WHERE id_conseiller = ?");
			ps.setString(1, conseiller.getNom());
			ps.setString(2, conseiller.getPrenom());
			ps.setString(3, conseiller.getAdresse());
			ps.setString(4, conseiller.getCodePostal());
			ps.setString(5, conseiller.getVille());
			ps.setString(6, conseiller.getTelephone());
			ps.setString(7, conseiller.getLogin());
			ps.setString(8, conseiller.getPwd());
			ps.setInt(9, conseiller.getId());

			// 4- executer la requ�te
			i = ps.executeUpdate();
			// 5- pr�senter les r�sultats

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est execut� quelque soit les �tapes pr�c�dentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();

		}
		

	}

	@Override
	public Conseiller verificationLogin(String login, String pwd) {
		Conseiller c = new Conseiller();
		try {
			// creer la connexion
			Connection conn = DaoConnexion.getConnexion();

			// Cr�er la requ�te
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Conseiller WHERE login = ? AND pwd = ?");
			ps.setString(1, login);
			ps.setString(2, pwd);

			// Ex�cuter la requ�te
			ResultSet rs = ps.executeQuery();

			// Pr�senter les r�sultats

			if (rs.next()) {
				c.setId(rs.getInt("id_conseiller"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAdresse(rs.getString("adresse"));
				c.setCodePostal(rs.getString("code_postal"));
				c.setVille(rs.getString("ville"));
				c.setLogin(rs.getString("login"));
				c.setPwd(rs.getString("pwd"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fermer la connexion
			DaoConnexion.closeConnexion();
		}
		return c;
	}

	@Override
	public void supprimerConseiller(Conseiller conseiller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerCompte(Compte compte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte getCompteParId(int id) {
		String typeCompte;
		CompteEpargne ce = new CompteEpargne();
		CompteCourant cc = new CompteCourant();
		try {
			// creer la connexion
			Connection conn = DaoConnexion.getConnexion();

			// 3- cr�er la requ�te
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Compte where id_compte = ?");
			ps.setInt(1, id);
			// 4- executer la requ�te
			ResultSet rs = ps.executeQuery();

			// 5- pr�senter les r�sultats
			rs.next();
			typeCompte = rs.getString("type_compte");

			if (typeCompte.equals("CompteCourant")) {
				cc.setIdCompte(rs.getInt("id_compte"));
				cc.setNumeroCompte(rs.getInt("num_compte"));
				cc.setSolde(rs.getDouble("solde"));
				cc.setDateOuverture(rs.getString("date_ouverture"));
				cc.setDecouvert(rs.getDouble("decouvert"));

			} else {
				ce.setIdCompte(rs.getInt("id_compte"));
				ce.setNumeroCompte(rs.getInt("num_compte"));
				ce.setSolde(rs.getDouble("solde"));
				ce.setDateOuverture(rs.getString("date_ouverture"));
				ce.setRemuneration(rs.getDouble("remuneration"));
				;

			}
			if (typeCompte.equals("CompteCourant")) {
				return (Compte) cc;
			} else {

				return (Compte) ce;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est execut� quelque soit les �tapes pr�c�dentes
			DaoConnexion.closeConnexion();
		}
		return cc;
	}

	@Override
	public void supprimerCompte(Compte compte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierClient(Client client) {
		int i = 0;
		try {

			// 2- cr�er la connexion
			Connection conn = DaoConnexion.getConnexion();
			// 3- cr�er la requ�te

			PreparedStatement ps = conn.prepareStatement(
					"UPDATE client SET nom = ?,prenom = ?, adresse = ?,code_postal =?,ville = ?,telephone = ?,entreprise = ?,nom_entreprise = ?,email =?WHERE id_client = ?");
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getAdresse());
			ps.setString(4, client.getCodePostal());
			ps.setString(5, client.getVille());
			ps.setString(6, client.getTelephone());
			ps.setBoolean(7, client.isEntreprise());
			ps.setString(8, client.getNomEntreprise());
			ps.setString(9, client.getEmail());
			ps.setInt(10, client.getId());

			// 4- executer la requ�te
			i = ps.executeUpdate();
			// 5- pr�senter les r�sultats

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est execut� quelque soit les �tapes pr�c�dentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();

		}
		
	}

	@Override
	public void supprimerClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client retourneClientParId(int idClient) {
		Client c = new Client();
		try {
			// creer la connexion
			Connection conn = DaoConnexion.getConnexion();

			// 3- cr�er la requ�te
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Client where id_client = ?");
			ps.setInt(1, idClient);
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Compte where id_client = ?");
			ps2.setInt(1, idClient);
			// 4- executer la requ�te
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			// 5- pr�senter les r�sultats
			if (rs.next()) {
				c.setId(rs.getInt("id_client"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAdresse(rs.getString("adresse"));
				c.setCodePostal(rs.getString("code_postal"));
				c.setVille(rs.getString("ville"));
				c.setTelephone(rs.getString("telephone"));
				c.setEntreprise(rs.getBoolean("entreprise"));
				c.setNomEntreprise(rs.getString("nom_entreprise"));
				c.setEmail(rs.getString("email"));
			}
			// 3- cr�er la requ�te
			Collection<Compte> comptes = new ArrayList<Compte>();
			while (rs2.next()) {
				String typeCompte = rs2.getString("type_compte");
				if (typeCompte.equals("CompteCourant")) {
					CompteCourant cc = new CompteCourant();
					cc.setIdCompte(rs2.getInt("id_compte"));
					cc.setNumeroCompte(rs2.getInt("num_compte"));
					cc.setSolde(rs2.getDouble("solde"));
					cc.setDateOuverture(rs2.getString("date_ouverture"));
					cc.setDecouvert(rs2.getDouble("decouvert"));
					comptes.add(cc);
				} else {
					CompteEpargne ce = new CompteEpargne();
					ce.setIdCompte(rs2.getInt("id_compte"));
					ce.setNumeroCompte(rs2.getInt("num_compte"));
					ce.setSolde(rs2.getDouble("solde"));
					ce.setDateOuverture(rs2.getString("date_ouverture"));
					ce.setRemuneration(rs2.getDouble("remuneration"));
					comptes.add(ce);

				}
				c.setComptes(comptes);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est execut� quelque soit les �tapes pr�c�dentes
			DaoConnexion.closeConnexion();
		}
		return c;
	}

	@Override
	public Collection<Client> listerClientsParConseiller(int idConseiller) {
		Collection<Client> clients = new ArrayList<Client>();
		try {
			// creer la connexion
			Connection conn = DaoConnexion.getConnexion();

			// 3- cr�er la requ�te
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Client where id_conseiller = ?");
			ps.setInt(1, idConseiller);
			// 4- executer la requ�te
			ResultSet rs = ps.executeQuery();
			// 5- pr�senter les r�sultats
			while (rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt("id_client"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAdresse(rs.getString("adresse"));
				c.setCodePostal(rs.getString("code_postal"));
				c.setVille(rs.getString("ville"));
				c.setTelephone(rs.getString("telephone"));
				c.setEntreprise(rs.getBoolean("entreprise"));
				c.setNomEntreprise(rs.getString("nom_entreprise"));
				c.setEmail(rs.getString("email"));

				clients.add(c);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Fermer la connexion
			DaoConnexion.closeConnexion();
		}
		return clients;
	}

	@Override
	public Conseiller afficherConseiller(int idConseiller) {
		Conseiller c = new Conseiller();
		try {
			// creer la connexion
			Connection conn = DaoConnexion.getConnexion();

			// 3- cr�er la requ�te
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Conseiller where id_conseiller = ?");
			ps.setInt(1, idConseiller);

			// 4- executer la requ�te
			ResultSet rs = ps.executeQuery();

			// 5- pr�senter les r�sultats
			if (rs.next()) {
				c.setId(rs.getInt("id_conseiller"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAdresse(rs.getString("adresse"));
				c.setCodePostal(rs.getString("code_postal"));
				c.setVille(rs.getString("ville"));
				c.setTelephone(rs.getString("telephone"));
				c.setLogin(rs.getString("login"));
				c.setPwd(rs.getString("pwd"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est execut� quelque soit les �tapes pr�c�dentes
			DaoConnexion.closeConnexion();
		}
		return c;
	}

}
