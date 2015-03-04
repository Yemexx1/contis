package com.contis;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Adeyemi Olaoye <yemexx1@gmail.com>
 */
public class Contis {

    public static void main(String[] args) {
        showMenu();
        int input = readMenuInput();
        routeMenu(input);
        showMenu();
    }

    public static void showMenu() {

        String[] menuItems = {"Exercise One", "Exercise Two", "Exercise Three", "Exercise Four", "Exit"};
        int menuIndex = 0;
        System.out.println("\nMenu\n=====================================================");
        for (String menuItem : menuItems) {
            System.out.println(++menuIndex + ". " + menuItem);
        }
        System.out.println("=====================================================");
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
        } else if (menuIndex == 3) {
            System.out.println("\n**Search Permutations**\n");
            System.out.println("Enter key to search:");
            Scanner sc = new Scanner(System.in);
            String key = sc.nextLine();
            System.out.println("Searching for key " + key);
            ArrayList permutations = getPermutations();
            int found = search(permutations, key);
            if (found == -1) {
                System.out.println("Key not found");
            } else {
                System.out.println("Key found at " + found);
            }
        } else if (menuIndex == 4) {
            System.out.println("\n**Search Permutations Recursively**\n");
            System.out.println("Enter key to search:");
            Scanner sc = new Scanner(System.in);
            String key = sc.nextLine();
            System.out.println("Searching for key " + key);
            ArrayList permutations = getPermutations();
            int found = searchRecursive(permutations, key, 0, permutations.size());
            if (found == -1) {
                System.out.println("Key not found");
            } else {
                System.out.println("Key found at " + found);
            }
        } else if (menuIndex == 5) {
            System.exit(0);
        } else if (menuIndex < 1 || menuIndex > 4) {
            System.out.println("Please enter number between 1 and 4");
            int input = readMenuInput();
            routeMenu(input);
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

    public static int search(ArrayList permutations, String key) {
        int start = 0;
        int end = permutations.size() - 1;

        while (start <= end) {
            int middle = (start + end) / 2;
            String middleStr = permutations.get(middle).toString();

            if (key.compareTo(middleStr) > 0) {
                start = middle + 1;
            } else if (key.compareTo(middleStr) < 0) {
                end = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static int searchRecursive(ArrayList permutations, String key, int start, int end) {

        int middle = (start + end) / 2;
        if (middle >= permutations.size()) {
            return -1;
        }
        String middleStr = permutations.get(middle).toString();

        if (key.compareTo(middleStr) > 0) {
            start = middle + 1;
            return searchRecursive(permutations, key, start, end);
        } else if (key.compareTo(middleStr) < 0) {
            end = middle - 1;
            return searchRecursive(permutations, key, start, end);
        } else {
            return middle;
        }

    }

}
