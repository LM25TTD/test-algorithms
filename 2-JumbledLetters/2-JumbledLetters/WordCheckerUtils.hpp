#pragma once
#include <iostream>

using namespace std;

class WordCheckerUtils
{
public:
	WordCheckerUtils();
	virtual ~WordCheckerUtils();
	bool isWordJumbled(const char *original, const char *jumbled);
};



WordCheckerUtils::WordCheckerUtils()
{
}


WordCheckerUtils::~WordCheckerUtils()
{
}

// Time complexity: O(n)
// Space complexity: O(n)
// Auxiliary complexity: O(1)
bool WordCheckerUtils::isWordJumbled(const char *word, const char *jumbled)
{
	auto wordLen = strlen(word);
	auto jumbledLen = strlen(jumbled);

	// Easy detection cases
	if (wordLen != jumbledLen || word[0] != jumbled[0])
	{
		return false;
	}

	// Consider jumbled word differences up to 2/3 of word length
	int threshold = (wordLen / 3) * 2;

	int changedPlaces = 0;

	// Time complexity: O(n)
	for (int i = 1; i < wordLen; i++)
	{
		if (word[i] != jumbled[i])
		{
			changedPlaces++;
			if (changedPlaces > threshold)
			{
				return false;
			}
		}
	}

	return true;
}

