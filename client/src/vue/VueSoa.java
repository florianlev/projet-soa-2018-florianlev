package vue;

import java.util.List;

import Action.ControleurPrincipal;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
	
	public VueSoa()
	{
		controleur = new ControleurPrincipal(this);
	}
	



	@Override
	public void start(Stage scenePrincipale){
		controleur.afficherContenu();

		this.scenePrincipale = scenePrincipale;
		VBox vboxVaisseau = new VBox();
		
		vboxVaisseau.setAlignment(Pos.TOP_LEFT);
		
		ScrollPane scrollListeVaisseau = new ScrollPane();
		
		scrollListeVaisseau.setContent(vboxVaisseau);
		afficherVaisseau(vboxVaisseau);
		
		Scene scene = new Scene(scrollListeVaisseau, 300,250);
		
		scenePrincipale.setScene(scene);
		scenePrincipale.setTitle("Planet");
		scenePrincipale.show();
		
		
		
	}
	
	protected void afficherVaisseau(VBox racine)
	{
		int decalage = 80;
		
		for(Vaisseau unVaisseau : listeVaisseaux )
		{
			Label labelVaisseau = new Label(unVaisseau.getNomModele());
			labelVaisseau.setTranslateX(0);
			labelVaisseau.setTranslateY(decalage);
			racine.getChildren().addAll(labelVaisseau);
			
		}
		
		for(Voyage unVoyage : listeVoyages)
		{
			System.out.println(unVoyage.getDepart());
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
