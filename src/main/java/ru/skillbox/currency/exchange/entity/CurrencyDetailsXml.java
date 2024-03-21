package ru.skillbox.currency.exchange.entity;


import lombok.Getter;
import lombok.Setter;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Setter
@Getter
@XmlRootElement(name = "Valute")
public class CurrencyDetailsXml {

    private Long numCode;

    private String charCode;

    private Long nominal;

    private String name;

    private String values;


    @XmlElement(name = "NumCode")
    public Long getNumCode() {
        return numCode;
    }

    public void setNumCode(Long numCode) {
        this.numCode = numCode;
    }

    @XmlElement(name = "CharCode")
    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @XmlElement(name = "Nominal")
    public Long getNominal() {
        return nominal;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Value")
    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
