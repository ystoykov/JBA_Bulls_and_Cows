package bullscows;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static boolean isCorrectValue = true;
    static String hideString = "";
    static long hideNum = 1;
    static int bulls = 0;
    static int cows = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int length = testedLengthOfCode();
        int posSymb = -1;
        if (isCorrectValue) {
            posSymb = testedCountOfSymbols(length);
        }
        if (isCorrectValue) {
            createNumber(length, posSymb);
            printSecret(posSymb);

            System.out.println("Okay, let's start a game!");
            int count = 1;

            while (bulls < length) {
                System.out.printf("Turn %d:\n", count);
                count++;
                String example = scanner.nextLine();
                grader(example);
                printResult();
                if (bulls == example.length()) {
                    System.out.println("Congratulations! You guessed the secret code.");
                }

            }
        }
    }

        // grader check "bulls" and "cows" in secret code
        static void grader (String compre){
            //String hiddenNumber = String.valueOf(hideNum);
            bulls = 0;
            cows = 0;
            char[] compareArray = compre.toCharArray();
            for (int i = 0; i < compre.length(); i++) {
                if (hideString.indexOf(compareArray[i]) == i) {
                    bulls++;
                } else if (hideString.indexOf(compareArray[i]) >= 0) {
                    cows++;
                }
            }
        }

        //print result after grader checks the secret code: count of bulls and cows or congratulations.
        static void printResult() {
            String res;
            if (bulls == 0 && cows == 0) {
                res = "None";
            } else if (bulls == String.valueOf(hideNum).length()) {
                res = String.format("%d bull(s)", String.valueOf(hideNum).length());
            } else if (bulls == 0) {
                res = String.format("%d cow(s)", cows);
            } else {
                res = String.format("%d bull(s) and %d cow(s)", bulls, cows);
            }
            System.out.printf("Grade: %s.\n", res);
        }

        //creates a code of a given length and range of characters
        static void createNumber (int lengthOfCode, int posSymbols){
        StringBuilder symb = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");
        symb.setLength(posSymbols);
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
            while (true) {
                for (int i = 0; i < lengthOfCode; i++) {
                    sb.append(symb.charAt(random.nextInt(posSymbols)));
                }
                HashSet<Character> hashSet = new HashSet<>();
                for (char ch : sb.toString().toCharArray()) {
                    hashSet.add(ch);
                }
                if (lengthOfCode == hashSet.size()) {
                    hideString = sb.toString();
                    break;
                } else {
                    sb.delete(0, lengthOfCode);
                }
            }
        }

        //prints a formatted secret code
        static void printSecret(int posSymbols) {
            StringBuilder symbols =new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");
            StringBuilder secret = new StringBuilder("The secret is prepared: ");
            for (int i = 0; i < hideString.length(); i++) {
                secret.append('*');
            }
            if (posSymbols < 10) {
                secret.append(" (0-")
                        .append(symbols.charAt(posSymbols - 1))
                        .append(").");
            }
            if (posSymbols > 9) {
                secret.append(" (0-9, a-")
                        .append(symbols.charAt(posSymbols - 1))
                        .append(").");
            }
            System.out.println(secret);
        }

        //validates user-entered code length values
        static int testedLengthOfCode() {
            int code = -1;
            String tempString = null;
            Scanner scanner = new Scanner(System.in);
            try {
                    System.out.println("Input the length of the secret code:");
                    tempString = scanner.nextLine();
                    code = Integer.parseInt(tempString);
                } catch (Exception e) {
                    System.out.printf("Error: \"%s\" isn't a valid number.\n", tempString);
                    isCorrectValue = false;
                }
            if (code > 36 || code < 1) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).\n");
                isCorrectValue = false;
            }
            return code;
        }

        //validates user-entered character range value to generate code
        static int testedCountOfSymbols(int length) {
        int count = -1;
        String tempString = null;
        Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Input the number of possible symbols in the code:");
                tempString = scanner.nextLine();
                count = Integer.parseInt(tempString);
            } catch (Exception e) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", tempString);
                isCorrectValue = false;
            }
            if (count > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).\n");
                isCorrectValue = false;
            } else if (count < length) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d " +
                                "with %d unique symbols.\n", length, count);
                isCorrectValue = false;
            }

        return count;
    }
    }

