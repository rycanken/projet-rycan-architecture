package controleur.commande;

import modele.HEROES;
import modele.HeroDeBataille;
import modele.Heroes;
import vue.VueWesnothForBattle;

public class CommandeDeployerPersonnage extends Commande {
	protected double x;
	protected double y;
	protected HeroDeBataille hero;
	protected HEROES heroes;
	protected Heroes heroList;
	
	public CommandeDeployerPersonnage(HEROES heroes, double x, double y,Heroes heroList) {
		this.x =x;
		this.y =y;
		this.heroes = heroes;
		this.heroList=heroList;
	}

	@Override
	public void executer() {
		HeroDeBataille nouveauHero = new HeroDeBataille(this.heroes, x, y);
		this.heroList.getHero().add(nouveauHero);
		VueWesnothForBattle.getInstance().PlacerChampHeroes(this.heroes,x,y);
		
	}

}
