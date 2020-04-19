#include "stdafx.h"
#include "CppUnitTest.h"
#include "../2-JumbledLetters/WordCheckerUtils.hpp"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTest
{		
	TEST_CLASS(UnitTest1)
	{
	public:
		
		TEST_METHOD(TestCommonCases)
		{
			WordCheckerUtils checker;

			Assert::AreEqual(checker.isWordJumbled("you", "yuo"), true);
			Assert::AreEqual(checker.isWordJumbled("probably", "porbalby"), true);
			Assert::AreEqual(checker.isWordJumbled("despite", "desptie"), true);
			Assert::AreEqual(checker.isWordJumbled("moon", "nmoo"), false);
			Assert::AreEqual(checker.isWordJumbled("misspellings", "mpeissngslli"), false);
		}

		TEST_METHOD(TestSmallWords)
		{
			WordCheckerUtils checker;

			Assert::AreEqual(checker.isWordJumbled("yo", "yu"), false);
			Assert::AreEqual(checker.isWordJumbled("yo", "yo"), true);
			Assert::AreEqual(checker.isWordJumbled("me", "mx"), false);
			Assert::AreEqual(checker.isWordJumbled("me", "me"), true);
			Assert::AreEqual(checker.isWordJumbled("to", "tl"), false);
			Assert::AreEqual(checker.isWordJumbled("to", "to"), true);
			Assert::AreEqual(checker.isWordJumbled("t", "x"), false);
			Assert::AreEqual(checker.isWordJumbled("t", "t"), true);

		}

	};
}