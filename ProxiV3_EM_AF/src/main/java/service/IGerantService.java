package service;

import metier.Agence;
import metier.Conseiller;
import metier.Gerant;

public interface IGerantService {

	public String effectuerAudit(Agence agence);
	public void creerConseiller(Conseiller co);
	public void supprimerConseiller(Gerant gerant, Conseiller c);
	public void listerConseiller(Gerant gerant);
	public void modifierConseiller(Gerant gerant, Conseiller c);
	
}
