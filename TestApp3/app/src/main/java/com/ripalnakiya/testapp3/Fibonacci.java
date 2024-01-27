package com.ripalnakiya.testapp3;

public class Fibonacci {
    static int[] fibonacci;

    static int fibRecursive(int num) {
        if (num <= 1)
            return num;
        return fibRecursive(num - 1) + fibRecursive(num - 2);
    }
    // O(2^n)

    static int fibDynamic(int num) {
        if (num <= 1) {
            fibonacci[num] = num;
            return num;
        }
        if (fibonacci[num - 1] == 0)
            fibonacci[num - 1] = fibDynamic(num - 1);
        if (fibonacci[num - 2] == 0)
            fibonacci[num - 2] = fibDynamic(num - 2);

        return fibonacci[num - 1] + fibonacci[num - 2];
    }
    // O(n)

    public static int findNext(int x) {
        fibDynamic(x);
        int i = 0;
        while (i < fibonacci.length) {
            if (x <= fibonacci[i])
                i++;
            else
                return fibonacci[i];
        }
        return 0;
    }
    // O(n)

    public static void main(String args[]) {
        fibonacci = new int[20];

        int num = 5;
        findNext(num);
    }
}
