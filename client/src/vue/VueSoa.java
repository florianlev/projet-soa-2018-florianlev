package vue;

import javafx.scene.control.Button;

import java.util.List;



import Action.ControleurPrincipal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Vaisseau;
import modele.Voyage;

public class VueSoa extends Application {
	
	protected ControleurPrincipal controleur;
	protected Stage scenePrincipale;
	
	protected List<Vaisseau> listeVaisseaux;
	protected List<Voyage> listeVoyages;
	protected List<Voyage> listeVoyagePourUnVaisseau;

	protected Text texte1;
	
	protected Scene scene ;
	protected VBox vboxVaisseau;
	protected VBox vboxVoyage ;
	
	protected ScrollPane scrollListeVaisseau;
	protected ScrollPane scrollListeVoyage;
	
	protected TabPane racine;
	protected Tab ongletListerVoyages;
	protected Tab ongletListerVaisseau;
	
	protected Label labelVaisseau;
	protected Label labelDepart;
	protected Label labelArrivee;
	protected Label labelDistance;
	protected Label labelPrix;
	protected Label labelVoyage;
	
	protected Button bouttonDetailVaisseau;
	protected Button bouttonRetour;
	
	public VueSoa()
	{
		
	}
	

	
	public void start(Stage scenePrincipale)
	{
		this.scenePrincipale = scenePrincipale;
		
		this.afficerScenePrincipale();

		
	}


	protected void afficerScenePrincipale(){
		controleur = new ControleurPrincipal(this);
		controleur.afficherContenu();

		 vboxVaisseau = new VBox();
		vboxVoyage = new VBox();
		
		vboxVaisseau.setAlignment(Pos.TOP_LEFT);
		vboxVoyage.setAlignment(Pos.TOP_LEFT);
		
		scrollListeVaisseau = new ScrollPane();
		 scrollListeVoyage = new ScrollPane();

		scrollListeVaisseau.setContent(vboxVaisseau);
		scrollListeVoyage.setContent(vboxVoyage);

		 racine = new TabPane();
		
		afficherVaisseau(vboxVaisseau);
		afficherVoyages(vboxVoyage);
		
		 scene = new Scene(racine, 800,800);
		
		ongletListerVaisseau = new Tab("");
		ongletListerVaisseau.setText("Liste des vaisseau");
		ongletListerVaisseau.setContent(scrollListeVaisseau);
		racine.getTabs().add(ongletListerVaisseau);
		
		ongletListerVoyages = new Tab("");
		ongletListerVoyages.setText("Liste des voyages");
		ongletListerVoyages.setContent(scrollListeVoyage);
		racine.getTabs().add(ongletListerVoyages);
		
		scenePrincipale.setScene(scene);
		scenePrincipale.setTitle("Voyages");
		scenePrincipale.show();
		
		
		
	}
	
	protected void afficherVoyages(VBox racine)
	{
		int decalage = 80;
		
		for(Voyage unVoyage : listeVoyages )
		{
			System.out.println(unVoyage);
			 labelVoyage = new Label(unVoyage.getDepart());
			
			labelVoyage.setTranslateX(0);
			labelVoyage.setTranslateY(decalage);
			racine.getChildren().addAll(labelVoyage);
			
		}
	}
	
	protected void afficherVaisseau(VBox racine)
	{
		int decalage = 80;
		
		for(Vaisseau unVaisseau : listeVaisseaux )
		{
			labelVaisseau = new Label(unVaisseau.getNomModele());
			 bouttonDetailVaisseau = new Button("Detail");
			
	
			labelVaisseau.setTranslateX(0);
			labelVaisseau.setTranslateY(decalage);
			
			bouttonDetailVaisseau.setId(String.valueOf(unVaisseau.getIdVaisseau()));
			bouttonDetailVaisseau.setTranslateX(100);
			bouttonDetailVaisseau.setTranslateY(decalage-25);
			this.ajouterEvenementDetail(bouttonDetailVaisseau);
			racine.getChildren().addAll(labelVaisseau, bouttonDetailVaisseau);
			
		}

	}


	protected void ajouterEvenementDetail(Button boutton) 
	{
		boutton.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				afficherSceneDetailVaisseau(Integer.parseInt(boutton.getId()));
			}
		});
		
		
		
		
		
	}


	protected void afficherSceneDetailVaisseau(int id) {
		
		//System.out.println("id " + id);
		
		VBox vboxVaisseau = new VBox();
		
		vboxVaisseau.setAlignment(Pos.TOP_LEFT);
		ScrollPane scrollVaisseau = new ScrollPane();
		scrollVaisseau.setContent(vboxVaisseau);
		afficherDetailsVaisseauEtVoyages(vboxVaisseau, id);

		Scene scene = new Scene(scrollVaisseau, 800,800);
		
		changerScene(scene);
		
	}




	private void afficherDetailsVaisseauEtVoyages(VBox racine, int id) {
		int decalage = 80;
		controleur.afficherVoyagesPourUnVaisseau(id);

		System.out.println(listeVoyagePourUnVaisseau);

		for(Voyage unVoyage : listeVoyagePourUnVaisseau )
		{
			labelDepart = new Label("Depart:" + unVoyage.getDepart());
			labelArrivee = new Label("Arrivee :" + unVoyage.getArrivee());
			labelDistance = new Label("Distance :" + unVoyage.getDistance());
			labelPrix= new Label("Prix :" + unVoyage.getPrix());
			labelDepart.setTranslateX(0);
			labelDepart.setTranslateY(decalage);
			
			 
			
			racine.getChildren().addAll(labelDepart,labelArrivee,labelDistance,labelPrix);
			
		}
		
		bouttonRetour = new Button("Retour");
		bouttonRetour.setTranslateX(20);
		bouttonRetour.setTranslateY(20);
		racine.getChildren().add(bouttonRetour);
		
		bouttonRetour.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event)
			{
				afficerScenePrincipale();
			}
			
			
			
		});
		
	}



	private void changerScene(Scene scene) {
		scenePrincipale.setScene(scene);
		scenePrincipale.setTitle("Voyages");
		scenePrincipale.show();
	}



	public void setListeVaisseaux(List<Vaisseau> listeVaisseaux) {
		this.listeVaisseaux = listeVaisseaux;
	}
	
	public void setListeVoyages (List<Voyage> listeVoyages)
	{
		this.listeVoyages = listeVoyages;

	}
	
	public void setListeVoyagePourUnVaisseau(List<Voyage> listeVoyagePourUnVaisseau) {
		this.listeVoyagePourUnVaisseau = listeVoyagePourUnVaisseau;
		
	}

}
