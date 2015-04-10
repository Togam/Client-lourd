
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
		        case "Commande" :     	
		        	try
			        {
			            Statement stmt = connexion.createStatement();
			            /*String query = "SELECT * FROM " + nomClasse + " WHERE id =?";
			            PreparedStatement req = connexion.prepareStatement(query);
			            req.setString(1,id);
			            ResultSet rs = req.executeQuery(query);
			            while(rs.next())
			            {
			                
			            }*/
			            
			            //Cast du type d'objet dans objet
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
	        	
	        	case "Produit" :
	        		
	        }	
	    	
			return objet;
	    }    
	     
	        
}
