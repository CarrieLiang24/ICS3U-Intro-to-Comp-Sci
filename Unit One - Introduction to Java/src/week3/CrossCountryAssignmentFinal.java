package week3;

import java.util.Scanner;

public class CrossCountryAssignmentFinal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        processRunner(in); //runner 1
        processRunner(in); //runner 2
        processRunner(in); //runner 3
    }
    private static void processRunner(Scanner in) {
        System.out.println();
        System.out.print("Please enter your first and last name: ");
        String runnerName = in.nextLine();
        int space = runnerName.indexOf(" ");
        String fname = runnerName.substring(0,space);
        String lname = runnerName.substring(space + 1);

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
        System.out.println("Runner Summary");
        System.out.println("**************");
        System.out.println();
        System.out.println("Runner: " + lname + ", " + fname);
        System.out.println("Split One: " + firstSplit);
        
        double secondSplitInSeconds = timeInSeconds2 - timeInSeconds1;
        System.out.println("Split 2: " + convertSecondsToTime(secondSplitInSeconds));

        double thirdSplitInSeconds = timeInSeconds3 - timeInSeconds2;
        System.out.println("Split 3: " + convertSecondsToTime(thirdSplitInSeconds));

        System.out.println("Total: " + totalTime);
        System.out.println();

    }
    
    /**
     * Converts split time in seconds into mm:ss.sss
     * @param thirdSplitInSeconds time in seconds for split 3 (double)
     * @return split in mm:ss.sss
     */
    private static String convertSecondsToTime(double timeInSeconds) {
    
        int minutes = (int)(timeInSeconds / 60);
        double seconds = (double)(timeInSeconds - (minutes * 60));
        
        return String.format("%d:%06.3f", minutes, seconds);
    }
    /**
     * Converts a time (ex. 5:34.221) to double seconds (ex. 334.331)
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
