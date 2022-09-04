package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //String input = "10 20 40 4 2 2 10";
        //String input = "144 55 8 0 1 9 4";
        String input = "10 10 10 2 3 2 6";
        //String input = "10 20 40 86 9 9 999";


        String inputArray[] = input.split(" ");

        System.out.println(inputArray);

        int M = Integer.parseInt(inputArray[0]);
        int P = Integer.parseInt(inputArray[1]);
        int L = Integer.parseInt(inputArray[2]);

        // 0 <= E <= 100
        int E = Integer.parseInt(inputArray[3]); //Eggs

        //1<=R,S<=10
        int R = Integer.parseInt(inputArray[4]);
        int S = Integer.parseInt(inputArray[5]);

        //1<=N<=1000
        int N = Integer.parseInt(inputArray[6]);

        int i = 0;
        while (i < N) {
            //end of first week


            int l = P / R;
            int m = L / S;
            int p = M * E;



            i++;


        }

        System.out.println("Mosquitoes  =" + M);


        System.out.println("Hello World!");
    }
}
