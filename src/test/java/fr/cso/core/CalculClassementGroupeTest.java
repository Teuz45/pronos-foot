package fr.cso.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.cso.models.beans.ClassementEquipe;
import fr.cso.models.beans.Equipe;
import fr.cso.models.beans.Resultat;

public class CalculClassementGroupeTest {

	@Test
	public void calculClassementGroupeTest() {
		Equipe equipeFra = new Equipe("FRA");
		equipeFra.setCoeffUEFA(10);
		Equipe equipeIta = new Equipe("ITA");
		equipeIta.setCoeffUEFA(5);
		Equipe equipeAll = new Equipe("ALL");
		equipeAll.setCoeffUEFA(6);
		
		List<Resultat> listeResultatsGroupe = new ArrayList<Resultat>();
		listeResultatsGroupe.add(new Resultat(equipeFra, equipeIta, 3, 1, null, null));
		listeResultatsGroupe.add(new Resultat(equipeAll, equipeFra, 0, 2, null, null));
		listeResultatsGroupe.add(new Resultat(equipeAll, equipeIta, 1, 1, null, null));
		
		List<ClassementEquipe> mapClassementGroupe = CalculClassementGroupe.getClassementGroupe(listeResultatsGroupe);
	}
	
}
