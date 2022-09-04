package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProblemB {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int noOfStack = Integer.parseInt(scanner.nextLine());
        String stackValues[] = scanner.nextLine().split(" ");

        List<CoinStack> coinStackList = new ArrayList<>();
        int totalCoins = 0;
        for (int i = 0; i < stackValues.length; i++) {
            int currentValue = Integer.parseInt(stackValues[i]);
            totalCoins = totalCoins + currentValue;
            CoinStack coinStack = new CoinStack(i + 1, currentValue);
            coinStackList.add(coinStack);
        }

        play(totalCoins, coinStackList);


    }

    /**
     * execute the rules on stack.
     * eg: conditions
     * Remove all , if total no of coins is even count further any stack should not be over valued.
     *
     * @param totalCoins    total no of coins in the all stack
     * @param coinStackList coin stack list.
     */
    static void play(int totalCoins, List<CoinStack> coinStackList) {
        //check the total coin is even and largest stack should not be greater than all other total coin count.
        if (totalCoins % 2 == 0 && !hasOvervaluedStack(coinStackList)) {
            List<String> output = new ArrayList<>();
            List<CoinStack> sortedAndFilteredList = coinStackList;
            //this will run until item count reach 2.
            while (sortedAndFilteredList.size() > 1) {
                //sort coinStack in reverse order
                sortedAndFilteredList = coinStackList.stream().filter(stack -> stack.getNoOfCoins() > 0).sorted(Comparator.comparing(CoinStack::getNoOfCoins).reversed()).collect(Collectors.toList());

                //get first 2 items
                if(sortedAndFilteredList.size() >1) {
                    CoinStack playerA = sortedAndFilteredList.get(0);
                    CoinStack playerB = sortedAndFilteredList.get(1);
                    playerA.pop();
                    playerB.pop();
                    output.add(playerA.getIndex() + " " + playerB.getIndex());
                }
               // sortedAndFilteredList = sortedAndFilteredList.stream().filter(stack -> stack.getNoOfCoins() > 0).collect(Collectors.toList());

            }
            System.out.println("yes");
            output.forEach(System.out::println);

        } else {
            System.out.println("no");
        }

    }

    /**
     * Check any of the stack has more coins than all other stacks
     *
     * @param coinStackList stack list
     * @return true if any of the stack is over valued.
     */
    static boolean hasOvervaluedStack(List<CoinStack> coinStackList) {
        coinStackList.sort(Comparator.comparing(CoinStack::getNoOfCoins).reversed());
        CoinStack largest = coinStackList.get(0);
        int total = coinStackList.stream().collect(Collectors.summingInt(CoinStack::getNoOfCoins));

        return largest.getNoOfCoins() > total - largest.getNoOfCoins();
    }

    /**
     * Data structure to for Coin stack
     */
    static class CoinStack {
        int index;


        int noOfCoins;

        public CoinStack(int index, int value) {
            this.index = index;
            this.noOfCoins = value;
        }

        public void pop() {
            this.noOfCoins = this.noOfCoins - 1;
        }

        public int getIndex() {
            return index;
        }


        public int getNoOfCoins() {
            return noOfCoins;
        }


    }
}
