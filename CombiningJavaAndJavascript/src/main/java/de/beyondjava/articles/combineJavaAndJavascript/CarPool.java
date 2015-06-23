package de.beyondjava.articles.combineJavaAndJavascript;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CarPool implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Car> cars = new ArrayList<>();
	
	private String hersteller;
	private String typ;
	private int baujahr;

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

	public CarPool() {
		cars.add(new Car("VW", "Golf", 2008));
		cars.add(new Car("Honda", "Civic", 2012));
		cars.add(new Car("BMW", "320i", 2014));
	}

	public List<Car> getCars() {
		return cars;
	}
}
