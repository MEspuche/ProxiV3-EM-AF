package metier;

public abstract class CarteBancaire {

	private boolean bloquee = false;
	private Compte compte;

	public CarteBancaire(Compte compte) {
		super();
		this.compte = compte;
	}

	public boolean isBloquee() {
		return bloquee;
	}

	public void setBloquee(boolean bloquee) {
		this.bloquee = bloquee;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString() {
		return "CarteBancaire [bloquee=" + bloquee + ", compte=" + compte + "]";
	}

}
