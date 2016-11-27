package WordScrambleClassifier;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Word {

    private static final Set<Character> vowels = Stream.of(
            'a',
            'e',
            'i',
            'o',
            'u',
            'y'
    ).collect(Collectors.toCollection(HashSet::new));

    private static final Set<String> allowedVowelConsonantCombinations = Stream.of(
            "ai",
            "ay",
            "ea",
            "ee",
            "eo",
            "io",
            "oa",
            "oo",
            "oy",
            "ya",
            "yo",
            "yu",
            "bl",
            "br",
            "ch",
            "ck",
            "cl",
            "cr",
            "dr",
            "fl",
            "fr",
            "gh",
            "gl",
            "gr",
            "kl",
            "kr",
            "kw",
            "pf",
            "pl",
            "pr",
            "sc",
            "sch",
            "scr",
            "sh",
            "shr",
            "sk",
            "sl",
            "sm",
            "sn",
            "sp",
            "sq",
            "st",
            "sw",
            "th",
            "thr",
            "tr",
            "tw",
            "wh",
            "wr"
    ).collect(Collectors.toCollection(HashSet::new));

    private String word;

    Word (String word){
        this.word = word;
    }

    String getWord() {
        return word;
    }

    Boolean looksReal () {

        if ( word.length() == 0 ) {
            return false;
        }
        boolean previousLetterIsVowel = vowels.contains(Character.toLowerCase(word.charAt(0)));
        boolean thisLetterIsVowel;
        int previousLetterSoundToggle = 0;
        for ( int i=1; i<word.length(); ++i) {
            char letter = Character.toLowerCase(word.charAt(i));
            thisLetterIsVowel = vowels.contains(Character.toLowerCase(word.charAt(i)));

            // is this letter and the previous letter BOTH a vowel or BOTH a consonant
            if ( thisLetterIsVowel == previousLetterIsVowel ) {

                int endIndex = i == word.length() ? i : i+1;

                boolean combinationIsException = allowedVowelConsonantCombinations
                        .contains(word.substring(previousLetterSoundToggle, endIndex).toLowerCase());

                // if vowel and not in the exception list
                if ( thisLetterIsVowel && !combinationIsException) {
                    return false;
                }

                // if consonant, longer than 2 character, and not in exception list
                if ( !thisLetterIsVowel && (i-previousLetterSoundToggle)>=2 && !combinationIsException) {
                    return false;
                }
            } else {
                previousLetterSoundToggle = i;
            }
            previousLetterIsVowel = thisLetterIsVowel;
        }

        return true;
    }
}
