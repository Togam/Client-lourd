import java.util.Date;
import java.text.SimpleDateFormat;


public class Commande {

	private String id;
	private Produit leProduit;
	private double prixHT;
	private String conditionnement;
	private int qte;
	private Date dateEnvoi = null, dateConditionnement;
	String idDistributeur;
	
	public Commande(String id, Produit unProd, double prix, String conditionnement , int quantite , Date dateEnvoi,Date dateConditionnement, String idDistrib){
        this.id =id;
        this.prixHT = prix;
        this.conditionnement = conditionnement ;
        this.qte = quantite ;
        this.dateConditionnement = dateConditionnement ;
        this.dateEnvoi = dateEnvoi;
        this.idDistributeur = idDistrib;
        this.leProduit = unProd;
        
    }
	
	public String getId(){
		return this.id;
	}
	public Produit getProduit(){ 
		return this.leProduit;
	}
	
	public double getPrixHT(){
		return this.prixHT;
	}
	public String getConditionnement(){
		return this.conditionnement;
	}
	public int getQte(){
		return this.qte;
	}
	public Date getDateEnvoi(){
		return this.dateEnvoi;
	}
	public Date getDateConditionnement(){
		return this.dateConditionnement;
	}
	public String getIdDistributeur(){
		return this.idDistributeur;
	}
	
	public boolean EnCours(){
		boolean rep = true;
		if(this.dateEnvoi != null){
			rep = false;
		}
		return rep;
	}
	
	public String XmlCommande(){
		String xml = "";
		xml += "<commande id=\"" + this.id + "\">";
		xml += "<produit variete=\"" + leProduit.getVariete() + "\" ";
		xml += "calibre =\"" + leProduit.getCalibre()+ "\"/>";
		xml += "<conditionnement type=\""  + this.conditionnement + "\"/>";
		xml += "<quantite>" + this.qte + "</quantite>";
		xml += "<date_conditionnement>" + this.dateConditionnement+ "</date_conditionnement>";
		xml += "<date_envoi>" + this.dateEnvoi + "</date_envoi>";
		xml += "</commande>";
		
		return xml;
	}
	
}
