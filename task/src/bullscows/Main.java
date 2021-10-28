package bullscows;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import  java.util.Random;


public class Main {
    static StringBuilder secretCode = new StringBuilder("");
    static int totalBulls = 0;
    static int turns = 1;
    static Scanner input = new Scanner(System.in);


    public static void gradeGuess (){
        System.out.println("Turn " + turns + ":");

        String number = input.nextLine();

        int bulls = 0;
        int cows = 0;

            for (int i = 0; i < number.length() ; i++){
                if( number.charAt(i) == secretCode.charAt(i))  bulls++;
                else if(secretCode.indexOf(String.valueOf(number.charAt(i)))!= -1)   cows++;
            }

        String bullString = bulls == 1 ? "bull" : "bulls";
        String cowString = cows == 1 ? "cow" : "cows";
        totalBulls = bulls;

            if (bulls > 0 && cows> 0){
                System.out.println("Grade: " + bulls +  " " + bullString + " and " + cows + " " + cowString);
                turns++;
            }
            else if( bulls > 0){
                System.out.println("Grade: " + bulls +  " " + bullString);
                turns++;

            }
            else if( cows > 0 ){
                System.out.println("Grade: " + cows + " " + cowString);
                turns++;

            }
            else if( cows == 0 && bulls == 0 ){
                System.out.println("Grade: none");
                turns++;
            }
            else if (bulls ==number.length()){
                System.out.println("Grade: " + bulls +  " " + bullString + "\n" +
                        "Congratulations! You guessed the secret code.");
            }


    }

    public static void playGame(){
        while (totalBulls < secretCode.toString().length()){
          gradeGuess();
        }
        totalBulls = 0;
        turns =  1;
        secretCode = new StringBuilder("");
    }
    //first-generation code generator
    public static void generateCode(){
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        if(length  > 10) System.out.println("Error: can't generate a secret number with a length of " + length +  " because there aren't enough unique digits.");
        else {
            long pseudoRandomNumber = System.nanoTime();
            String stringRandomNumber = pseudoRandomNumber + "";
            StringBuilder numberSequence = new StringBuilder(stringRandomNumber);
            int startingIndex = 0;
            while (numberSequence.charAt(startingIndex) == 0) {
                startingIndex++;
            }

            List<Character> digits = new ArrayList<>();
            while (digits.size() < length) {
                if (!digits.contains(numberSequence.charAt(startingIndex))) {
                    digits.add(numberSequence.charAt(startingIndex));
                }
                startingIndex++;
            }

            StringBuilder finalNumber = new StringBuilder();

            for (int i = 0; i < digits.size(); i++) {
                finalNumber.append(digits.get(i));
            }

            StringBuilder reversedCode = finalNumber.reverse();
            secretCode = reversedCode;

            System.out.println("Okay, let's start a game!");


        }

    }
    //second-generation code generator
    public static void generateRandomCode(){
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        if(length  > 10) System.out.println("Error: can't generate a secret number with a length of " + length +  " because there aren't enough unique digits.");
        else {
            Random random = new Random();
            int randomDigit = random.nextInt(10);

            List<Integer> digits = new ArrayList<>();
            while (digits.size() == 0){
                if (randomDigit!= 0 ) digits.add(randomDigit);
                else randomDigit = random.nextInt(10);
            }
            while (digits.size() < length) {
                if (!digits.contains(randomDigit)) digits.add(randomDigit);
                randomDigit = random.nextInt(10);
            }
            StringBuilder finalNumber = new StringBuilder();

            for (int i = 0; i < digits.size(); i++) {
                finalNumber.append(digits.get(i));
            }

            StringBuilder reversedCode = finalNumber.reverse();
            secretCode = reversedCode;

            System.out.println("Okay, let's start a game!");
        }

    }
    //third-generation code generator
    public static void generateUniqueCode(){
        System.out.println("Input the length of the secret code:");
        int length = 0;
        int specialCharactersLength = 0;
        try{
            length = input.nextInt();
        }catch(Exception e){
            System.out.println("Error.");
        }
        String lengthString = Integer.toString(length);
        System.out.println("Input the number of possible symbols in the code:");




        try{
            specialCharactersLength = input.nextInt();
        }catch(Exception e){
            System.out.println("Error.");
        }
        String specialCharactersLengthString = Integer.toString(specialCharactersLength);

        char[] characters = new char [] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        if (lengthString.length() > 3 || specialCharactersLengthString.length() > 3) {
            System.out.println("Error: This isn't a valid number.");
        }
        else if (specialCharactersLength < length){
            System.out.println("Error: it's not possible to generate a code with a length of " + length+ " with "+  specialCharactersLength+ " unique symbols.");
        }
        else if (specialCharactersLength > 36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        }
        else if (length < 1 || specialCharactersLength < 1){
            System.out.println("Error: provided value must be a positive number");
        }
        else if  ( length <= 36 && specialCharactersLength <= 36) {
            Random random = new Random();
            int randomDigit = random.nextInt((specialCharactersLength -1));

            List<Character> digits = new ArrayList<>();

            while (digits.size() < length ) {
                if (!digits.contains(characters[randomDigit])) {
                    digits.add(characters[randomDigit]);
                }
                randomDigit = random.nextInt(specialCharactersLength);
            }
            StringBuilder finalNumber = new StringBuilder();

            for (int i = 0; i < digits.size(); i++) {
                finalNumber.append(digits.get(i));
            }

            StringBuilder reversedCode = finalNumber.reverse();
            secretCode = reversedCode;
            StringBuilder stars = new StringBuilder("");
            for (int i= 1 ; i <= length; i++ ){
                stars.append("*");
            }
            String range = specialCharactersLength < 11 ?  "(0-" + (specialCharactersLength-1) +")." : "(0,9),(a-" + characters[(specialCharactersLength-1)] + ").";
            System.out.println("The secret is prepared: " + stars + " " + range);
            System.out.println("Okay, let's start a game!");
        }
        else {
            System.out.println("Error: can't generate a secret number with a length of " + length +  " and " + specialCharactersLength + " because there aren't enough unique digits.");

        }

    }
    public static void main(String[] args) {
        generateUniqueCode();
        playGame();

    }
}
