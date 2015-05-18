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
    	String res="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    	// Retourne une cha�ne de caract�res qui repr�sente le document XML de la liste des commandes 
    	// non livr�es du distributeur pass� en param�tre comme le montre l'exemple de l'annexe. 
    	res = res + "<commandes idDistributeur=\"";
    	res = res + unDistri.getId();
    	res = res +"\" xmlns:xlink=\"";
    	res = res + unDistri.getNom()+"\">\n";
    	for(Commande uneCommande : liste){
    		res = res + uneCommande.XmlCommande();
    	}
    	res = res + "</commandes>";
    	return res;
    }
        
}
