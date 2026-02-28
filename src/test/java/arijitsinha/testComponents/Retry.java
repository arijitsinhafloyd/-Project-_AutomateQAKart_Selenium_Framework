package arijitsinha.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count=0;
	int maxTry=1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry) {
			count++;
			return true;//when the test reaches this block it has failed; and it will run until it gets false
		}
		return false;
	}
}

//✅ If test passes in first attempt → retry() is never called

//❌ If test fails in first attempt → retry() is called

//If retry() returns true → TestNG re-runs the test

//If retry() returns false → Test is marked as failed