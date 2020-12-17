package org.icij.datashare.io;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


public class Main {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(RemoteFilesProdCheck.class);
        resultReport(result);
        System.exit(result.getFailureCount());
    }

    public static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
          result.getFailureCount() + ". Ignored: " +
          result.getIgnoreCount() + ". Tests run: " +
          result.getRunCount() + ". Time: " +
          result.getRunTime() + "ms.");
    }
}
