import static org.junit.Assert.*;

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
	public void testChargerDepuisBase() throws ParseException {
			PersistanceSQL ps = new PersistanceSQL("localhost", 3306,"vdev2");
			
			Object objet = new Produit("var1","fraiche entiere",10);
			Object objet2= ps.ChargerDepuisBase("1", "Produit");
			
			assertEquals(objet.getClass(),objet2.getClass());
			assertEquals(((Produit) objet).getVariete(),((Produit) objet2).getVariete());
			assertEquals(((Produit) objet).getType(),((Produit) objet2).getType());
			assertEquals(((Produit) objet).getCalibre(),((Produit) objet2).getCalibre());
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date dE = format.parse("01-01-2015");
			Date dC = format.parse("01-01-2015");
			
			Object objet3 = new Commande(1, (Produit)objet, 10.F, "cond1",10, dE,dC);
			Object objet4 = ps.ChargerDepuisBase("1", "Commande");
			
			
			assertNotEquals(objet3.getClass(),objet.getClass());
			assertEquals(objet3.getClass(),objet4.getClass());
			
			assertEquals(((Commande)objet3).getId(),((Commande)objet4).getId());
			
			Produit prod1 = ((Commande) objet3).getProduit();
			Produit prod2 = ((Commande) objet4).getProduit();
			
			assertEquals(prod1.getVariete(),prod2.getVariete());
			assertEquals(prod1.getType(),prod2.getType());
			assertEquals(prod1.getCalibre(),prod2.getCalibre());
			
			assertEquals(((Commande)objet3).getConditionnement(),((Commande)objet4).getConditionnement());
			assertEquals(((Commande)objet3).getPrixHT(),((Commande)objet4).getPrixHT(),0.1);
			assertEquals(((Commande)objet3).getQte(),((Commande)objet4).getQte());
			assertEquals(((Commande)objet3).getDateEnvoi(), ((Commande)objet4).getDateEnvoi());
			assertEquals(((Commande)objet3).getDateConditionnement(), ((Commande)objet4).getDateConditionnement());
			
			Date dE2 = format.parse("02-02-2015");
			
			Produit prodcom1 = new Produit("var1","fraiche entiere",10);
			Produit prodcom2 = new Produit("var1","seche entiere",10);
			Commande com1 = new Commande(1, prodcom1, 10.F, "cond1",10, dE,dC);
			Commande com2 = new Commande(2, prodcom2, 20.F, "cond1",20, dE2,dC);
			ArrayList<Commande> desCommandes = new ArrayList<Commande>();
			desCommandes.add(com1);
			desCommandes.add(com2);
			
			Object objet5 = new Distributeur("1","soc1",desCommandes);
			Object objet6 = ps.ChargerDepuisBase("1", "Distributeur");
			
			assertEquals(objet5.getClass(),objet6.getClass());
			assertNotEquals(objet5.getClass(),objet.getClass());
			
			assertEquals(((Distributeur)objet5).getId(),((Distributeur)objet6).getId());
			assertEquals(((Distributeur)objet5).getNom(),((Distributeur)objet6).getNom());
			
			ArrayList<Commande> desCommandes2 = new ArrayList<Commande>();
			desCommandes2 = ((Distributeur)objet6).getCommandes();
			
			assertEquals(desCommandes.size(),desCommandes2.size());
			
			Commande com3 = desCommandes2.get(0);
			assertEquals(com1.getId(),com3.getId());
			assertNotEquals(com2.getId(),com3.getId());
			
			Produit unProd = com3.getProduit();
			assertEquals(prodcom1.getType(),unProd.getType());
			assertNotEquals(prodcom2.getType(),unProd.getType());
	}


}
