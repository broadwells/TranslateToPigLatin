//author: Stephanie Broadwell
//lab #6
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean translate = true;       //boolean for beginning the while loop
        boolean casing;     //boolean for determining the case of the word(s) within the findCase method

        System.out.println("Let's translate to Pig Latin! \nEt'slay anslatetray otay igpay atinlay!");

        while (translate) {
            System.out.println("Please enter a word or sentence to be translated: ");
            String userPrompt1 = scan.nextLine();

            for (int i = 0; i < userPrompt1.length(); i++) {    //validating the input contains only letters, no numbers
                char x = userPrompt1.charAt(i);
                if (Character.isDigit(x)) {
                    System.out.println("Please enter valid word(s) with no numbers. " +
                            "\nEnter word or sentence to be translated: ");
                    userPrompt1 = scan.nextLine();
                }
            }
            //parseSentence method is called to find all whitespaces w/in the input & store the words inside an array
            String[] userPrompt2 = parseSentence(userPrompt1);
            String finalOutput = "";
            for (String input : userPrompt2) {
                casing = findCase(input);
                finalOutput = finalOutput + " " + translateInput(input, casing);
            }
            System.out.println("Translation: " + finalOutput + " ");

            System.out.println("Do you want to translate another word? (y/n): ");
            String yesNo = scan.nextLine();
            if (yesNo.equalsIgnoreCase("n")) {
                System.out.println("Goodbye! -- Oodbyegay!");
                translate = false;
            }
        }
    }
    //translateInput method is called with userPrompt and casing to begin translation
    public static String translateInput(String userInput, boolean casing) {
        String changedInput = userInput;    //storing the original userInput to keep the case
        userInput = userInput.toLowerCase();    //changing the userInput to lowercase for simpler translation
        if ((userInput.startsWith("a")) || userInput.startsWith("e") || userInput.startsWith("i")
                || userInput.startsWith("o") || userInput.startsWith("u")){
            if (casing) {
                changedInput = changedInput.concat("WAY");
            }
            else {
                changedInput = changedInput.concat("way");
            }
        }
        else {
            for (int i = 0; i < userInput.length(); i++) {  //for loop used to find the index of first vowel
                if ((userInput.charAt(i) == 'a') || (userInput.charAt(i) == 'e') || (userInput.charAt(i) == 'i') ||
                        (userInput.charAt(i) == 'o') || (userInput.charAt(i) == 'u')) {
                    //storing the substring sections before the first vowel and after the first vowel
                    // using variables; consonant and remainingInput
                    String consonant = changedInput.substring(0, i);
                    String remainingInput = changedInput.substring(i, changedInput.length());
                    if (casing) {
                        changedInput = remainingInput + consonant + "AY";
                    }
                    else {
                        if (changedInput.charAt(0) == changedInput.toUpperCase().charAt(0)) {
                            remainingInput = remainingInput.substring(0, 1).toUpperCase() + remainingInput.substring(1);
                            consonant = consonant.substring(0,1).toLowerCase() + consonant.substring(1);
                            changedInput = remainingInput + consonant + "ay";
                        }
                        else {
                            changedInput = remainingInput + consonant + "ay";
                        }
                    }
                    break;      //break used to exit for loop/if statement once the first vowel is found
                }
            }
        }
        return changedInput;
    }
    public static boolean findCase(String caseOfWord) {
        //findCase method is setting boolean casing to true if the input word is all Uppercase and
        // false for if input word is all Lowercase/Title case
        boolean casing;
        if (caseOfWord == caseOfWord.toUpperCase()) {
            casing = true;
        }
        else if (caseOfWord == caseOfWord.substring(0,1).toUpperCase()){
            casing = false;
        }
        else {      //if the input word is all Lowercase
            casing = false;
        }
        return casing;
    }
    //separating the words from whitespaces and placing in an array
    public static String[] parseSentence(String userInput) {
        String[] wordsFromInput;
        wordsFromInput = userInput.split(" ");
        return wordsFromInput;
    }
}
