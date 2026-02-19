import java.io.*;
import java.util.*;

public class Lab5
{
    /**
     *  Problem: Sort arr into blocks of the same length using counting sort.
     *  
     */
    private static void problem(byte[][] arr)
    {   
       //Implement me!
        if (arr.length <= 1) {
            return;
        }


        int maxLen = 0;
        byte[][] finalArr = new byte[arr.length][];

        for (byte[] subArr : arr) {
            if (maxLen < subArr.length) {
                maxLen = subArr.length;
            }
        }

        int[] count = new int[maxLen+1];
        int[] prefix = new int[maxLen+1];


        for (byte[] subArr : arr) {
            count[subArr.length]++;
        }



        prefix[0] = count[0];
        for (int i = 1; i <= maxLen; i++) {
            prefix[i] = prefix[i-1] + count[i];
        }


        for (int i = arr.length-1; i >= 0; i--) {
            int len = arr[i].length;  // 4
            int idx = prefix[len] - 1;
            finalArr[idx] = arr[i];
            prefix[len]--;
        }


        System.arraycopy(finalArr, 0, arr, 0, arr.length);



    }
    

    // ---------------------------------------------------------------------
    // Do not change any of the code below!

    private static final int LabNo = 5;
    private static final Random rng = new Random(654321);

    private static boolean testProblem(byte[][] testCase)
    {
        byte[][] numbersCopy = new byte[testCase.length][];

        // Create copy.
        for (int i = 0; i < testCase.length; i++)
        {
            numbersCopy[i] = testCase[i].clone();
        }

        // Sort
        problem(testCase);
        Arrays.sort(numbersCopy, new numberComparator());
        
        if(testCase.length != numbersCopy.length)
        {
            return false;
        }
        
        for (int i = 0; i < testCase.length; i++)
        {
            if(testCase[i].length != numbersCopy[i].length)
            {
               return false;
            }        
        }
        
        return true;
    }

    
    private static class numberComparator implements Comparator<byte[]>
    {
        @Override
        public int compare(byte[] n1, byte[] n2)
        {
            return n1.length - n2.length;
        }
    }

    public static void main(String args[])
    {
        System.out.println("CS 302 -- " + " -- Lab " + LabNo);
        testProblems();
    }

    private static void testProblems()
    {
        int noOfLines = 10000;

        System.out.println("-- -- -- -- --");
        System.out.println(noOfLines + " test cases.");

        boolean passedAll = true;

        for (int i = 1; i <= noOfLines; i++)
        {
            byte[][] testCase =  createTestCase(i);

            boolean passed = false;
            boolean exce = false;

            try
            {
                passed = testProblem(testCase);
            }
            catch (Exception ex)
            {
                passed = false;
                exce = true;
            }

            if (!passed)
            {
                System.out.println("Test " + i + " failed!" + (exce ? " (Exception)" : ""));
                passedAll = false;

                break;
            }
        }

        if (passedAll)
        {
            System.out.println("All test passed.");
        }

    }

    private static byte[][] createTestCase(int testNo)
    {
        int maxSize = Math.min(100, testNo) + 5;
        int size = rng.nextInt(maxSize) + 5;

        byte[][] numbers = new byte[size][];

        for (int i = 0; i < size; i++)
        {
            int digits = rng.nextInt(maxSize) + 1;
            numbers[i] = new byte[digits];

            for (int j = 0; j < digits - 1; j++)
            {
                numbers[i][j] = (byte)rng.nextInt(128);
            }

            // Ensures that the most significant digit is not 0.
            numbers[i][digits - 1] = (byte)(rng.nextInt(127) + 1);
        }

        return numbers;
    }

}
