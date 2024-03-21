package ru.skillbox.currency.exchange.entity;

import lombok.Getter;
import lombok.Setter;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@Getter
@XmlRootElement(name = "ValCurs")
public class CurrencyXml {

    private List<CurrencyDetailsXml> valutes;

    @XmlElement(name = "Valute")
    public List<CurrencyDetailsXml> getValutes() {
        return valutes;
    }

    public void setValutes(List<CurrencyDetailsXml> valutes) {
        this.valutes = valutes;
    }
}
