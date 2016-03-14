package fr.cso.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.cso.model.Equipe;
import fr.cso.model.Resultat;
import fr.cso.model.bean.ClassementEquipe;

public class CalculClassementGroupeTest {

	@Test
	public void calculClassementGroupeTest() {
		Equipe equipeFra = new Equipe();
		equipeFra.setCdEquipe("FRA");
		equipeFra.setCoeffUEFA(10);
		Equipe equipeIta = new Equipe();
		equipeIta.setCdEquipe("ITA");
		equipeIta.setCoeffUEFA(5);
		Equipe equipeAll = new Equipe();
		equipeAll.setCdEquipe("ALL");
		equipeAll.setCoeffUEFA(6);
		
		List<Resultat> listeResultatsGroupe = new ArrayList<Resultat>();
		Resultat resultat1 = new Resultat();
		resultat1.setEquipeDom(equipeFra);
		resultat1.setEquipeExt(equipeIta);
		resultat1.setScoreDom(3);
		resultat1.setScoreExt(1);
		listeResultatsGroupe.add(resultat1);
		Resultat resultat2 = new Resultat();
		resultat2.setEquipeDom(equipeAll);
		resultat2.setEquipeExt(equipeFra);
		resultat2.setScoreDom(0);
		resultat2.setScoreExt(2);
		listeResultatsGroupe.add(resultat2);
		Resultat resultat3 = new Resultat();
		resultat3.setEquipeDom(equipeAll);
		resultat3.setEquipeExt(equipeIta);
		resultat3.setScoreDom(1);
		resultat3.setScoreExt(1);
		listeResultatsGroupe.add(resultat3);
		
		List<ClassementEquipe> mapClassementGroupe = CalculClassementGroupe.getClassementGroupe(listeResultatsGroupe);
	}
	
}
