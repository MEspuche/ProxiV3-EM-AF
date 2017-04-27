package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * Classe qui gère la connexion à une base de donnée mySQL
 *
 */
public class DaoConnexion {
	private static final String PILOTE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/proxibanqueafij";
	private static final String USER = "root";
	private static final String PWD = "";
	private static Connection conn = null;

	/**
	 * Créee une connexion avec la base de donnée, si innexistante, et la
	 * retourne
	 * 
	 * @return une connexion avec la base de donnée
	 */
	public static Connection getConnexion() {
		if (conn == null) {

			try {
				// charger le pilote
				Class.forName(PILOTE);
				// créer la connexion
				conn = DriverManager.getConnection(URL, USER, PWD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * Ferme la connexion avec la base de donnée
	 */
	public static void closeConnexion() {
		if (conn != null) {
			// fermeture de la connexion
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
