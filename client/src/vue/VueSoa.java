package vue;

import javafx.scene.control.Button;
import java.io.Console;
import java.util.List;

import Action.ControleurPrincipal;
import javafx.application.Application;
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

	protected Text texte1;
	
	protected Tab ongletListerVoyages;
	protected Tab ongletListerVaisseau;
	
	public VueSoa()
	{
		controleur = new ControleurPrincipal(this);
	}
	



	@Override
	public void start(Stage scenePrincipale){
		controleur.afficherContenu();

		this.scenePrincipale = scenePrincipale;
		VBox vboxVaisseau = new VBox();
		VBox vboxVoyage = new VBox();
		
		vboxVaisseau.setAlignment(Pos.TOP_LEFT);
		vboxVoyage.setAlignment(Pos.TOP_LEFT);
		
		
		
				
		
		ScrollPane scrollListeVaisseau = new ScrollPane();
		ScrollPane scrollListeVoyage = new ScrollPane();

		scrollListeVaisseau.setContent(vboxVaisseau);
		scrollListeVoyage.setContent(vboxVoyage);

		TabPane racine = new TabPane();
		
		afficherVaisseau(vboxVaisseau);
		afficherVoyages(vboxVoyage);
		
		Scene scene = new Scene(racine, 800,800);
		
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
			Label labelVoyage = new Label(unVoyage.getDepart());
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
			Label labelVaisseau = new Label(unVaisseau.getNomModele());
			Button bouttonDetailVaisseau = new Button("Detail");
			
	
			labelVaisseau.setTranslateX(0);
			labelVaisseau.setTranslateY(decalage);
			
			bouttonDetailVaisseau.setId(String.valueOf(unVaisseau.getIdVaisseau()));
			bouttonDetailVaisseau.setTranslateX(100);
			bouttonDetailVaisseau.setTranslateY(decalage-25);
			racine.getChildren().addAll(labelVaisseau, bouttonDetailVaisseau);
			
		}

	}


	public void setListeVaisseaux(List<Vaisseau> listeVaisseaux) {
		this.listeVaisseaux = listeVaisseaux;
	}
	
	public void setListeVoyages (List<Voyage> listeVoyages)
	{
		this.listeVoyages = listeVoyages;

	}

}
