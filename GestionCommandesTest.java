import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;


public class GestionCommandesTest {

	@Test
	public void testGestionCommandes() {
	}

	@Test
	public void testGetDistributeur() throws ParseException, IOException, SQLException {
		PersistanceSQL ps = new PersistanceSQL("localhost", 3306,"gestcommande");
		
		Produit p1 = new Produit("1","Mayette","Fraiche Entière",2);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dE2 = format.parse("14-10-2014");
		Date dC1 = format.parse("12-05-2015");
		Date dC2 = format.parse("12-10-2014");
		Date dC3 = format.parse("08-05-2014");
		Commande c1 = new Commande("00213", p1, 266, "filet 1kg",50, null,dC1,"carr15432");
		Commande c2 = new Commande("00214", p1, 398, "filet 1kg",100, dE2,dC2,"carr15432");
		Commande c3 = new Commande("00215", p1, 2660, "filet 5kg",100, null,dC2,"carr15432");
		
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		lesCommandes.add(c1);
		lesCommandes.add(c2);
		lesCommandes.add(c3);
		
		Distributeur dist1 = new Distributeur("carr15432","carreclerc",lesCommandes);
		
		GestionCommandes GC = new GestionCommandes(ps);
		Distributeur dist2 = GC.getDistributeur("carr15432");
		assertEquals(dist1.getNom(),dist2.getNom());
		
		String XML = GC.XmlNonLivrees(dist2);
		ArrayList<Commande> enCours = dist1.getCommandesEnCours();
		
		String XMLtest = ""
				+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+"<commandes idDistributeur=\"carr15432\" xmlns:xlink=\"carreclerc\">"
				+"<commande id=\"00213\"><produit variete=\"Mayette\" calibre =\"2\"/>"
				+ "<conditionnement type=\"filet 1kg\"/><quantite>50</quantite>"
				+ "<date_conditionnement>2014-05-12</date_conditionnement>"
				+ "<date_envoi>null</date_envoi></commande>"
				+ "<commande id=\"00215\"><produit variete=\"Mayette\" calibre =\"2\"/>"
				+ "<conditionnement type=\"filet 5kg\"/><quantite>100</quantite>"
				+ "<date_conditionnement>2014-05-08</date_conditionnement>"
				+ "<date_envoi>null</date_envoi></commande>";
		assertEquals(XMLtest,XML);
	}

	@Test
	public void testXmlNonLivrees() {
	}

}
