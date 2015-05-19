
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;


public class PersistanceSQL {
	
		private String ipBase , nomBaseDonne ;
	    private int port ;
	    private Connection connexion ;
	    
	public PersistanceSQL(){
	    	
	}
	
	 public PersistanceSQL(String ipBase , int port , String nomBaseDonne){
	     this.ipBase = ipBase ;
	     this.port = port ; 
	     this.nomBaseDonne = nomBaseDonne;
	 }
	
	   public void RangerDansBase(Object unObjet){
		   
		   if(unObjet.getClass().toString() == "Produit"){
			   String variete = ((Produit)unObjet).getVariete();
			   String type = ((Produit)unObjet).getType();
			   int calibre = ((Produit)unObjet).getCalibre();
			   
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
			   
			   try{
				   Statement stmt1 = connexion.createStatement();
				   String query1 = "INSERT INTO lotproduction ('variete', 'type', 'Calibre') VALUES ('"+variete+"', '"+type+"', '"+calibre+"')";
				   ResultSet rs1 = stmt1.executeQuery(query1);
				   rs1.insertRow();
			   }
			   catch(SQLException e)
		        {
		            System.out.println("SQL error : " + e.getMessage());
		        }
	        	catch(java.lang.NullPointerException e){
	        		System.out.println("NullPointerException error : " + e.getMessage());
	        	}
		   }
		   
		   if(unObjet.getClass().toString() == "Commande"){
			   String id = ((Commande)unObjet).getId();
			   Produit LeProd = ((Commande)unObjet).getProduit();
			   double prixHT = ((Commande)unObjet).getPrixHT();
			   String conditionnement = ((Commande)unObjet).getConditionnement();
			   int qte = ((Commande)unObjet).getQte();
			   Date envoie = (Date) ((Commande)unObjet).getDateEnvoi();
			   Date cond = (Date) ((Commande)unObjet).getDateConditionnement();
			   
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
		   
		   if(unObjet.getClass().toString() == "Distributeur"){
			   String id = ((Distributeur)unObjet).getId();
			   String nom = ((Distributeur)unObjet).getNom();
			   ArrayList<Commande> LesCommandes = new ArrayList<Commande>();
			   LesCommandes = ((Distributeur)unObjet).getCommandes();
			   
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

	    }
	
	   public Object ChargerDepuisBase(String id, String nomClasse) throws SQLException{
	    	Object res = new Object();
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
	        	
	                switch(nomClasse){
		                case "Distributeur" :
		                	try
		        	        {
		        	            Statement stmt = connexion.createStatement();
		        	            String query = "SELECT * FROM " + nomClasse + " WHERE id= \""+id+"\"";
		        	            ResultSet result = stmt.executeQuery(query);
		        	            
		        	            String nom ="";
		        	            
		        	            while(result.next())
		        	            {
				                	nom = result.getString("nomDistributeur");
		        	            }
		        	            
		        	            Statement stmt2 = connexion.createStatement();
		        	            ArrayList<Commande> commandes = new ArrayList<Commande>();
		        	            String query2 = "SELECT id FROM commande WHERE idDistributeur= \""+id+"\"";
		        	            ResultSet result2 = stmt2.executeQuery(query2);
		        	            
		        	            ArrayList<String> codeCom = new ArrayList<String>();
		        	            while(result2.next()){
		        	            	String idDistrib = result2.getString("id");
		        	            	codeCom.add(idDistrib);
		        	            }
		        	            
		        	            for (String code : codeCom){
		        	            	Commande commande = (Commande)this.ChargerDepuisBase(code, "Commande");
		        	            	commandes.add(commande);
		        	            }

		        	            res = new Distributeur(id, nom, commandes);
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
		                	try
		        	        {
		        	            Statement stmt = connexion.createStatement();
		        	            String query = "SELECT * FROM " + nomClasse + " WHERE id= \""+id+"\"";
		        	            
		        	            ResultSet result = stmt.executeQuery(query);
		        	            
		        	            String idproduit ="";
		        	            double prix = 0;
		        	            String conditionnement = "";
		        	            int qte = 0;
		        	            Date dateEnvoi = null;
		        	            Date dateCondi = null;
		        	            String idDistrib = "";
		        	            
		        	            while(result.next())
		        	            {
				                	idproduit = result.getString("idProduit");
				                	prix = result.getDouble("prixHt");
				                	conditionnement = result.getString("conditionnement");
				                	qte = result.getInt("quantite");
				                	dateCondi = result.getDate("dateConditionnement");
				                	dateEnvoi = result.getDate("dateEnvoi");
				                	idDistrib = result.getString("idDistributeur");                	
		        	            }
		        	            Produit produit = (Produit)this.ChargerDepuisBase(idproduit, "Produit");
		        	            res = new Commande(id, produit, prix, conditionnement, qte, dateEnvoi, dateCondi, idDistrib);
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
		                case "Produit":
		                	try
		        	        {
		        	            Statement stmt = connexion.createStatement();
		        	            String query = "SELECT * FROM produit WHERE id= \""+id+"\"";
		        	            
		        	            ResultSet result = stmt.executeQuery(query);
		        	            String variete = "";
		        	            String type ="";
		        	            int calibre = 0;
		        	            while(result.next())
		        	            {
					                variete = result.getString("varieteProduit");
					                type = result.getString("typeProduit");
					                calibre = result.getInt("calibreProduit");
		        	            }
		        	            res = new Produit(id,variete,type,calibre);
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
		            }
	                

			return res;
	   }
	        
}
