import java.util.Scanner;
public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String response = "";
        boolean gotNonZeroLenString = false;
        do {
            System.out.println(prompt + ": ");
            response = pipe.nextLine();
            pipe.nextLine();
            if (response.length() == 0) {
                gotNonZeroLenString = false;
                System.out.println("Invalid input: " + response);
            } else {
                gotNonZeroLenString = true;
            }
        } while (!gotNonZeroLenString);
        return response;
    }
    public static int getInt(Scanner pipe, String prompt) {
        int result = 0;
        String trash = "";
        boolean gotInt;
        do {
            System.out.println(prompt + ": ");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine();
                gotInt = true;
            } else {
                trash = pipe.nextLine();
                gotInt = false;
                System.out.println("Enter a valid integer: " + trash);
            }
        } while (!gotInt);
        return result;
    }
    public static int getDouble(Scanner pipe, String prompt) {
        double result = 0;
        String trash = "";
        boolean gotDouble;
        do {
            System.out.println(prompt + ": ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine();
                gotDouble = true;
            } else {
                trash = pipe.nextLine();
                gotDouble = false;
                System.out.println("Enter a valid decimal: " + trash);
            }
        } while (!gotDouble);
        return (int) result;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine();
                if (result >= low && result <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + result);
                }
            } else {
                done = false;
                trash = pipe.nextLine();
                System.out.println("You must enter a valid int [" + low + " - " + high + "]: " + trash);
            }
        } while (!done);
        return result;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine();
                if (result >= low && result <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + result);
                }
            } else {
                done = false;
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number [" + low + " - " + high + "]: " + trash);
            }
        } while (!done);
        return result;
    }
    public static String getYNConfirm(Scanner pipe, String prompt) {

        String response;
        boolean invalid;
        String trash;
        do {
            System.out.println(prompt + " :");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                invalid = false;
            } else if (response.equalsIgnoreCase("N")) {
                invalid = false;
            } else {
                invalid = true;
            }
        } while (invalid);
        return response;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern) {
        String value = "";
        boolean gotAValue = false;
        do {
            System.out.println(prompt + ": ");
            value = pipe.nextLine();
            if (value.matches(regExPattern)) {
                gotAValue = true;
            } else {
                System.out.println("Invalid input: " + value);
            }

        } while (!gotAValue);

        return value;
    }
}