package fr.cso.models.beans;

public class Resultat {
	private Equipe equipeDom;
	
	private Equipe equipeExt;
	
	private Integer scoreDom;
	
	private Integer scoreExt;
	
	private Integer scorePenDom;
	
	private Integer scorePenExt;
	
	public Resultat(Equipe equipeDom, Equipe equipeExt, Integer scoreDom, Integer scoreExt, Integer scorePenDom,
			Integer scorePenExt) {
		super();
		this.equipeDom = equipeDom;
		this.equipeExt = equipeExt;
		this.scoreDom = scoreDom;
		this.scoreExt = scoreExt;
		this.scorePenDom = scorePenDom;
		this.scorePenExt = scorePenExt;
	}

	public Equipe getEquipeDom() {
		return equipeDom;
	}

	public void setEquipeDom(Equipe equipeDom) {
		this.equipeDom = equipeDom;
	}

	public Equipe getEquipeExt() {
		return equipeExt;
	}

	public void setEquipeExt(Equipe equipeExt) {
		this.equipeExt = equipeExt;
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
	
	
}
