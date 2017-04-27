package metier;

public class Placement {

	private double montant;
	private String placeFinanciere;
	private Client client;

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getPlaceFinanciere() {
		return placeFinanciere;
	}

	public void setPlaceFinanciere(String placeFinanciere) {
		this.placeFinanciere = placeFinanciere;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Placement [montant=" + montant + ", placeFinanciere=" + placeFinanciere + ", client=" + client + "]";
	}
}
