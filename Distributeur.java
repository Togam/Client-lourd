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
	
	public Distributeur(String unId , String unNom){
        this.id = unId;
        this.nom = unNom ;
        this.lesCommandes = new ArrayList<Commande>();
        PersistanceSQL ps = new PersistanceSQL();
        
        try  {
        	Class.forName("org.gjt.mm.mysql.Driver");
   	    	connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestcommande" ,"root","");  	
   	     }
   	     catch(SQLException e) {
   	        System.out.println("SQL error : " + e.getMessage());
   	     }
   	     catch(ClassNotFoundException e){
   	        System.out.println("Class not found : " + e.getMessage());
   	     }
        	try{
            Statement stmt = connexion.createStatement();
            ResultSet result ;
            String query = "SELECT id FROM Commande Where idDistributeur=\""+ unId +"\"";
            PreparedStatement req = connexion.prepareStatement(query);
            result = req.executeQuery(query);
            while(result.next())
            { 
             String idCommande = result.getString("id");
             Commande C = (Commande) ps.ChargerDepuisBase(idCommande, "Commande");
             lesCommandes.add(C);
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQL error : " + e.getMessage());
        }
    
               
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
