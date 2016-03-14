package fr.cso.core;

import java.math.BigDecimal;
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

import fr.cso.model.bean.ClassementEquipe;
import fr.cso.model.bean.ResultatMatch;
import fr.cso.model.Equipe;
import fr.cso.model.Resultat;

public class CalculClassementGroupe {

	private static int POIDS_COEFF_UEFA = 0 ;
	private static int POIDS_BUTS_POUR_GLOBAL = POIDS_COEFF_UEFA + 6 ;
	private static int POIDS_DIFF_BUTS_GLOBAL = POIDS_BUTS_POUR_GLOBAL + 2;
	private static int POIDS_BUTS_POUR_PARTICULIER = POIDS_DIFF_BUTS_GLOBAL + 3;
	private static int POIDS_DIFF_BUTS_PARTICULIER = POIDS_BUTS_POUR_PARTICULIER + 2;
	private static int POIDS_NB_POINTS_PARTICULIER = POIDS_DIFF_BUTS_PARTICULIER + 3;
	private static int POIDS_NB_POINTS_GLOBAL = POIDS_NB_POINTS_PARTICULIER + 2;
	
	private static Comparator<Map.Entry<Equipe, ClassementEquipe>> comparatorPointsPonderes = new Comparator<Map.Entry<Equipe, ClassementEquipe>>() {
		public int compare(Map.Entry<Equipe, ClassementEquipe> o1, Map.Entry<Equipe, ClassementEquipe> o2) {
			return o2.getValue().getNbPointsPonderes().compareTo(o1.getValue().getNbPointsPonderes());
		}
	};
	
	public static List<ClassementEquipe> getClassementGroupeFromResultat(List<Resultat> listeResultatsGroupe) {
		
		List<ClassementEquipe> classementGroupe = null;
		
		if(listeResultatsGroupe!=null) {
			List<ResultatMatch> listeResultatsMatch = new ArrayList<>();
			for(Resultat resultat : listeResultatsGroupe) {
				ResultatMatch resultatMatch = new ResultatMatch();
				resultatMatch.setEquipeDom(resultat.getEquipeDom());
				resultatMatch.setEquipeExt(resultat.getEquipeExt());
				resultatMatch.setScoreDom(resultat.getScoreDom());
				resultatMatch.setScoreExt(resultat.getScoreExt());
				resultatMatch.setScorePenDom(resultat.getScorePenDom());
				resultatMatch.setScorePenExt(resultat.getScorePenExt());
				listeResultatsMatch.add(resultatMatch);
			}
			
			classementGroupe = getClassementGroupe(listeResultatsMatch);
		}
		
		return classementGroupe;
	}
	
	private static List<ClassementEquipe> getClassementGroupe(List<ResultatMatch> listeResultatsGroupe) {
		
		Map<Equipe, ClassementEquipe> mapClassementGroupe = calculClassementGroupe(listeResultatsGroupe);
		
		// Contrôle des égalités #1
		List<List<Equipe>> listesEquipesAEgalite = getListesEquipesAEgalite(mapClassementGroupe);
		
		if(listesEquipesAEgalite==null) {
			System.out.println("Classement Groupe");
			afficherClassementGroupe(mapClassementGroupe.values());
			
			return new ArrayList<ClassementEquipe>(mapClassementGroupe.values());
		}
		else {
			for(List<Equipe> listeEquipesAEgalite : listesEquipesAEgalite) {
				// On récupère la liste des résultats des équipes à égalité
				List<ResultatMatch> listeResultatsMatchsEquipesAEgalite = new ArrayList<ResultatMatch>();
				for(int i=0 ; i <= listeEquipesAEgalite.size()-2 ; i++) {
					Equipe equipe1 = listeEquipesAEgalite.get(i);
					for(int j=i+1 ; j <= listeEquipesAEgalite.size()-1 ; j++) {
						Equipe equipe2 = listeEquipesAEgalite.get(j);
						for(ResultatMatch resultatMatch : listeResultatsGroupe) {
							if(resultatMatch.getEquipeDom().getCdEquipe().equals(equipe1.getCdEquipe()) && resultatMatch.getEquipeExt().getCdEquipe().equals(equipe2.getCdEquipe())
									|| resultatMatch.getEquipeDom().getCdEquipe().equals(equipe2.getCdEquipe()) && resultatMatch.getEquipeExt().getCdEquipe().equals(equipe1.getCdEquipe())) {
								listeResultatsMatchsEquipesAEgalite.add(resultatMatch);
							}
						}
					}
				}
				
				// On calcul un nouveau classement par rapport aux résultats des matchs entre les équipes à égalité
				Map<Equipe, ClassementEquipe> classementEquipesAEgalite = calculDetailsEquipeParMatch(listeResultatsMatchsEquipesAEgalite);
				
				// Pondération particulière
				for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = classementEquipesAEgalite.entrySet().iterator(); it.hasNext();) {
					Map.Entry<Equipe, ClassementEquipe> entry = it.next();
					BigDecimal nbPointsPonderes = mapClassementGroupe.get(entry.getKey()).getNbPointsPonderes()
							.add(new BigDecimal(entry.getValue().getNbPoints()).multiply(new BigDecimal(Math.pow(10 ,POIDS_NB_POINTS_PARTICULIER))))
							.add(new BigDecimal(entry.getValue().getDifferenceButs()).multiply(new BigDecimal(Math.pow(10 ,POIDS_DIFF_BUTS_PARTICULIER))))
							.add(new BigDecimal(entry.getValue().getNbButsPour()).multiply(new BigDecimal(Math.pow(10 ,POIDS_BUTS_POUR_PARTICULIER))));
					mapClassementGroupe.get(entry.getKey()).setNbPointsPonderes(nbPointsPonderes);
				}
				
				// Pondération globale
				for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = mapClassementGroupe.entrySet().iterator(); it.hasNext();) {
					Map.Entry<Equipe, ClassementEquipe> entry = it.next();
					if(listeEquipesAEgalite.contains(entry.getKey())) {				
						BigDecimal nbPointsPonderes = entry.getValue().getNbPointsPonderes()
								.add(new BigDecimal((100 + entry.getValue().getDifferenceButs())).multiply(new BigDecimal(Math.pow(10 ,POIDS_DIFF_BUTS_GLOBAL))))
								.add(new BigDecimal(entry.getValue().getNbButsPour()).multiply(new BigDecimal(Math.pow(10 ,POIDS_BUTS_POUR_GLOBAL))))
								.add(new BigDecimal(entry.getKey().getCoeffUEFA()));
						mapClassementGroupe.get(entry.getKey()).setNbPointsPonderes(nbPointsPonderes);
					}
				}
			}
			
			// Classement des équipes selon les points pondérés
			List<Map.Entry<Equipe, ClassementEquipe>> listeClassementEquipe = new LinkedList<Map.Entry<Equipe, ClassementEquipe>>(mapClassementGroupe.entrySet());
			Collections.sort(listeClassementEquipe, comparatorPointsPonderes);
			
			// Détermination des rankings selon les points pondérés
			Map<Equipe, ClassementEquipe> mapClassementGroupe2 = new LinkedHashMap<Equipe, ClassementEquipe>();
			ClassementEquipe prevClassementEquipe = null;
		    int ranking = 1;
		    int cpt = 0;
			for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = listeClassementEquipe.iterator(); it.hasNext();) {
				Map.Entry<Equipe, ClassementEquipe> entry = it.next();
		        if (prevClassementEquipe != null) {	
			        if (prevClassementEquipe.getNbPointsPonderes().compareTo(entry.getValue().getNbPointsPonderes()) != 0) {
			        	ranking = cpt + 1;
			        }
		        }
		        prevClassementEquipe = entry.getValue();
		        entry.getValue().setRanking(ranking);
		        cpt++;
				
				mapClassementGroupe2.put(entry.getKey(), entry.getValue());
			}
			
			System.out.println("Classement Groupe");
			afficherClassementGroupe(mapClassementGroupe2.values());
			
			return new ArrayList<ClassementEquipe>(mapClassementGroupe2.values());
		}
	}

	private static Map<Equipe, ClassementEquipe> calculClassementGroupe(List<ResultatMatch> listeResultatsMatch) {
		// Construction du classement d'un groupe
		Map<Equipe, ClassementEquipe> classementGroupe = calculDetailsEquipeParMatch(listeResultatsMatch);
		
		// Pondération
		for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = classementGroupe.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Equipe, ClassementEquipe> entry = it.next();
			entry.getValue().setNbPointsPonderes(new BigDecimal(entry.getValue().getNbPoints()).multiply(new BigDecimal(Math.pow(10 ,POIDS_NB_POINTS_GLOBAL))));
		}
		
		// Classement des équipes selon les points pondérés
		List<Map.Entry<Equipe, ClassementEquipe>> listeClassementEquipe = new LinkedList<Map.Entry<Equipe, ClassementEquipe>>(classementGroupe.entrySet());
		Collections.sort(listeClassementEquipe, comparatorPointsPonderes);
		
		// Détermination des rankings selon les points pondérés
		Map<Equipe, ClassementEquipe> mapClassementGroupe = new LinkedHashMap<Equipe, ClassementEquipe>();
		ClassementEquipe prevClassementEquipe = null;
	    int ranking = 1;
	    int cpt = 0;
		for (Iterator<Map.Entry<Equipe, ClassementEquipe>> it = listeClassementEquipe.iterator(); it.hasNext();) {
			Map.Entry<Equipe, ClassementEquipe> entry = it.next();

	        if (prevClassementEquipe != null) {	        	
		        if (prevClassementEquipe.getNbPointsPonderes().compareTo(entry.getValue().getNbPointsPonderes()) != 0) {
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

	
	private static Map<Equipe, ClassementEquipe> calculDetailsEquipeParMatch(List<ResultatMatch> listeResultatsMatch) {
		Map<Equipe, ClassementEquipe> mapClassementPoule = new HashMap<Equipe, ClassementEquipe>();		
		
		for(ResultatMatch resultatMatch : listeResultatsMatch) {
			
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
			
			// TODO modifier par isJoue
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
	
	private static List<List<Equipe>> getListesEquipesAEgalite(Map<Equipe, ClassementEquipe> classementGroupe) {
		
		List<List<Equipe>> listesEquipesAEgalite = null;
		
		List<Set<Equipe>> listeHsetEquipesAEgalite = new ArrayList<>();
		Set<Equipe> hsetEquipesAEgalite = null;
		Entry<Equipe, ClassementEquipe> prevEntry = null;
		int prevRankingEgalite=-1;
		for (Entry<Equipe, ClassementEquipe> entry : classementGroupe.entrySet()) {
	        if (prevEntry != null) {	        	
		        if (prevEntry.getValue().getRanking() == entry.getValue().getRanking()) {
		        	if(entry.getValue().getRanking() != prevRankingEgalite) {
		        		if(hsetEquipesAEgalite!=null) {
		        			listeHsetEquipesAEgalite.add(hsetEquipesAEgalite);
		        		}
		        		hsetEquipesAEgalite = new HashSet<Equipe>();
		        		prevRankingEgalite = entry.getValue().getRanking();
		        	}
		        	hsetEquipesAEgalite.add(prevEntry.getKey());
		        	hsetEquipesAEgalite.add(entry.getKey());
		        }
	        }
	        prevEntry = entry;
		}
		
		if(hsetEquipesAEgalite!=null) {
			listeHsetEquipesAEgalite.add(hsetEquipesAEgalite);

			listesEquipesAEgalite = new ArrayList<List<Equipe>>();
			for(Set<Equipe> hsetEquipesAEgalite2 : listeHsetEquipesAEgalite) {
				List<Equipe> equipesAEgalite = new ArrayList<>();
				equipesAEgalite.addAll(hsetEquipesAEgalite2);
				listesEquipesAEgalite.add(equipesAEgalite);
			}
		}
		return listesEquipesAEgalite;
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
