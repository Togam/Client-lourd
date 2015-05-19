import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Distributeur {

	private String id, nom;
	private ArrayList<Commande> lesCommandes;
	private Connection connexion ;
	
	public Distributeur(String unId , String unNom, ArrayList<Commande> commandes){
        this.id = unId;
        this.nom = unNom;
        this.lesCommandes = commandes;     
        }
	
	public String getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
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
