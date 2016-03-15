package fr.cso.model.bean;

import fr.cso.model.Equipe;

public class ResultatMatch {

	private boolean matchJoue;
	
	private Integer scoreDom;

	private Integer scoreExt;

	private Integer scorePenDom;

	private Integer scorePenExt;
	
	private Equipe equipeExt;

	private Equipe equipeDom;

	
	
	public boolean isMatchJoue() {
		return matchJoue;
	}

	public void setMatchJoue(boolean matchJoue) {
		this.matchJoue = matchJoue;
	}

	public Integer getScoreDom() {
		return scoreDom;
	}

	public void setScoreDom(Integer scoreDom) {
		this.scoreDom = scoreDom;
	}

	public Integer getScoreExt() {
		return scoreExt;
	}

	public void setScoreExt(Integer scoreExt) {
		this.scoreExt = scoreExt;
	}

	public Integer getScorePenDom() {
		return scorePenDom;
	}

	public void setScorePenDom(Integer scorePenDom) {
		this.scorePenDom = scorePenDom;
	}

	public Integer getScorePenExt() {
		return scorePenExt;
	}

	public void setScorePenExt(Integer scorePenExt) {
		this.scorePenExt = scorePenExt;
	}

	public Equipe getEquipeExt() {
		return equipeExt;
	}

	public void setEquipeExt(Equipe equipeExt) {
		this.equipeExt = equipeExt;
	}

	public Equipe getEquipeDom() {
		return equipeDom;
	}

	public void setEquipeDom(Equipe equipeDom) {
		this.equipeDom = equipeDom;
	}
	
	
}
