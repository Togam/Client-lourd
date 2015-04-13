import java.util.ArrayList;


public class Distributeur {

	private String id, nom;
	private ArrayList<Commande> lesCommandes;
	
	public Distributeur(String unId, String unNom, ArrayList<Commande> UneListeCommandes){
		this.id = unId;
		this.nom = unNom;
		this.lesCommandes = new ArrayList<Commande>();
		this.lesCommandes = UneListeCommandes;
	}
	
	public String getId(){
		return this.id;
	}
	public ArrayList<Commande> getCommandes(){
		return this.lesCommandes;
	}
	public ArrayList<Commande> getCommandesEnCours(){
		ArrayList<Commande> lesCommandesEnCours = new ArrayList<Commande>();
		for (Commande tmp : lesCommandes){
			if(tmp.EnCours() == true){
				lesCommandesEnCours.add(tmp);
			}
		}
		return lesCommandesEnCours;
	}
	
}
