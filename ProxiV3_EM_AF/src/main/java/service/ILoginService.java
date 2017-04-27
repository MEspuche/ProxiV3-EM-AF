package service;

import metier.Conseiller;

public interface ILoginService {
	/**
	 * Methode retournant un conseiller à partir de son login et de son mot de
	 * passe
	 * 
	 * @param login
	 *            un login
	 * @param pwd
	 *            un mot de passe
	 * @return le conseiller correspondant au login et pwd
	 */
	public Conseiller verificationLogin(String login, String pwd);

}
