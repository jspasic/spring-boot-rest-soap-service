package rs.jspasic.webservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "country", propOrder = {
    "name",
    "population",
    "capital",
    "currency"
})
public class Country {

    @XmlElement(required = true)
    protected String name;
    protected int population;
    @XmlElement(required = true)
    protected String capital;
    @XmlElement(required = true)
    protected Currency currency;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int value) {
        this.population = value;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String value) {
        this.capital = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency value) {
        this.currency = value;
    }

}
