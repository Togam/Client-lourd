
public class Produit {

	private String variete, type, id;
	private int calibre;
	
	public Produit(String id,String uneVar, String unType, int unCalibre){
		this.id = id;
		this.variete = uneVar;
		this.type = unType;
		this.calibre = unCalibre;
	}
	
	public String getVariete(){
		return this.variete;
	}
	public String getType(){
		return this.type;
	}
	public int getCalibre(){
		return this.calibre;
	}
	
}
