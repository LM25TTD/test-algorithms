#include "stdafx.h"
#include "CppUnitTest.h"
#include "../1-StringReplacement/ReplaceUtils.hpp"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTests
{		
	TEST_CLASS(UnitTest1)
	{
	public:
		
		TEST_METHOD(TestNospaceReplacement)
		{
			ReplaceUtils replacer;

			char str[MAX_LENGTH] = "UserIsNotAllowed   ";
			int newSize = replacer.replaceSpaces(str, 16);

			Assert::AreEqual(16, newSize);
			Assert::AreEqual("UserIsNotAllowed", str);
		}

		TEST_METHOD(TestSpaceReplacement)
		{
			ReplaceUtils replacer;

			char str[MAX_LENGTH] = "User is not allowed   ";
			int newSize = replacer.replaceSpaces(str, 19);

			Assert::AreEqual(25, newSize);
			Assert::AreEqual("User&32is&32not&32allowed", str);
		}

		TEST_METHOD(TestOnlySpacesReplacement)
		{
			ReplaceUtils replacer;

			char str[MAX_LENGTH] = "      ";
			int newSize = replacer.replaceSpaces(str, 6);

			Assert::AreEqual(18, newSize);
			Assert::AreEqual("&32&32&32&32&32&32", str);
		}

		TEST_METHOD(TestExceedMaximumSize)
		{
			ReplaceUtils replacer;

			char str[MAX_LENGTH] = "      ";
			int newSize = replacer.replaceSpaces(str, MAX_LENGTH + 1);

			Assert::AreEqual(-1, newSize);
			Assert::AreEqual("      ", str);
		}

	};
}