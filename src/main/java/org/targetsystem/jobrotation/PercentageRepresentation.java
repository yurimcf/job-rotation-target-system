package org.targetsystem.jobrotation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class PercentageRepresentation {
//    SP – R$67.836,43
//    RJ – R$36.678,66
//    MG – R$29.229,88
//    ES – R$27.165,48
//    Outros – R$19.849,53
    public static void main(String[] args) {
        Map<String, BigDecimal> InvoicingByState = new HashMap<>();
        InvoicingByState.put("SP", new BigDecimal("67836.43"));
        InvoicingByState.put("RJ", new BigDecimal("36678.66"));
        InvoicingByState.put("MG", new BigDecimal("29229.88"));
        InvoicingByState.put("ES", new BigDecimal("27165.48"));
        InvoicingByState.put("Outros", new BigDecimal("19849.53"));

        BigDecimal totalMonthly = BigDecimal.ZERO;
        for (BigDecimal faturamento : InvoicingByState.values()) {
            totalMonthly = totalMonthly.add(faturamento);
        }

        for (Map.Entry<String, BigDecimal> entry : InvoicingByState.entrySet()) {
            String state = entry.getKey();
            BigDecimal invoicing = entry.getValue();
            BigDecimal percentage = invoicing.divide(totalMonthly, 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
            System.out.printf("%s - R$%.2f (%.2f%%)\n", state, invoicing, percentage);
        }
    }
}
