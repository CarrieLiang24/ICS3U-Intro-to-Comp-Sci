package week3;

import java.util.Scanner;

public class runningtest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        processRunner(in); //runner 1
        
    }
    private static void processRunner(Scanner in) {
        System.out.println();
        System.out.print("Please enter your first and last name: ");
        String runnerOneName = in.nextLine();
        int space = runnerOneName.indexOf(" ");
        String fname = runnerOneName.substring(0,space);
        String lname = runnerOneName.substring(space + 1);

        System.out.print(fname + ", please enter your mile one time (mm:ss.sss): ");
        String firstSplit = in.nextLine();
        double timeInSeconds1 = convertToSeconds(firstSplit);
        

        System.out.print(fname + ", please enter your time to the second mile (mm:ss.sss): ");
        String secondSplit = in.nextLine();
        double timeInSeconds2 = convertToSeconds(secondSplit);
        

        System.out.print(fname + ", please enter your time to the end of the 5km (mm:ss.sss): ");
        String totalTime = in.nextLine();
        double timeInSeconds3 = convertToSeconds(totalTime);
        
        System.out.println();
        System.out.println("Runner One Summary");
        System.out.println("******************");
        System.out.println();
        System.out.println("Runner: " + lname + ", " + fname);
        System.out.println("Split One: " + firstSplit);
        
        double secondSplitInSeconds = timeInSeconds2 - timeInSeconds1;
        double timeInMins = convertToMins(secondSplitInSeconds);
        System.out.println(timeInMins);

        double thirdSplitInSeconds = timeInSeconds3 - timeInSeconds2;
        double timeInMins3 = convertToMins3(thirdSplitInSeconds);
        System.out.println(timeInMins3);

        System.out.println("Total: " + totalTime);
        System.out.println();

    }
    /**
     * Converts split 2 into race format
     * @param secondSplitInSeconds time in seconds for split 2 (double)
     * @return second split in mm:ss.sss
     */
    private static double convertToMins(double secondSplitInSeconds) {
    
        int secondsAsMinutes = (int)(secondSplitInSeconds / 60);
        double secondsRemain = (double)(secondSplitInSeconds - (secondsAsMinutes * 60));
        System.out.print("Split Two: " + secondsAsMinutes +":");
        return secondsRemain;
    }
    /**
     * Converts split 3 into race format
     * @param thirdSplitInSeconds time in seconds for split 3 (double)
     * @return third split in mm:ss.sss
     */
    private static double convertToMins3(double thirdSplitInSeconds) {
    
        int thirdAsMinutes = (int)(thirdSplitInSeconds / 60);
        double thirdRemain = (double)(thirdSplitInSeconds - (thirdAsMinutes * 60));
        System.out.print("Split Three: " + thirdAsMinutes +":");
        return thirdRemain;
    }
    /**
     * Converts a time (5:34.221) to double seconds (334.331)
     * @param firstSplit time in the format mm:ss.sss
     * @return converts race time into seconds
     */
    private static double convertToSeconds(String firstSplit) {
        int colon = firstSplit.indexOf(":");
        int minutesAsSeconds = Integer.parseInt(firstSplit.substring(0,colon))*60;
        double seconds = Double.parseDouble(firstSplit.substring(colon + 1));

        return minutesAsSeconds + seconds;
    }
}
