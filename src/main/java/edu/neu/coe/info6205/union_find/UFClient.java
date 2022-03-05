package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;
public class UFClient {

    public static int cnt(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);
        Random random = new Random();
        int ct = 0;
        while (uf.components() > 1) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            uf.connect(x, y);
            ct++;
        }
        return ct;
    }

    public static void main(String[] args) {

        System.out.println("Enter a number");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("total objects " + n + " connections: " + cnt(n));

        System.out.println("relationship between m and n");

        for (int i = 1000; i < 160000; i *= 2) {
            int sum = 0;

            for (int j = 0; j < 10; j++) {
                sum += cnt(i);
            }
            int mean = sum / 10;
            System.out.println("total objects " + i + ", total pairs " + mean);
        }
    }

}
