package org.targetsystem.jobrotation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Arrays;

public class MainInvoicing {
    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("dados.json"));
        Invoicing[] invoicings = invoicings(jsonArray);


        System.out.println(Arrays.toString(invoicings));
    }


    public static Invoicing[] invoicings (JSONArray jsonArray) {
        Invoicing[] invoicings = new Invoicing[jsonArray.size()];
        for (int i = 0; i < invoicings.length; i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Invoicing invoicing = new Invoicing();
            invoicing.setDay((Long) jsonObject.get("dia"));
            invoicing.setValue(BigDecimal.valueOf((Double) jsonObject.get("valor")));
            if (invoicing.getValue().compareTo(BigDecimal.ZERO) != 0){
                invoicings[i] = invoicing;
            }
        }
        return invoicings;
    }

    public static Invoicing lowerBilling(Invoicing[] invoicings) {
    return null;
    }










}



class Invoicing {
    private Long day;
    private BigDecimal value;

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Invoicing{" +
                "day=" + day +
                ", value=" + value +
                '}' +"\n";
    }
}
