package com.brndn.barweight.barweightcalculator;

import java.util.ArrayList;

/**
 * Created by Brendan on 10/6/2014.
 */
public class WeightCalculator {

    /**
     * Give back number of olympic plates needed
     * @param weight
     * @param has45
     * @param has35
     * @param has25
     * @param has10
     * @param has5
     * @param has2_5
     * @return
     */
    public static int[] calculate(int weight, boolean has45, boolean has35, boolean has25, boolean has10, boolean has5, boolean has2_5) {



        //Check if divisible by 5
        int barweight = 45;
        if (weight < 0) return null;
        if (weight < barweight) return null;
        if (weight % 5  != 0) return null;
        weight -= barweight;

        int[] weightValsDbl = {90, 70, 50, 20, 10, 5};
        int[] weights = new int[weightValsDbl.length];

        for (int i = 0; i < weightValsDbl.length; i++) {
            while (!(weight < weightValsDbl[i])) {
                weight -= weightValsDbl[i];
                weights[i]++;
            }
        }

        return weights;
    }


}
