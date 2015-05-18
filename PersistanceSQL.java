
import java.sql.Date;
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
	    	 Class.forName("org.gjt.mm.mysql.Driver");
	    	   
	    	 connexion = DriverManager.getConnection("jdbc:mysql://" + ipBase + ":" +port + "/" + nomBaseDonne ,"root","");
	        	
	     }
	     catch(SQLException e) {
	        System.out.println("SQL error : " + e.getMessage());
	     }
	     catch(ClassNotFoundException e){
	        System.out.println("Class not found : " + e.getMessage());
	     }
	     
	 }
	
	   public void RangerDansBase(){
	        try
	        {
	            Statement stmt = connexion.createStatement();
	            ResultSet rs = stmt.executeQuery("");
	            // A COMPLETER
	        }
	        catch(SQLException e)
	        {
	            System.out.println("SQL error : " + e.getMessage());
	        }
	    }
	
	    public Object ChargerDepuisBase(String id , String nomClasse){
	    	Object objet = new Object();
	    	
	    	try {
	    		Statement stmt = connexion.createStatement();

		    	switch(nomClasse){
			        case "Produit" :     	
			        	try
			        	{
				            
				            String query = "SELECT V.Libelle, L.Type, C.taille "
				            		+ "FROM variete V, livraison L, lotproduction P, verger N, calibre C "
				            		+ "WHERE P.NumLot = \""+id+"\" "
				            		+ "AND P.CodeLiv = L.CodeLiv "
				            		+ "AND P.Calibre = C.IdCalibre "
				            		+ "AND L.CodeVerger = N.CodeVerger "
				            		+ "AND N.LibelleVar = V.Libelle";
				            ResultSet rs = stmt.executeQuery(query);
				            
				            String variete = "";
				            String typeProduit = "";
				            int calibre = 0;
				            
				            while(rs.next())
				            {
				            	variete = rs.getString("V.Libelle");
				            	typeProduit = rs.getString("L.Type");
				            	calibre = rs.getInt("C.taille");
				            }
				            	
				            objet = new Produit(variete,typeProduit,calibre);
				            
				        }
				        catch(SQLException e)
				        {
				            System.out.println("SQL error : " + e.getMessage());
				        }
			        	catch(java.lang.NullPointerException e){
			        		System.out.println("NullPointerException error : " + e.getMessage());
			        	}
			        case "Commande" :
			        	try
			        	{
			        		//RAJOUTER E.Date
				            String query = "SELECT E.Libelle, D.Quantite, C.Date "
				            		+ "FROM commande C, comporter D, conditionnement E "
				            		+ "WHERE C.CodeCom =\""+id+"\" "
				            		+ "AND C.CodeCom = D.NumCom "
				            		+ "AND E.IdCond = D.IdCond";
				            ResultSet rs = stmt.executeQuery(query);
				            
				            String conditionnement="";
				            int qte = 0;
				            Date dateE = null;
				            Date dateC = null;
				            
				            while(rs.next())
				            {
				            	conditionnement = rs.getString("E.Libelle");
				            	qte = rs.getInt("E.Quantite");
				            	dateE = rs.getDate("C.Date");
				            	//dateC = rs.getDate("E.Date");
				            }
				            
				            
				            
				            String query2 = "SELECT NumLot FROM commande";
				            ResultSet rs2 = stmt.executeQuery(query2);
				            int numProd = rs2.getInt("NumLot");
				            id = Integer.toString(numProd);
				            Object LeProd = new Object();
				            LeProd = this.ChargerDepuisBase(id, "Produit");
				            
				            //A COMPLETER ?
				            float prix = 0;
				            
				            objet = new Commande(Integer.parseInt(id), ((Produit)LeProd), prix, conditionnement, qte, dateE, dateC);
				            
			        	}
		        		 catch(SQLException e)
				        {
				            System.out.println("SQL error : " + e.getMessage());
				        }
			        	catch(java.lang.NullPointerException e){
			        		System.out.println("NullPointerException error : " + e.getMessage());
			        	}
		    	}
	    	}
	    	catch(SQLException e)
		    {
	    		System.out.println("SQL error : " + e.getMessage());
		    }
	        catch(java.lang.NullPointerException e){
	        	System.out.println("NullPointerException error : " + e.getMessage());
	        }
	    	
	    	try
            {
                connexion.close();
            }
            catch(SQLException e)
            {
                System.out.println("SQL error cannot close bdd " + e.getMessage());
            }
            catch(java.lang.NullPointerException e)
            {
                System.out.println("SQL error cannot close bdd " + e.getMessage());
            }
	    	
	        /*switch(nomClasse){
		        case "Produit" :     	
		        	try
		        	{
			            Statement stmt = connexion.createStatement();
			            String query = "SELECT V.Libelle, L.Type, C.taille "
			            		+ "FROM variete V, livraison L, lotproduction P, verger N, calibre C "
			            		+ "WHERE P.NumLot = \""+id+"\" "
			            		+ "AND P.CodeLiv = L.CodeLiv "
			            		+ "AND P.Calibre = C.IdCalibre "
			            		+ "AND L.CodeVerger = N.CodeVerger "
			            		+ "AND N.LibelleVar = V.Libelle";
			            ResultSet rs = stmt.executeQuery(query);
			            
			            String variete = "";
			            String typeProduit = "";
			            int calibre = 0;
			            
			            while(rs.next())
			            {
			            	variete = rs.getString("V.Libelle");
			            	typeProduit = rs.getString("L.Type");
			            	calibre = rs.getInt("C.taille");
			            }
			            	
			            objet = new Produit(variete,typeProduit,calibre);
			            
			        }
			        catch(SQLException e)
			        {
			            System.out.println("SQL error : " + e.getMessage());
			        }
		        	catch(java.lang.NullPointerException e){
		        		System.out.println("NullPointerException error : " + e.getMessage());
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
			            catch(java.lang.NullPointerException e)
			            {
			                System.out.println("SQL error cannot close bdd " + e.getMessage());
			            }
			               
			        }
		        case "Commande" :
		        	try
		        	{
			            Statement stmt = connexion.createStatement();
			            
			            String query = "SELECT CodeCom FROM commande";
			            ResultSet rs = stmt.executeQuery(query);
			            String idCom = rs.getString("CodeCom");
			            
			            String query2 = "SELECT NumLot FROM commande";
			            ResultSet rs2 = stmt.executeQuery(query2);
			            int numProd = rs2.getInt("NumLot");
			            id = Integer.toString(numProd);
			            Object LeProd = new Object();
			            
			           // LeProd = ps.ChargerDepuisBase(id, "Produit");
		        	}
	        		 catch(SQLException e)
			        {
			            System.out.println("SQL error : " + e.getMessage());
			        }
		        	catch(java.lang.NullPointerException e){
		        		System.out.println("NullPointerException error : " + e.getMessage());
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
			            catch(java.lang.NullPointerException e)
			            {
			                System.out.println("SQL error cannot close bdd " + e.getMessage());
			            }
			               
			        }
	        	/*case "Distributeur" :
	        		try
		        	{
			            Statement stmt = connexion.createStatement();
			            String query = "SELECT P.NumProducteur, P.NomSociete"
			            		+ "FROM producteur P";
			            ResultSet rs = stmt.executeQuery(query);
		        	}
	        		 catch(SQLException e)
			        {
			            System.out.println("SQL error : " + e.getMessage());
			        }
		        	catch(java.lang.NullPointerException e){
		        		System.out.println("NullPointerException error : " + e.getMessage());
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
			            catch(java.lang.NullPointerException e)
			            {
			                System.out.println("SQL error cannot close bdd " + e.getMessage());
			            }
			               
			        }
	        }	*/
	        
	    	
			return objet;
	    }    
	     
	        
}
