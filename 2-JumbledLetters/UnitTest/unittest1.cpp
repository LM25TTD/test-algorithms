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

			Assert::IsTrue(checker.isWordJumbled("you", "yuo"));
			Assert::IsTrue(checker.isWordJumbled("probably", "porbalby"));
			Assert::IsTrue(checker.isWordJumbled("despite", "desptie"));
			Assert::IsFalse(checker.isWordJumbled("moon", "nmoo"));
			Assert::IsFalse(checker.isWordJumbled("misspellings", "mpeissngslli"));
		}

		TEST_METHOD(TestSmallWords)
		{
			WordCheckerUtils checker;

			Assert::IsFalse(checker.isWordJumbled("yo", "yu"));
			Assert::IsTrue(checker.isWordJumbled("yo", "yo"));
			Assert::IsFalse(checker.isWordJumbled("me", "mx"));
			Assert::IsTrue(checker.isWordJumbled("me", "me"));
			Assert::IsFalse(checker.isWordJumbled("to", "tl"));
			Assert::IsTrue(checker.isWordJumbled("to", "to"));
			Assert::IsFalse(checker.isWordJumbled("t", "x"));
			Assert::IsTrue(checker.isWordJumbled("t", "t"));

		}

	};
}