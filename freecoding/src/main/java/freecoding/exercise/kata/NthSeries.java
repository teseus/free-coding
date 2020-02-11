package freecoding.exercise.kata;

import java.text.DecimalFormat;

public class NthSeries {
    public static String seriesSum(int n) {
        if (n == 1) return "1";
        double sum = 0;
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < n; i++) {
            if(i==0){
                sum = 1;
            } else {
                sum += 1 / (1f + i * 3);
                sb.append(" + 1/");
                sb.append(1f + i * 3);
            }
        }

        System.out.println(sb.toString());
        DecimalFormat decimalFormat = new DecimalFormat("##.00");

        String format = decimalFormat.format(sum);
        return String.valueOf(format);
    }
}
