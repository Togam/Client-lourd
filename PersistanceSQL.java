
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

		    	switch(nomClasse){
			        case "Produit" : 
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
				        	
				        	try
				        	{
				        
				        		Statement stmt1 = connexion.createStatement();
					            String query = "SELECT V.Libelle, L.Type, C.taille "
					            		+ "FROM variete V, livraison L, lotproduction P, verger N, calibre C "
					            		+ "WHERE P.NumLot = \""+id+"\" "
					            		+ "AND P.CodeLiv = L.CodeLiv "
					            		+ "AND P.Calibre = C.IdCalibre "
					            		+ "AND L.CodeVerger = N.CodeVerger "
					            		+ "AND N.LibelleVar = V.Libelle";
					            ResultSet rs = stmt1.executeQuery(query);
					            
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
				        	finally {
				        		try
					            {
				        			if(connexion != null) {
				       	             connexion.close();
				        			}
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
			        break;
			        
			        case "Commande" :	
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
				        	
				        	try
				        	{
				        		Statement stmt2 = connexion.createStatement();
					            String query2 = "SELECT E.Libelle, D.Quantite, C.Date, E.Date, C.prixHT "
					            		+ "FROM commande C, comporter D, conditionnement E "
					            		+ "WHERE C.CodeCom = \""+id+"\" "
					            		+ "AND C.CodeCom = D.NumCom "
					            		+ "AND E.IdCond = D.IdCond";
					            ResultSet rs2 = stmt2.executeQuery(query2);
					            
					            String conditionnement="";
					            int qte = 0;
					            Date dateE = null;
					            Date dateC = null;
					            float prix = 0;
					            
					            while(rs2.next())
					            {
					            	conditionnement = rs2.getString("E.Libelle");
					            	qte = rs2.getInt("D.Quantite");
					            	dateE = rs2.getDate("C.Date");
					            	dateC = rs2.getDate("E.Date");
					            	prix = rs2.getFloat("C.prixHT");
					            }
					            
					            Statement stmt3 = connexion.createStatement();
					            String query3 = "SELECT NumLot "
					            		+ "FROM commande "
					            		+ "WHERE CodeCom = \""+id+"\" ";
					            ResultSet rs3 = stmt3.executeQuery(query3);
					            
					            int numProd = 0;
					            while(rs3.next())
					            {
					            	numProd = rs3.getInt("NumLot");
					            }
					            id = Integer.toString(numProd);
				            
					            Produit LeProd = new Produit("0","0",0);
					            
					            try
					        	{
					        
					        		Statement stmt4 = connexion.createStatement();
						            String query = "SELECT V.Libelle, L.Type, C.taille "
						            		+ "FROM variete V, livraison L, lotproduction P, verger N, calibre C "
						            		+ "WHERE P.NumLot = \""+numProd+"\" "
						            		+ "AND P.CodeLiv = L.CodeLiv "
						            		+ "AND P.Calibre = C.IdCalibre "
						            		+ "AND L.CodeVerger = N.CodeVerger "
						            		+ "AND N.LibelleVar = V.Libelle";
						            ResultSet rs4 = stmt4.executeQuery(query);
						            
						            String variete = "";
						            String typeProduit = "";
						            int calibre = 0;
						            
						            while(rs4.next())
						            {
						            	variete = rs4.getString("V.Libelle");
						            	typeProduit = rs4.getString("L.Type");
						            	calibre = rs4.getInt("C.taille");
						            }
						            	
						            LeProd = new Produit(variete,typeProduit,calibre);
						            
						        }
						        catch(SQLException e)
						        {
						            System.out.println("SQL error : " + e.getMessage());
						        }
					        	catch(java.lang.NullPointerException e){
					        		System.out.println("NullPointerException error : " + e.getMessage());
					        	} 
					            
	
					            objet = new Commande(Integer.parseInt(id), LeProd, prix, conditionnement, qte, dateE, dateC);
					            
				        	}
			        		 catch(SQLException e)
					        {
					            System.out.println("SQL error : " + e.getMessage());
					        }
				        	catch(java.lang.NullPointerException e){
				        		System.out.println("NullPointerException error : " + e.getMessage());
				        	}
				        	finally {
				        		try
					            {
				        			if(connexion != null) {
				       	             connexion.close();
				        			}
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
			        	break;
		    	

	        	case "Distributeur" :
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
			            	if(connexion != null) {
			       	            connexion.close();
			        		}
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
	        		break;
	        }	
	        
	    	
			return objet;
	    }    
	     
	        
}
