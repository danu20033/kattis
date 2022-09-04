package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemA {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        //a,a+1,....,b,    a and b are lowest and highest
        // die has (b-a)+1

        //conditions
        // 1<=a <=b <=100
        // each die has at least four sides ai + 3 <= bi




        int gunnarSum = new DieRoll(scanner.nextLine()).getSum();
        int emmaSum= new DieRoll(scanner.nextLine()).getSum();


        if(gunnarSum == emmaSum){
            System.out.println("Tie");
        }else if(gunnarSum>emmaSum){
            System.out.println("Gunnar");
        }else{
            System.out.println("Emma");
        }


    }

   static  class DieRoll {

        int[] values;

        public DieRoll(String input) {

            if (input != null) {
                String rollValues[] = input.split(" ");
                values = new int[rollValues.length];

                for (int i = 0; i < rollValues.length; i++) {
                    values[i] = Integer.parseInt(rollValues[i]);
                }

            }


        }

        public int getSum() {
            return Arrays.stream(values).sum();
        }


    }


}
