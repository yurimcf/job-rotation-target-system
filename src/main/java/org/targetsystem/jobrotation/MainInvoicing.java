package org.targetsystem.jobrotation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class MainInvoicing {
    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("dados.json"));

        Invoicing[] invoicings = invoicings(jsonArray);
        Invoicing higherRevenue = higherRevenue(invoicings);
        System.out.println("Dia com com a Maior receita foi " + higherRevenue.getDay()
                + " com Faturamento de " + higherRevenue.getValue());
        Invoicing lowerRevenue = lowerRevenue(invoicings);
        System.out.println("Dia com com a memor receita foi " + lowerRevenue.getDay()
                + " com Faturamento de " + lowerRevenue.getValue());

        Object[] daysAndAvgAboveMonthly = numberDaysBillingAboveAverage(invoicings);
        System.out.printf("Dia em que o fatuamento foi acime de %.2f foram %d dias",
                daysAndAvgAboveMonthly[0], daysAndAvgAboveMonthly[1]);
    }


    public static Invoicing[] invoicings(JSONArray jsonArray) {
        Invoicing[] invoicings = new Invoicing[jsonArray.size()];
        for (int i = 0; i < invoicings.length; i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Invoicing invoicing = new Invoicing();
            invoicing.setDay((Long) jsonObject.get("dia"));
            invoicing.setValue((Double) jsonObject.get("valor"));
            if (invoicing.getValue() != 0) {
                invoicings[i] = invoicing;
            }
        }
        return invoicings;
    }

    public static Invoicing higherRevenue(Invoicing[] invoicings) {
        Invoicing max = invoicings[0];
        for (int i = 0; i < invoicings.length; i++) {
            if (invoicings[i] != null && invoicings[i].getValue().compareTo(max.getValue()) > 0) {
                max = invoicings[i];
            }
        }
        return max;
    }

    public static Invoicing lowerRevenue(Invoicing[] invoicings) {
        Invoicing min = invoicings[0];
        for (int i = 0; i < invoicings.length; i++) {
            if (invoicings[i] != null && invoicings[i].getValue().compareTo(min.getValue()) < 0) {
                min = invoicings[i];
            }
        }
        return min;
    }

    public static Object[] numberDaysBillingAboveAverage(Invoicing[] invoicings) {
        Object[] objects = new Object[2];
        Double avgMonthly = avgMonthly(invoicings);
        Integer dayAbove = numberDaysAboveAverage(invoicings, avgMonthly);

        objects[0] = avgMonthly;
        objects[1] = dayAbove;
        return objects;
    }

    private static Double avgMonthly(Invoicing[] invoicings) {
        int cont = 0;
        Double sum = 0.0;

        for (Invoicing invoicing : invoicings) {
            if (invoicing != null) {
                cont++;
                Double value = invoicing.getValue();
                sum = sum + value.doubleValue();
            }
        }
        Double avg = sum / cont;
        return avg;
    }

    private static Integer numberDaysAboveAverage(Invoicing[] invoicings, Double avgMonthly) {
        int cont = 0;
        for (Invoicing invoicing :
                invoicings) {
            if (invoicing != null && invoicing.getValue() > avgMonthly) {
                cont++;
            }
        }
        return cont;
    }
}


class Invoicing {
    private Long day;
    private Double value;

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Invoicing{" +
                "day=" + day +
                ", value=" + value +
                '}' + "\n";
    }
}
