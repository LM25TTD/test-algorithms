#include <iostream>
#include "ReplaceUtils.hpp"

using namespace std;


int main() {
	ReplaceUtils replacer;

	char str[MAX_LENGTH] = "User is not allowed   ";
	int newSize = replacer.replaceSpaces(str, 19);

	_ASSERT(newSize == 25);
	_ASSERT(strcmp(str, "User&32is&32not&32allowed") == 0);

	cout << "Output: " << str << endl;
	cout << "New size: " << newSize << endl << endl;

	cout << "Press any key to continue..." << endl;
	cin.get();

	return 0;
}