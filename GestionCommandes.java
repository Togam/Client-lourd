import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class GestionCommandes {
    
    private PersistanceSQL donnees;
    
    public GestionCommandes(PersistanceSQL lesDonnees){
        this.donnees = lesDonnees;
    }
    
    public Distributeur getDistributeur(String idDistributeur) throws IOException, SQLException{
    	Distributeur res;
    	res = (Distributeur)donnees.ChargerDepuisBase(idDistributeur, "Distributeur");
		return res;
    }
    
    public String XmlNonLivrees(Distributeur unDistri){
    	ArrayList<Commande> liste = unDistri.getCommandesEnCours();
    	String res="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    	// Retourne une chaîne de caractères qui représente le document XML de la liste des commandes 
    	// non livrées du distributeur passé en paramètre comme le montre l'exemple de l'annexe. 
    	res = res + "<commandes idDistributeur=\"";
    	res = res + unDistri.getId();
    	res = res +"\" xmlns:xlink=\"";
    	res = res + unDistri.getNom()+"\">";
    	for(Commande uneCommande : liste){
    		res = res + uneCommande.XmlCommande();
    	}
    	
    	return res;
    }
        
}
