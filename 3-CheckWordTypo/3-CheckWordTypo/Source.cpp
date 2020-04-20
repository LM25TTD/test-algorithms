#include <iostream>
#include "WordCheckerUtils.hpp"

using namespace std;


int main() {

	WordCheckerUtils checker;

	cout << boolalpha;

	cout << "pale, ple: " << checker.hasWordOneOrLessTypo("pale", "ple") << endl;
	cout << "pales, pale: " << checker.hasWordOneOrLessTypo("pales", "pale") << endl;
	cout << "pale, bale: " << checker.hasWordOneOrLessTypo("pale", "bale") << endl;
	cout << "pale, bake: " << checker.hasWordOneOrLessTypo("pale", "bake") << endl;

	cin.get();

	return 0;
}