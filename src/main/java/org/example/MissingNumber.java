package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MissingNumber {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.next());
        boolean fail = false;

        int a = 0;
        int i = 1;
        while (a < count) {
            int recitedNumber = Integer.parseInt(sc.next());
            if (i != recitedNumber) {
                fail = true;
                for (int j = i; j < recitedNumber; j++) {
                    System.out.println(j);
                }

            }
            i = recitedNumber + 1;
            a++;


        }
        if (!fail) {
            System.out.println("good job");
        }
    }
}
