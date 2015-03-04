package com.contis;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Adeyemi Olaoye <yemexx1@gmail.com>
 */
public class Contis {

    public static void main(String[] args) {
        showMenu();
        int input = readMenuInput();
        routeMenu(input);
    }

    public static void showMenu() {

        String[] menuItems = {"Exercise One", "Exercise Two", "Exercise Three", "Exercise Four"};
        int menuIndex = 0;
        for (String menuItem : menuItems) {
            System.out.println(++menuIndex + ". " + menuItem);
        }
    }

    public static void routeMenu(int menuIndex) {
        if (menuIndex == 1) {
            System.out.println("\n**Get number of permutations**\n");
            ArrayList permutations = getPermutations();
            System.out.println("Number of permutations = " + permutations.size());
        } else if (menuIndex == 2) {
            System.out.println("\n**Show each permuation arrangements**\n");
            ArrayList permutations = getPermutations();
            for (Object permuation : permutations) {
                System.out.println(permuation.toString());
            }
        } else if (menuIndex < 1 || menuIndex > 4) {
            System.out.println("Please enter number between 1 and 4");
            readMenuInput();
        }
    }

    public static int readMenuInput() {
        System.out.println("Enter the number corresponding to the excersise you want to run and press enter:");
        try {
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            return input;
        } catch (InputMismatchException in) {
            return -1;
        }
    }

    public static ArrayList getPermutations() {
        String input = "abcde";
        // get length of string to permutate
        int inputLength = input.length();
        boolean[] used = new boolean[inputLength];
        StringBuffer outputString = new StringBuffer();
        char[] in = input.toCharArray();
        ArrayList<String> arrayList = new ArrayList();
        // recursively permutate characters
        doPermute(in, outputString, used, inputLength, 0, arrayList);
        return arrayList;
    }

    public static void doPermute(char[] in, StringBuffer outputString,
            boolean[] used, int inputLength, int level, ArrayList arrayList) {
        // check if length of string equals current level
        if (level == inputLength) {
            return;
        }

        for (int i = 0; i < inputLength; ++i) {
            outputString.append(in[i]);
            used[i] = true;
            //add the current permutation to the List
            if (outputString.toString().length() == 5) {
                arrayList.add(outputString.toString());
            }
            doPermute(in, outputString, used, inputLength, level + 1, arrayList);
            used[i] = false;
            outputString.setLength(outputString.length() - 1);
        }

    }

}
