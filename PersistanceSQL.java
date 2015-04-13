
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersistanceSQL {
	
		private String ipBase , nomBaseDonne ;
	    private int port ;
	    private Connection connexion ;
	    
	 public PersistanceSQL(String ipBase , int port , String nomBaseDonne){
	     this.ipBase = ipBase ;
	     this.port = port ; 
	     this.nomBaseDonne = nomBaseDonne;
	     try  {
	    	 Class .forName("org.gjt.mm.mysql.Driver");
	    	   
	    	 connexion = DriverManager.getConnection("jdbc:mysql://" + ipBase + ":" +port + "/" + nomBaseDonne ,"root","");
	        	
	     }
	     catch(SQLException e) {
	        System.out.println("SQL error : " + e.getMessage());
	     }
	     catch(ClassNotFoundException e){
	        System.out.println("Class not found : " + e.getMessage());
	     }
	     finally {
	        try {
	          if(connexion != null) {
	             connexion.close();
	          }    
	        }
	        catch(SQLException e) {
	             System.out.println("SQL error : " +e.getMessage());
	        }
	     }
	 }
	
	  /*  public void RangerDansBase(){
	        try
	        {
	            Statement stmt = connexion.createStatement();
	            ResultSet rs = stmt.executeQuery("");
	            
	        }
	        catch(SQLException e)
	        {
	            System.out.println("SQL error : " + e.getMessage());
	        }
	    }*/
	
	    public Object ChargerDepuisBase(String id , String nomClasse){
	    	Object objet = new Object();
	        switch(nomClasse){
		        case "Produit" :     	
		        	try
		        	{
			            Statement stmt = connexion.createStatement();
			            String query = "SELECT V.Libelle, L.TypeProduit, P.Calibre "
			            		+ "FROM variete V, livraison L, lotproduction P, verger N "
			            		+ "WHERE P.NumLot ="+id+" "
			            		+ "AND P.CodeLiv = L.CodeLiv"
			            		+ "AND L.CodeVerger = N.CodeVerger"
			            		+ "AND N.LibelleVar = V.Libelle";
			            ResultSet rs = stmt.executeQuery(query);
			            
			            String variete = "";
			            String typeProduit = "";
			            int calibre = 0;
			            
			            while(rs.next())
			            {
			            	variete = rs.getString("V.Libelle");
			            	typeProduit = rs.getString("L.TypeProduit");
			            	calibre = rs.getInt("P.Calibre");
			            }
			            
			            if(calibre !=0){
			            	Produit leProduit = new Produit(variete,typeProduit,calibre);
			            	objet = leProduit;
			            }
			        }
			        catch(SQLException e)
			        {
			            System.out.println("SQL error : " + e.getMessage());
			        }
			        finally
			        {
			            try
			            {
			                connexion.close();
			            }
			            catch(SQLException e)
			            {
			                System.out.println("SQL error cannot close bdd " + e.getMessage());
			            }
			               
			        }
	        	case "Distributeur" :
	        	
	        	case "Commande" :
	        		
	        }	
	    	
			return objet;
	    }    
	     
	        
}
