package fr.cso.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fr.cso.models.beans.ClassementEquipe;
import fr.cso.models.Equipe;
import fr.cso.models.Resultat;

public class CalculClassementGroupe {

	public static List<ClassementEquipe> getClassementGroupe(List<Resultat> listeResultatsGroupe) {
		Map<Equipe, ClassementEquipe> mapClassementGroupe = calculClassementGroupe(listeResultatsGroupe);
		
		// Contrôle des égalités #1
		List<Equipe> listEquipesAEgalite = getEquipesAEgalite(mapClassementGroupe); // TODO si plusieurs équipes à égalité
		if(listEquipesAEgalite.size() != 0) {// TODO si un match au moins a été joué
			
			// Si égalité aux points alors on applique les critères selon la différence particulière
			Map<Equipe, ClassementEquipe> mapClassementEquipeTrieApresEgaliteDiffParticuliere = calculClassementGroupeEgaliteDifferenceParticuliere(
					listeResultatsGroupe, listEquipesAEgalite);
			
			// Contrôle des égalités #2
			List<Equipe> listEquipesAEgaliteDiffParticuliere = getEquipesAEgalite(mapClassementEquipeTrieApresEgaliteDiffParticuliere); 
			if(listEquipesAEgaliteDiffParticuliere.size() == 0) {
				// Si pas d'égalité, alors maj du classement général pour les équipes à égalité
				mapClassementGroupe = updateRankingsGroupeCompletApresEgalites(mapClassementGroupe, listEquipesAEgalite, mapClassementEquipeTrieApresEgaliteDiffParticuliere);
			}
			else {
				// Si égalité alors on applique les critères selon la différence globale
				Map<Equipe, ClassementEquipe> mapClassementEquipeTrieApresEgaliteDiffGlobale = calculClassementGroupeEgaliteDifferenceGlobale(listeResultatsGroupe,
						listEquipesAEgaliteDiffParticuliere);
				
				// Contrôle des égalités #3
				List<Equipe> listEquipesAEgaliteDiffGlobale = getEquipesAEgalite(mapClassementEquipeTrieApresEgaliteDiffGlobale);
				if(listEquipesAEgaliteDiffGlobale.size() == 0) {
					// Si pas d'égalité, alors maj du classement général pour les équipes à égalité
					mapClassementGroupe = updateRankingsGroupeCompletApresEgalites(mapClassementGroupe, listEquipesAEgaliteDiffParticuliere, mapClassementEquipeTrieApresEgaliteDiffGlobale);
				}
				else {
					// Si égalité alors on applique le critères du coeff UEFA
					Map<Equipe, ClassementEquipe> mapClassementEquipeTrieApresEgaliteDiffCoeffUEFA = calculClassementGroupeEgaliteCoeffUEFA(listEquipesAEgaliteDiffGlobale, mapClassementEquipeTrieApresEgaliteDiffGlobale);
					
					// Maj du classement général
					mapClassementGroupe = updateRankingsGroupeCompletApresEgalites(mapClassementGroupe, listEquipesAEgaliteDiffGlobale, mapClassementEquipeTrieApresEgaliteDiffCoeffUEFA);
				}
			}
		}
		
		return new ArrayList<ClassementEquipe>(mapClassementGroupe.values());
	}

	private static Map<Equipe, ClassementEquipe> calculClassementGroupe(List<Resultat> listeResultatsGroupe) {
		// Construction du classement d'un groupe
		Map<Equipe, ClassementEquipe> classementGroupe = calculDetailsEquipeParMatch(listeResultatsGroupe);
		
		// Classement des équipes selon les points
		List<Map.Entry<Equipe, ClassementEquipe>> listeClassementEquipe = new LinkedList<Map.Entry<Equipe, ClassementEquipe>>(classementGroupe.entrySet());
		Collections.sort(listeClassementEquipe, new Comparator<Map.Entry<Equipe, ClassementEquipe>>() {
			public int compare(Map.Entry<Equipe, ClassementEquipe> o1, Map.Entry<Equipe, ClassementEquipe> o2) {
				return (o2.getValue().getNbPoints() - o1.getValue().getNbPoints());
			}
		});
		
		// Détermination des rankings selon les points
		Map<Equipe, ClassementEquipe> mapClassementGroupe = new LinkedHashMap<Equipe, ClassementEquipe>();
		ClassementEquipe prevClassementEquipe = null;
	    int ranking = 1;
	    int cpt = 0;
		for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = listeClassementEquipe.iterator(); it.hasNext();) {
			Map.Entry<Equipe, ClassementEquipe> entry = it.next();

	        if (prevClassementEquipe != null) {	        	
		        if (prevClassementEquipe.getNbPoints() != entry.getValue().getNbPoints()) {
		        	ranking = cpt + 1;
		        }
	        }
	        prevClassementEquipe = entry.getValue();
	        entry.getValue().setRanking(ranking);
	        cpt++;
			
			mapClassementGroupe.put(entry.getKey(), entry.getValue());
		}
		
		System.out.println("Classement Groupe");
		afficherClassementGroupe(mapClassementGroupe.values());
		
		return mapClassementGroupe;
	}

	private static Map<Equipe, ClassementEquipe> calculClassementGroupeEgaliteDifferenceParticuliere(
			List<Resultat> listeResultatsPoule, List<Equipe> listEquipesAEgalite) {
		
		// On récupère la liste des résultats des équipes à égalité
		List<Resultat> listeResultatsEquipesAEgalite = new ArrayList<Resultat>();
		for(int i=0 ; i <= listEquipesAEgalite.size()-2 ; i++) {
			Equipe equipe1 = listEquipesAEgalite.get(i);
			for(int j=i+1 ; j <= listEquipesAEgalite.size()-1 ; j++) {
				Equipe equipe2 = listEquipesAEgalite.get(j);
				for(Resultat resultatMatch : listeResultatsPoule) {
					if(resultatMatch.getEquipeDom().getCdEquipe().equals(equipe1.getCdEquipe()) && resultatMatch.getEquipeExt().getCdEquipe().equals(equipe2.getCdEquipe())
							|| resultatMatch.getEquipeDom().getCdEquipe().equals(equipe2.getCdEquipe()) && resultatMatch.getEquipeExt().getCdEquipe().equals(equipe1.getCdEquipe())) {
						listeResultatsEquipesAEgalite.add(resultatMatch);
					}
				}
			}
		}
		
		// On calcul un nouveau classement par rapport aux résultats des matchs entre les équipes à égalité
		Map<Equipe, ClassementEquipe> classementEquipesAEgalite = calculDetailsEquipeParMatch(listeResultatsEquipesAEgalite);
		
		// Tri selon les règles en cas d’égalité entre plusieurs équipes d’un même groupe (http://euro2016-france.net/reglement-euro-football/)
		List<Map.Entry<Equipe, ClassementEquipe>> listeClassementEquipe = new LinkedList<Map.Entry<Equipe, ClassementEquipe>>(classementEquipesAEgalite.entrySet());
		Collections.sort(listeClassementEquipe, new Comparator<Map.Entry<Equipe, ClassementEquipe>>() {
			public int compare(Map.Entry<Equipe, ClassementEquipe> o1, Map.Entry<Equipe, ClassementEquipe> o2) {
				if(Math.abs(o2.getValue().getNbPoints() - o1.getValue().getNbPoints()) != 0) {
					return (o2.getValue().getNbPoints() - o1.getValue().getNbPoints());
				}
				else {
					if(Math.abs(o2.getValue().getDifferenceButs() - o1.getValue().getDifferenceButs()) != 0) {
						return (o2.getValue().getDifferenceButs() - o1.getValue().getDifferenceButs());
					}
					else {
						if(Math.abs(o2.getValue().getNbButsPour() - o1.getValue().getNbButsPour()) != 0) {
							return (o2.getValue().getNbButsPour() - o1.getValue().getNbButsPour());
						}
						else {
							return 0;
						}
					}
				}
			}
		});
		
		// Détermination du ranking entre les équipes à égalité
		Map<Equipe, ClassementEquipe> mapClassementEquipeTrie = new LinkedHashMap<Equipe, ClassementEquipe>();
		ClassementEquipe prevClassementEquipe = null;
	    int ranking = 1;
	    int cpt = 0;
		for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = listeClassementEquipe.iterator(); it.hasNext();) {
			Map.Entry<Equipe, ClassementEquipe> entry = it.next();
			
	        if (prevClassementEquipe != null) {	        	
				if(prevClassementEquipe.getNbPoints() != entry.getValue().getNbPoints()) {
					ranking = cpt + 1;
				}
				else {
					if(prevClassementEquipe.getDifferenceButs() != entry.getValue().getDifferenceButs()) {
						ranking = cpt + 1;
					}
					else {
						if(prevClassementEquipe.getNbButsPour() != entry.getValue().getNbButsPour()) {
							ranking = cpt + 1;
						}
					}
				}
	        }
	        prevClassementEquipe = entry.getValue();
	        entry.getValue().setRanking(ranking);
	        cpt++;
			
			mapClassementEquipeTrie.put(entry.getKey(), entry.getValue());
		}
		
		System.out.println("ClassementGroupeEgaliteDifferenceParticuliere");
		afficherClassementGroupe(mapClassementEquipeTrie.values());
		
		return mapClassementEquipeTrie;
	}

	private static Map<Equipe, ClassementEquipe> calculClassementGroupeEgaliteDifferenceGlobale(List<Resultat> listeResultatsPoule,
			List<Equipe> listEquipesAEgalite) {
		
		// On recalcule le classement général sur tous les matchs du groupe
		Map<Equipe, ClassementEquipe> classementPoule = calculDetailsEquipeParMatch(listeResultatsPoule);
		
		// Classement des équipes selon les points puis la différence de buts puis le nombre de buts marqués
		List<Map.Entry<Equipe, ClassementEquipe>> listeClassementEquipe = new LinkedList<Map.Entry<Equipe, ClassementEquipe>>(classementPoule.entrySet());
		Collections.sort(listeClassementEquipe, new Comparator<Map.Entry<Equipe, ClassementEquipe>>() {
			public int compare(Map.Entry<Equipe, ClassementEquipe> o1, Map.Entry<Equipe, ClassementEquipe> o2) {
				if(Math.abs(o2.getValue().getNbPoints() - o1.getValue().getNbPoints()) != 0) {
					return (o2.getValue().getNbPoints() - o1.getValue().getNbPoints());
				}
				else {
					if(Math.abs(o2.getValue().getDifferenceButs() - o1.getValue().getDifferenceButs()) != 0) {
						return (o2.getValue().getDifferenceButs() - o1.getValue().getDifferenceButs());
					}
					else {
						if(Math.abs(o2.getValue().getNbButsPour() - o1.getValue().getNbButsPour()) != 0) {
							return (o2.getValue().getNbButsPour() - o1.getValue().getNbButsPour());
						}
						else {
							return 0;
						}
					}
				}
			}
		});
		
		// Détermination du nouveau ranking pour les équipes à égalité
		Map<Equipe, ClassementEquipe> mapClassementEquipeTrie = new LinkedHashMap<Equipe, ClassementEquipe>();
		ClassementEquipe prevClassementEquipe = null;
	    int ranking = 1;
	    int cpt = 0;
		for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = listeClassementEquipe.iterator(); it.hasNext();) {
			Map.Entry<Equipe, ClassementEquipe> entry = it.next();
			if(listEquipesAEgalite.contains(entry.getKey())) {

		    	if(prevClassementEquipe != null) {
			        if (prevClassementEquipe.getNbPoints() != entry.getValue().getNbPoints()) {
			        	ranking = cpt + 1;
			        }
			        else {
						if(prevClassementEquipe.getDifferenceButs() != entry.getValue().getDifferenceButs()) {
							ranking = cpt + 1;
						}
						else {
							if(prevClassementEquipe.getNbButsPour() != entry.getValue().getNbButsPour()) {
								ranking = cpt + 1;
							}
						}
			        }
		        }
		        prevClassementEquipe = entry.getValue();
		        entry.getValue().setRanking(ranking);
		        cpt++;
				
				mapClassementEquipeTrie.put(entry.getKey(), entry.getValue());
			}
		}
		
		System.out.println("ClassementGroupeEgaliteDifferenceGlobale");
		afficherClassementGroupe(mapClassementEquipeTrie.values());
		
		return mapClassementEquipeTrie;
	}

	private static Map<Equipe, ClassementEquipe> calculClassementGroupeEgaliteCoeffUEFA(List<Equipe> listEquipesAEgalite, 
			Map<Equipe, ClassementEquipe> mapClassementEquipesAEgalite) {
		
		// Classement des équipes selon le coeff UEFA
		Collections.sort(listEquipesAEgalite, new Comparator<Equipe>() {
			public int compare(Equipe o1, Equipe o2) {
				return o2.getCoeffUEFA() - o1.getCoeffUEFA();
			}
		});
		
		// Maj du classement général du groupe
		int cpt = 0;
		for(Equipe equipe : listEquipesAEgalite) {
			mapClassementEquipesAEgalite.get(equipe).setRanking(mapClassementEquipesAEgalite.get(equipe).getRanking() + cpt);
			cpt++;
		}
		
		System.out.println("ClassementGroupeEgaliteCoeffUEFA");
		afficherClassementGroupe(mapClassementEquipesAEgalite.values());
		
		return mapClassementEquipesAEgalite;
	}
	
	private static Map<Equipe, ClassementEquipe> calculDetailsEquipeParMatch(List<Resultat> listeResultatsMatch) {
		Map<Equipe, ClassementEquipe> mapClassementPoule = new HashMap<Equipe, ClassementEquipe>();		
		
		for(Resultat resultatMatch : listeResultatsMatch) {
			
			// Initialisation Dom
			Equipe equipeDom = resultatMatch.getEquipeDom();
			if(!mapClassementPoule.containsKey(equipeDom)) {
				mapClassementPoule.put(equipeDom, new ClassementEquipe(equipeDom.getNom()));
			}
			ClassementEquipe classementEquipeDom = mapClassementPoule.get(equipeDom);
			Integer scoreDom = resultatMatch.getScoreDom();
			
			// Initialisation Ext
			Equipe equipeExt = resultatMatch.getEquipeExt();
			if(!mapClassementPoule.containsKey(equipeExt)) {
				mapClassementPoule.put(equipeExt, new ClassementEquipe(equipeExt.getNom()));
			}
			ClassementEquipe classementEquipeExt = mapClassementPoule.get(equipeExt);
			Integer scoreExt = resultatMatch.getScoreExt();
			
			if(scoreDom != null && scoreExt != null) {
				// Matchs joués
				classementEquipeDom.setNbMatchsJoues(classementEquipeDom.getNbMatchsJoues() + 1);
				classementEquipeExt.setNbMatchsJoues(classementEquipeExt.getNbMatchsJoues() + 1);
				
				// Points et VND			
				if(scoreDom > scoreExt) {
					classementEquipeDom.setNbPoints(classementEquipeDom.getNbPoints() + 3);
					classementEquipeDom.setNbVictoiresDom(classementEquipeDom.getNbVictoiresDom() + 1);
					classementEquipeDom.setNbVictoires(classementEquipeDom.getNbVictoires() + 1);
					
					classementEquipeExt.setNbPoints(classementEquipeExt.getNbPoints() + 0);
					classementEquipeExt.setNbDefaitesExt(classementEquipeExt.getNbDefaitesExt() + 1);
					classementEquipeExt.setNbDefaites(classementEquipeExt.getNbDefaites() + 1);
				} 
				else if (scoreDom == scoreExt) {
					classementEquipeDom.setNbPoints(classementEquipeDom.getNbPoints() + 1);
					classementEquipeDom.setNbNulsDom(classementEquipeDom.getNbNulsDom() + 1);
					classementEquipeDom.setNbNuls(classementEquipeDom.getNbNuls() + 1);
					
					classementEquipeExt.setNbPoints(classementEquipeExt.getNbPoints() + 1);
					classementEquipeExt.setNbNulsExt(classementEquipeExt.getNbNulsExt() + 1);
					classementEquipeExt.setNbNuls(classementEquipeExt.getNbNuls() + 1);
				}
				else if (scoreDom < scoreExt) {
					classementEquipeDom.setNbPoints(classementEquipeDom.getNbPoints() + 0);
					classementEquipeDom.setNbDefaitesDom(classementEquipeDom.getNbDefaitesDom() + 1);
					classementEquipeDom.setNbDefaites(classementEquipeDom.getNbDefaites() + 1);
					
					classementEquipeExt.setNbPoints(classementEquipeExt.getNbPoints() + 3);
					classementEquipeExt.setNbVictoiresExt(classementEquipeExt.getNbVictoiresExt() + 1);
					classementEquipeExt.setNbVictoires(classementEquipeExt.getNbVictoires() + 1);
				}
				
				// BP, BC et DB
				classementEquipeDom.setNbButsPour(classementEquipeDom.getNbButsPour() + scoreDom);
				classementEquipeDom.setNbButsContre(classementEquipeDom.getNbButsContre() - scoreExt);
				classementEquipeDom.setDifferenceButs(classementEquipeDom.getDifferenceButs() + (scoreDom - scoreExt));
				
				classementEquipeExt.setNbButsPour(classementEquipeExt.getNbButsPour() + scoreExt);
				classementEquipeExt.setNbButsContre(classementEquipeExt.getNbButsContre() - scoreDom);
				classementEquipeExt.setDifferenceButs(classementEquipeExt.getDifferenceButs() + (scoreExt - scoreDom));
			}
		}
		
		return mapClassementPoule;
	}
	
	private static ArrayList<Equipe> getEquipesAEgalite(Map<Equipe, ClassementEquipe> classementGroupe) {
		Set<Equipe> hsetEquipesAEgalite = new HashSet<Equipe>();
		Entry<Equipe, ClassementEquipe> prevEntry = null;
		for (Entry<Equipe, ClassementEquipe> entry : classementGroupe.entrySet()) {
	        if (prevEntry != null) {	        	
		        if (prevEntry.getValue().getRanking() == entry.getValue().getRanking()) {
		        	hsetEquipesAEgalite.add(prevEntry.getKey());
		        	hsetEquipesAEgalite.add(entry.getKey());
		        }
	        }
	        prevEntry = entry;
		}
	    
		ArrayList<Equipe> listeEquipesAEgalite = new ArrayList<Equipe>();
		listeEquipesAEgalite.addAll(hsetEquipesAEgalite);
		return listeEquipesAEgalite;
	}
	
	private static Map<Equipe, ClassementEquipe> updateRankingsGroupeCompletApresEgalites(Map<Equipe, ClassementEquipe> mapClassementGroupe,
			List<Equipe> listEquipesAEgalite, Map<Equipe, ClassementEquipe> mapClassementEquipesAEgalite) {
		
		for(Equipe equipe : listEquipesAEgalite) {
			mapClassementGroupe.get(equipe).setRanking(mapClassementGroupe.get(equipe).getRanking() + (mapClassementEquipesAEgalite.get(equipe).getRanking()-1));
		}
		
		// Tri selon les nouveaux rankings
		List<Map.Entry<Equipe, ClassementEquipe>> list3 = new LinkedList<Map.Entry<Equipe, ClassementEquipe>>(mapClassementGroupe.entrySet());
		Collections.sort(list3, new Comparator<Map.Entry<Equipe, ClassementEquipe>>() {
			public int compare(Map.Entry<Equipe, ClassementEquipe> o1, Map.Entry<Equipe, ClassementEquipe> o2) {
				return (o1.getValue().getRanking() - o2.getValue().getRanking());
			}
		});
		mapClassementGroupe = new LinkedHashMap<Equipe, ClassementEquipe>();
		for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = list3.iterator(); it.hasNext();) {
			Map.Entry<Equipe, ClassementEquipe> entry = it.next();
			mapClassementGroupe.put(entry.getKey(), entry.getValue());
		}
		
		System.out.println("Classement Groupe Final");
		afficherClassementGroupe(mapClassementGroupe.values());
		
		return mapClassementGroupe;
	}
	
	private static void afficherClassementGroupe(Collection<ClassementEquipe> listeClassementGroupe) {
		StringBuilder strTitres = new StringBuilder();
		strTitres.append("Rk" + "\t");
		strTitres.append("Eq" + "\t");
		strTitres.append("Pts" + "\t");
		strTitres.append("J" + "\t");
		strTitres.append("VDom" + "\t");
		strTitres.append("NDom" + "\t");
		strTitres.append("DDom" + "\t");
		strTitres.append("VExt" + "\t");
		strTitres.append("NExt" + "\t");
		strTitres.append("DExt" + "\t");
		strTitres.append("VTot" + "\t");
		strTitres.append("NTot" + "\t");
		strTitres.append("DTot" + "\t");
		strTitres.append("BP" + "\t");
		strTitres.append("BC" + "\t");
		strTitres.append("DB" + "\t");
		System.out.println(strTitres.toString());
		
		for(ClassementEquipe classementEquipe : listeClassementGroupe) {
			System.out.println(classementEquipe.toString());
		}
		
		System.out.print("\n");
	}
}
