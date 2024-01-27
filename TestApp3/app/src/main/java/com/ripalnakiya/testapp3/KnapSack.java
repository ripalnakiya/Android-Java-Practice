package com.ripalnakiya.testapp3;

public class KnapSack {

//    profit = 10, 12, 20
//    weight = 2 , 5, 1
//    capacity = 3

    static int myCapacity;
    int[] itemsAdded;

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        int high = 0, totalProfit = 0;

        itemsAdded = new int[profits.length];

        for (int j = 0; j < profits.length; j++) {

            for (int i = 0; i < profits.length; i++) {

                if (profits[high] < profits[i]) {
                    if (isFeasible(weights[i]) && isPartOf(high)) {
                        high = i;
                    }
                }

            }

            totalProfit += profits[high];
            itemsAdded[j] = high;
        }
        return totalProfit;
    }
    // O (n^3)

    public boolean isPartOf(int high) {
        for (int i = 0; i < itemsAdded.length; i++) {
            if (high == itemsAdded[i])
                return true;
        }
        return false;
    }

    public boolean isFeasible(int weight) {
        if (weight <= myCapacity) {
            return true;
        }
        return false;
    }
}