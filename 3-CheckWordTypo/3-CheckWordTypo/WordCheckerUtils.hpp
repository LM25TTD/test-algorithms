#pragma once
#include <iostream>

#define SAME_LEN 0
#define ORIGINAL_BIGGER 1
#define TO_CHECK_BIGGER 2

using namespace std;


class WordCheckerUtils
{
public:
	WordCheckerUtils();
	virtual ~WordCheckerUtils();
	bool hasWordOneOrLessTypo(const char *original, const char *toCheck);
};



WordCheckerUtils::WordCheckerUtils()
{
}


WordCheckerUtils::~WordCheckerUtils()
{
}

bool WordCheckerUtils::hasWordOneOrLessTypo(const char *original, const char *toCheck)
{
	auto originalSize = strlen(original);
	auto toCheckSize = strlen(toCheck);
	auto sizeDifference = abs(static_cast<double>(originalSize - toCheckSize));

	// Size greater than one represents more than one typo
	if (sizeDifference > 1)
	{
		return false;
	}

	// Classify difference between the size of the two words
	const char lengthClassification = 
		originalSize == toCheckSize ?
		SAME_LEN :
		originalSize > toCheckSize ?
		ORIGINAL_BIGGER :
		TO_CHECK_BIGGER;

	int differences = 0; // Count of edits 

	int originalIndex = 0, toCheckIndex = 0;

	// Use edit distance strategy
	while (originalIndex < originalSize
		&& toCheckIndex < toCheckSize)
	{
		// Check differences between words
		if (original[originalIndex] != 
			toCheck[toCheckIndex])
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
