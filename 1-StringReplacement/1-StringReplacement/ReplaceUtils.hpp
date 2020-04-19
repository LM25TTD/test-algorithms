#pragma once

#define MAX_LENGTH 2048
#define WHITESPACE ' '
#define STR_TERMINATOR '\0'

class ReplaceUtils
{
public:
	ReplaceUtils();
	virtual ~ReplaceUtils();

	int replaceSpaces(char str[], int realSize);
};



ReplaceUtils::ReplaceUtils()
{
}


ReplaceUtils::~ReplaceUtils()
{
}


// Time complexity: O(n)
// Space complexity: O(n)
// Auxiliary complexity: O(1)
int ReplaceUtils::replaceSpaces(char str[], int realSize)
{
	int totalWhitepaces = 0;

	// Count space
	for (int i = 0; i < realSize; i++) {
		if (str[i] == WHITESPACE) {
			totalWhitepaces++;
		}
	}

	// Find new length. 
	// Sum: real size of the string (including whitespace, each current withespace will be replaced by '&')
	// Sum: 2 * whitespaces because we will include more 2 characters for each found whitespace ('32')
	// Sum: 1 for string terminator
	int newSize = realSize + totalWhitepaces * 2 + 1;

	// Check sanity of the new size of array
	if (newSize > MAX_LENGTH) {
		return -1;
	}

	// Start filling character from end 
	int currIndex = newSize;

	// Last character must be the string terminator
	str[--currIndex] = STR_TERMINATOR;

	// Fill rest of the string from end
	for (int i = realSize - 1; i >= 0; i--)
	{
		// inserts &32 in place of space 
		if (str[i] == WHITESPACE)
		{
			str[--currIndex] = '2';
			str[--currIndex] = '3';
			str[--currIndex] = '&';
		}
		else
		{
			str[--currIndex] = str[i];
		}
	}

	return newSize - 1;
}
