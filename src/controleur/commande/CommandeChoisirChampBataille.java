package controleur.commande;

import modele.ChampDeBataille;
import modele.Heroes;
import modele.TERRAINS;
import vue.VueWesnothForBattle;

public class CommandeChoisirChampBataille extends Commande {

	protected Heroes heroes;
	protected TERRAINS champ;
	
	public CommandeChoisirChampBataille(TERRAINS champ, Heroes heroes) {
		this.heroes=heroes;
		this.champ=champ;
		
	}
	@Override
	public void executer() {
		this.heroes.setBataille(new ChampDeBataille(this.champ));
		VueWesnothForBattle.getInstance().afficherBataille(this.champ);
		
	}

}
