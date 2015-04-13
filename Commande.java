import java.util.Date;
import java.text.SimpleDateFormat;


public class Commande {

	private int id;
	private Produit leProduit;
	private float prixHT;
	private String conditionnement;
	private int qte;
	private Date dateEnvoi = null, dateConditionnement;
	
	public Commande(int unId, Produit unProd, float unPrix, String unCond, int uneQte, Date uneDateE, Date uneDateC){
		this.id = unId;
		this.leProduit = unProd;
		this.prixHT = unPrix;
		this.conditionnement = unCond;
		this.qte = uneQte;
		this.dateConditionnement = uneDateC;
		this.dateEnvoi = uneDateE;
	}
	
	public int getId(){
		return this.id;
	}
	public Produit getProduit(){ 
		return this.leProduit;
	}
	
	public float getPrixHT(){
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
