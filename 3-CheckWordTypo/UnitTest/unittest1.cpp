#include "stdafx.h"
#include "CppUnitTest.h"
#include "../3-CheckWordTypo/WordCheckerUtils.hpp"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTest
{		
	TEST_CLASS(UnitTest1)
	{
	public:
		
		TEST_METHOD(TestCheckWordTypos)
		{
			WordCheckerUtils checker;

			Assert::IsTrue(checker.hasWordOneOrLessTypo("pale", "ple"));
			Assert::IsTrue(checker.hasWordOneOrLessTypo("pales", "pale"));
			Assert::IsTrue(checker.hasWordOneOrLessTypo("pale", "bale"));
			Assert::IsFalse(checker.hasWordOneOrLessTypo("pale", "bake"));
		}

		TEST_METHOD(TestCheckWordBigLengthDiff)
		{
			WordCheckerUtils checker;

			Assert::IsFalse(checker.hasWordOneOrLessTypo("pale", "pa"));
			Assert::IsFalse(checker.hasWordOneOrLessTypo("pales", "al"));
			Assert::IsFalse(checker.hasWordOneOrLessTypo("pales", "les"));
			Assert::IsFalse(checker.hasWordOneOrLessTypo("misplace", "mispl"));
		}

		TEST_METHOD(TestCheckWordRemainingDiffAfterLoop)
		{
			WordCheckerUtils checker;

			Assert::IsFalse(checker.hasWordOneOrLessTypo("pale", "pxl"));
			Assert::IsFalse(checker.hasWordOneOrLessTypo("cakx", "cakes"));
			Assert::IsTrue(checker.hasWordOneOrLessTypo("pales", "palex"));
		}

	};
}