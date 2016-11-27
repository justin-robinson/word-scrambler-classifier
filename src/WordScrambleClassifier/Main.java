/*
   Assumptions:
    1. No allowed combinations of vowels and consonants contain both vowels and consonants IE "ASM" is not an exception because it contains a vowel and consonant
    2. scrambledWord and word are the same length
    3. scrambledWord and word contain the same letter frequency
    4. word has length > 0
 */

package WordScrambleClassifier;

public class Main {

    public static void main(String[] args) {

        System.out.println(classify("MAPS", "SPAM"));
        System.out.println(classify("RIONY", "IRONY"));
        System.out.println(classify("ONYRI", "IRONY"));
        System.out.println(classify("IRONY", "IRONY"));
        System.out.println(classify("INOYR", "IRONY"));
        System.out.println(classify("IOYRN", "IRONY"));
        System.out.println(classify("breristhogerl", "rothlisberger"));
        System.out.println(classify("brerithrogesl", "rothlisberger"));
        System.out.println(classify("breristlogerh", "rothlisberger"));
        System.out.println(classify("rothlisberger", "rothlisberger"));

    }

    private static String classify(String scrambledWord, String word) {
        WordScrambleClassifier ws = new WordScrambleClassifier(scrambledWord, word);
        Integer scrambleClassification = ws.classify();
        String message;
        switch(scrambleClassification) {
            case WordScrambleClassifier.SCRAMBLE_CLASSIFICATION_NOT:
                message = "not";
                break;
            case WordScrambleClassifier.SCRAMBLE_CLASSIFICATION_POOR:
                message = "poor";
                break;
            case WordScrambleClassifier.SCRAMBLE_CLASSIFICATION_HARD:
                message = "hard";
                break;
            case WordScrambleClassifier.SCRAMBLE_CLASSIFICATION_FAIR:
            default:
                message = "fair";
                break;
        }

        return scrambledWord + " is a " + message + " scrambled of " + word;
    }
}
