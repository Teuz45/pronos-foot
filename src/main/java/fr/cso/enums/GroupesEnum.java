package fr.cso.enums;

public enum GroupesEnum {

	A("A"),
	B("B"),
	C("C"),
	D("D"),
	E("E"),
	F("F");
	
	private String cdGroupe;

	private GroupesEnum(String cdGroupe) {
		this.cdGroupe = cdGroupe;
	}

	public String getCdGroupe() {
		return cdGroupe;
	}

	public void setCdGroupe(String cdGroupe) {
		this.cdGroupe = cdGroupe;
	}
	
}
