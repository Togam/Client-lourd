import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;


public class PersistanceSQLTest {

	@Test
	public void testPersistanceSQL() {
		
	}

	@Test
	public void testRangerDansBase() {
		
		
	}

	@Test
	public void testChargerDepuisBase() throws ParseException, SQLException {
			PersistanceSQL ps = new PersistanceSQL("localhost", 3306,"gestcommande");
			
			Object objet = new Produit("1","Mayette","Fraiche Entière",2);
			Object objet2= ps.ChargerDepuisBase("1", "Produit");
			
			assertEquals(objet.getClass(),objet2.getClass());
			assertEquals(((Produit) objet).getVariete(),((Produit) objet2).getVariete());
			assertEquals(((Produit) objet).getType(),((Produit) objet2).getType());
			assertEquals(((Produit) objet).getCalibre(),((Produit) objet2).getCalibre());
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date dC = format.parse("12-05-2014");
			
			Object objet3 = new Commande("00213", (Produit)objet, 266, "filet 1kg",50, null,dC,"1");
			Object objet4 = ps.ChargerDepuisBase("00213", "Commande");
			
			
			assertNotEquals(objet3.getClass(),objet.getClass());
			assertEquals(objet3.getClass(),objet4.getClass());
			
			assertEquals(((Commande)objet3).getId(),((Commande)objet4).getId());
			
			Produit prod1 = ((Commande) objet3).getProduit();
			Produit prod2 = ((Commande) objet4).getProduit();
			
			assertEquals(prod1.getId(),prod2.getId());
			assertEquals(prod1.getVariete(),prod2.getVariete());
			assertEquals(prod1.getType(),prod2.getType());
			assertEquals(prod1.getCalibre(),prod2.getCalibre());
			
			assertEquals(((Commande)objet3).getConditionnement(),((Commande)objet4).getConditionnement());
			assertEquals(((Commande)objet3).getPrixHT(),((Commande)objet4).getPrixHT(),0.1);
			assertEquals(((Commande)objet3).getQte(),((Commande)objet4).getQte());
			assertEquals(((Commande)objet3).getDateEnvoi(), ((Commande)objet4).getDateEnvoi());
			assertEquals(((Commande)objet3).getDateConditionnement(), ((Commande)objet4).getDateConditionnement());
			
			Date dE2 = format.parse("14-10-2014");
			Date dC1 = format.parse("12-05-2015");
			Date dC2 = format.parse("12-10-2014");
			
			Produit p1 = new Produit("1","Mayette","Fraiche Entière",2);
			Commande c1 = new Commande("00213", p1, 266, "filet 1kg",50, null,dC1,"carr15432");
			Commande c2 = new Commande("00214", p1, 398, "filet 1kg",100, dE2,dC2,"carr15432");
			ArrayList<Commande> desCommandes = new ArrayList<Commande>();
			desCommandes.add(c1);
			desCommandes.add(c2);
			
			Distributeur objet5 = new Distributeur("carr15432","carreclerc",desCommandes);
			Object objet6 = ps.ChargerDepuisBase("carr15432", "Distributeur");
			
			assertEquals(objet5.getClass(),objet6.getClass());
			assertNotEquals(objet5.getClass(),objet.getClass());
			
			/*assertEquals(((Distributeur)objet5).getId(),((Distributeur)objet6).getId());
			assertEquals(((Distributeur)objet5).getNom(),((Distributeur)objet6).getNom());
			
			ArrayList<Commande> desCommandes2 = new ArrayList<Commande>();
			desCommandes2 = ((Distributeur)objet6).getCommandes();
			
			assertEquals(desCommandes.size(),desCommandes2.size());
			
			Commande com3 = desCommandes2.get(0);
			assertEquals(com1.getId(),com3.getId());
			assertNotEquals(com2.getId(),com3.getId());
			
			Produit unProd = com3.getProduit();
			assertEquals(prodcom1.getType(),unProd.getType());
			assertNotEquals(prodcom2.getType(),unProd.getType());*/
	}


}
