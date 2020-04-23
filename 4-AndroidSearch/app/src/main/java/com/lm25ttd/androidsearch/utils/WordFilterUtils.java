package com.lm25ttd.androidsearch.utils;

public class WordFilterUtils {

    enum LengtClassification {
        SAME_LEN,
        ORIGINAL_BIGGER,
        TO_CHECK_BIGGER
    }

    public boolean isWordJumbled(CharSequence word, CharSequence jumbled) {
        int wordLen = word.length();
        int jumbledLen = jumbled.length();

        // Easy detection cases
        if (wordLen != jumbledLen || word.charAt(0) != jumbled.charAt(0)) {
            return false;
        }

        // Consider jumbled word differences up to 2/3 of word length
        int threshold = (wordLen / 3) * 2;

        int changedPlaces = 0;

        // Time complexity: O(n)
        for (int i = 1; i < wordLen; i++) {
            if (word.charAt(i) != jumbled.charAt(i)) {
                changedPlaces++;
                if (changedPlaces >= threshold) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean hasWordOneOrLessTypo(CharSequence original, CharSequence toCheck) {
        int originalSize = original.length();
        int toCheckSize = toCheck.length();
        int sizeDifference = Math.abs(originalSize - toCheckSize);

        // Size greater than one represents more than one typo
        if (sizeDifference > 1) {
            return false;
        }

        // Classify difference between the size of the two words
        LengtClassification lengthClassification =
            originalSize == toCheckSize ?
                    LengtClassification.SAME_LEN :
                    originalSize > toCheckSize ?
                            LengtClassification.ORIGINAL_BIGGER :
                            LengtClassification.TO_CHECK_BIGGER;

        int differences = 0; // Count of edits
        int originalIndex = 0, toCheckIndex = 0;

        // Use edit distance strategy
        while (originalIndex < originalSize
                && toCheckIndex < toCheckSize)
        {
            // Check differences between words
            if (original.charAt(originalIndex) !=
                    toCheck.charAt(toCheckIndex))
            {
                differences++;

                if (differences > 1) {
                    return false;
                }

                switch (lengthClassification)
                {
                    // Move next for both words
                    case SAME_LEN:
                        originalIndex++;
                        toCheckIndex++;
                        break;
                    // Move only over original word
                    case ORIGINAL_BIGGER:
                        originalIndex++;
                        break;
                    // Move only over toCheck word
                    case TO_CHECK_BIGGER:
                        toCheckIndex++;
                        break;
                }
            }
            else // Move next for both words
            {
                originalIndex++;
                toCheckIndex++;
            }
        }

        // After iterate over the words,
        // if there is there is some non-iterated index
        // in any word, this must be summed to differences
        if (originalSize > originalIndex
                || toCheckSize > toCheckIndex)
        {
            differences++;
        }

        return differences == 1;
    }
}
