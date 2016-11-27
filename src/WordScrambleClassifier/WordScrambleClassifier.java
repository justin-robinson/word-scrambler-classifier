package WordScrambleClassifier;

class WordScrambleClassifier {

    private Word scrambledWord;
    private Word word;

    static final int SCRAMBLE_CLASSIFICATION_NOT  = 0;
    static final int SCRAMBLE_CLASSIFICATION_POOR = 1;
    static final int SCRAMBLE_CLASSIFICATION_FAIR = 2;
    static final int SCRAMBLE_CLASSIFICATION_HARD = 3;

    private static final int SCRAMBLE_LETTERS_OTHER = 0;
    private static final int SCRAMBLE_LETTERS_FIRST = 1;
    private static final int SCRAMBLE_LETTERS_CONSECUTIVE_TWO = 2;
    private static final int SCRAMBLE_LETTERS_NONE = 3;

    WordScrambleClassifier(String scrambledWord, String word) {
        this.scrambledWord = new Word(scrambledWord);
        this.word = new Word(word);
    }

    /**
     * Classifies the scrambled as
     * Not –  if the word is not scrambled at all
     * Poor – if the first letter or any two consecutive letters are in the correct place and the word doesn't look real
     * Hard – if none of the letters are in the correct place and the word looks real
     * Fair – for all other cases
     * @return integer code for scramble classification
     */
    int classify () {

        if ( word.getWord().equals(scrambledWord.getWord()) ) {
            return SCRAMBLE_CLASSIFICATION_NOT;
        }

        Integer lettersScrambled = calculateLettersScrambled();
        if ( scrambledWord.looksReal() ) {
            if ( lettersScrambled.equals(SCRAMBLE_LETTERS_NONE) ){
                return SCRAMBLE_CLASSIFICATION_HARD;
            }
        } else {
            if ( lettersScrambled.equals(SCRAMBLE_LETTERS_FIRST) || lettersScrambled.equals(SCRAMBLE_LETTERS_CONSECUTIVE_TWO)) {
                return SCRAMBLE_CLASSIFICATION_POOR;
            }
        }

        return SCRAMBLE_CLASSIFICATION_FAIR;
    }

    /**
     * Determines what level the letters are scrambled at:
     * - the first letter is in the correct place
     * - any two consecutive letters are in the correct place
     * - none of the letters are in the correct place
     * - other: all other cases
     *
     * @return integer code for letter scramble
     */
    private int calculateLettersScrambled () {

        // short circuit is the first letter isn't scrambled
        if ( word.getWord().charAt(0) == scrambledWord.getWord().charAt(0) ) {
            return SCRAMBLE_LETTERS_FIRST;
        }

        // first letter is not in the right place after the above return statement fails
        boolean previousLetterIsInCorrectPlace = false;
        boolean thisLetterIsInCorrectPlace;
        int lettersInCorrectPlaceCount = 0;

        // check the remaining letters for two consecutive ones in the correct place
        for ( int i=1; i<word.getWord().length(); ++i) {

            thisLetterIsInCorrectPlace = word.getWord().charAt(i) == scrambledWord.getWord().charAt(i);

            if ( thisLetterIsInCorrectPlace ) {
                if ( previousLetterIsInCorrectPlace ) {
                    return SCRAMBLE_LETTERS_CONSECUTIVE_TWO;
                }
                ++lettersInCorrectPlaceCount;
            }
            previousLetterIsInCorrectPlace = thisLetterIsInCorrectPlace;
        }

        if ( lettersInCorrectPlaceCount == 0 ) {
            return SCRAMBLE_LETTERS_NONE;
        }

        return SCRAMBLE_LETTERS_OTHER;
    }
}
