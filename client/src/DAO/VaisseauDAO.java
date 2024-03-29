package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.Vaisseau;

public class VaisseauDAO implements VaisseauURL{
	
	List<Vaisseau> listeVaisseau;
	String xml = null;

	
	
	public VaisseauDAO()
	{
		listeVaisseau = new ArrayList<>();
		
	}
	
	public List<Vaisseau> rechercherVaisseau()
	{
		try
		{
			URL urlListeVaisseau = new URL(URL_VAISSEAU);
			String derniereBalise = "</vaisseaux>";
			InputStream flux = urlListeVaisseau.openConnection().getInputStream();
			Scanner lecteur = new Scanner(flux);
			lecteur.useDelimiter(derniereBalise);
			xml = lecteur.next() + derniereBalise;
			
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			DocumentBuilder parseur =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
			@SuppressWarnings("deprecation")
			Document document = parseur.parse(new StringBufferInputStream(xml));
			String racine = document.getDocumentElement().getNodeName();
			System.out.println(racine);
			
			NodeList listeNoeudsVaisseau = document.getElementsByTagName("vaisseau");
			for(int position = 0; position < listeNoeudsVaisseau.getLength(); position++)
			{
				Vaisseau vaisseau = new Vaisseau();
				Element noeudVaisseau = (Element)listeNoeudsVaisseau.item(position);
				String id = noeudVaisseau.getElementsByTagName("id").item(0).getTextContent();

				String nomModele = noeudVaisseau.getElementsByTagName("nomModele").item(0).getTextContent();
				vaisseau.setNomModele(nomModele);
				String kilometrage = noeudVaisseau.getElementsByTagName("kilometrage").item(0).getTextContent();
				String nombreDePlace = noeudVaisseau.getElementsByTagName("nombrePlace").item(0).getTextContent();
				String vitesse = noeudVaisseau.getElementsByTagName("vitesse").item(0).getTextContent();

				//System.out.println("ID : " + id);
				/*System.out.println("nom : " + nomModele);
				System.out.println("kilometrage : " + kilometrage);
				System.out.println("nombreDePlace : " + nombreDePlace);
				System.out.println("vitesse : " + vitesse);
				*/
				//Vaisseau vaisseau = new Vaisseau();
				vaisseau.setIdVaisseau(Integer.parseInt(id));

				vaisseau.setNomModele(nomModele);
				
				
				
				System.out.println(vaisseau.getIdVaisseau());
				listeVaisseau.add(vaisseau);
				
			}

			
			
		}catch (ParserConfigurationException e) 
		{	
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listeVaisseau);
		return listeVaisseau;

	}

}
