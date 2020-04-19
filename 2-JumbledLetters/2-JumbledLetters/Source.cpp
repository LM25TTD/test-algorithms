#include <iostream>
#include "WordCheckerUtils.hpp"

using namespace std;


int main() {

	WordCheckerUtils checker;

	cout << boolalpha;

	cout << "you, yuo: " << checker.isWordJumbled("you", "yuo") << endl;
	cout << "probably, porbalby: " << checker.isWordJumbled("probably", "porbalby") << endl;
	cout << "despite, desptie: " << checker.isWordJumbled("despite", "desptie") << endl;
	cout << "moon, nmoo: " << checker.isWordJumbled("moon", "nmoo") << endl;
	cout << "misspellings, mpeissngslli: " << checker.isWordJumbled("misspellings", "mpeissngslli") << endl;

	cin.get();

	return 0;
}