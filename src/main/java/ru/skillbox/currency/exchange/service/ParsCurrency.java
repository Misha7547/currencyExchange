package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.entity.CurrencyDetailsXml;
import ru.skillbox.currency.exchange.entity.CurrencyXml;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
@Async
@EnableScheduling
public class ParsCurrency {

    @Value("${cbr.api.getXml}")
    private String url;

    private final CurrencyRepository repository;

    @Scheduled(fixedDelay = 1000000)
    public void parseXml() throws IOException, InterruptedException, JAXBException, ParseException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyXml.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        CurrencyXml currencyXml;
        currencyXml = (CurrencyXml) jaxbUnmarshaller.unmarshal(new StringReader(response.body()));
        List<CurrencyDetailsXml> list = currencyXml.getValutes();
        for (CurrencyDetailsXml currencyDetailsXml : list) {
            examination(currencyDetailsXml);
        }
    }

    private void examination(CurrencyDetailsXml xml) throws ParseException {
        List<Currency> list = repository.findAll();
        for (Currency currency : list) {
            if (currency.getName().equals(xml.getName())) {
                databaseUpdate(currency, xml);
            } else {
                addingBase(xml);
                break;
            }
        }
    }

    private void databaseUpdate(Currency currency, CurrencyDetailsXml xml) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(xml.getValues());
        Double d = number.doubleValue();
        currency.setValue(d);
        currency.setIsoLetterCode(xml.getCharCode());
        repository.save(currency);
    }

    private void addingBase(CurrencyDetailsXml xml) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(xml.getValues());
        Double d = number.doubleValue();
        Currency currency = new Currency();
        currency.setName(xml.getName());
        currency.setNominal(xml.getNominal());
        currency.setValue(d);
        currency.setIsoNumCode(xml.getNumCode());
        currency.setIsoLetterCode(xml.getCharCode());
        repository.save(currency);
    }
}
