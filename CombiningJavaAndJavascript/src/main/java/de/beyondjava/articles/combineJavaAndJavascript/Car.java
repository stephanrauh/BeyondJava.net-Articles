package de.beyondjava.articles.combineJavaAndJavascript;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	private String hersteller;
	private String typ;
	private int baujahr;

	public Car(String hersteller, String typ, int baujahr) {
		this.hersteller = hersteller;
		this.typ = typ;
		this.baujahr = baujahr;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(int baujahr) {
		this.baujahr = baujahr;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

}
