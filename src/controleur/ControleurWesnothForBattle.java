package controleur;

import java.util.Stack;

import com.sun.media.jfxmedia.logging.Logger;

import architecture.Controleur;
import controleur.commande.CommandeChoisirChampBataille;
import controleur.commande.Commande;
import controleur.commande.CommandeDeployerPersonnage;
import donnee.Exporteur;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import modele.ChampDeBataille;
import modele.HEROES;
import modele.NomBataille;
import modele.HeroDeBataille;
import modele.Heroes;
import modele.MONSTRES;
import modele.Monstre;
import modele.TERRAINS;
import vue.VueWesnothForBattle;

public class ControleurWesnothForBattle extends Controleur{
	
	protected Heroes heroes = new Heroes();

	public ControleurWesnothForBattle()
	{
		Logger.logMsg(Logger.INFO, "new ControleurJardinator()");
	}
	
	
	public void initialiser()
	{
		
	}
	HEROES heroChoisie = null;
	MONSTRES monstreChoisit = null;
	TERRAINS terrainChoisie = null;
	String nomBataille = null;
	public void notifierClicDrakan() {
		//VueWesnothForBattle.getInstance().PlacerChampDrakan();
		this.heroChoisie = HEROES.DRAKAN;
		
	}
	protected Stack<Commande> historique = new Stack<Commande>();
	
	public void notifierClicChamp(double x, double y) {
		Commande commande = new CommandeDeployerPersonnage(heroChoisie,x,y,this.heroes);
		commande.executer();
		historique.add(commande);
		
		Monstre monstre = new Monstre(this.monstreChoisit, x, y);
		VueWesnothForBattle.getInstance().PlacerChampMonstre(monstreChoisit, x, y);
	}
	public void notifierChangerChamp(TERRAINS terrain) {
		
		Commande commande = new CommandeChoisirChampBataille(terrain, this.heroes);
		commande.executer();
		historique.add(commande);
	}
/*
	public void notifierClicDuelist() {
		this.heroChoisie = HEROES.DUELIST;
		
	}

	public void notifierClicHeroElf() {
		this.heroChoisie = HEROES.HEROELF;
		
	}
	
	public void notifierClicHunterOndin() {
		this.heroChoisie = HEROES.HUNTERONDIN;
	}

	public void notifierClicWarriorDwarf() {
		this.heroChoisie = HEROES.WARRIORDWARF;
		
	}

	public void notifierClicWhiteMage() {
		this.heroChoisie = HEROES.WHITEMAGE;
		
	}*/
	
	/*public void notifierClicWraith() {
		this.monstreChoisit = MONSTRES.WRAITH;
		System.out.println("Click on Wraith registered in controler");
	}*/

	public void notiferClicPlaine() {
		this.terrainChoisie = TERRAINS.PLAINE;
		
	}


	public void notifierClicChateau() {
		this.terrainChoisie = TERRAINS.CHATEAU;
		
	}


	public void notifierclicHero(HEROES heroChoisie) {
		this.heroChoisie = heroChoisie;
		this.monstreChoisit =null;
		
	}
	
	public void notifierClicMonstre(MONSTRES monstreChoisit) {
		this.monstreChoisit = monstreChoisit;
		this.heroChoisie = null;
		System.out.println("Monstre choisit: "+monstreChoisit);
	}


	public void notifierClicSauvegarder() {
		System.out.println("La sauvegarde s'en vient!");
		Exporteur exporteur = new Exporteur();
		exporteur.sauvegarder(this.heroes);
	}


	public void notifierNomBataille(String text) {
		System.out.println("Yo save name");
		this.nomBataille = text;
		this.heroes.setNomChoisie(new NomBataille(nomBataille));
	}


	public void notifierActionUndo() {
		if(!historique.isEmpty()) {
			Commande commande = historique.pop();
			commande.annuler();
			annulations.add(commande);
		}
		
		
	}
	protected Stack<Commande> annulations = new Stack<Commande>();

	public void notifierActionRedo() {
		if(!annulations.isEmpty()) {
			Commande commande = annulations.pop();
			commande.executer();
			historique.add(commande);
		}
		
	}


	

}
