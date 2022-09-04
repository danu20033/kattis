package org.example;



import java.util.Scanner;

public class Babylonian {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int noOfTestcases = Integer.parseInt(sc.next());


        while (sc.hasNext()) {
            String text = sc.next();

            String[] textArray = text.split(",",-1);

            int pow = textArray.length-1;
            int value=0;
            for(int i=pow ; i>=0 ; i--){

                if(!textArray[i].isEmpty()){
                 value = value+ Integer.parseInt(textArray[i]) * (int) Math.pow(60,pow-i);
                }
            }

            System.out.println(value);
        }


    }
}
