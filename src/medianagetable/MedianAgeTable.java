/* PROJECT:  MedianAgeTable
 * AUTHOR:   Drew Rautenberg                   DESIGNER:  Dr. Kaminski
 * DESCRIPTION: This prepares a report to examine the median age of various
 *      regions of the world.
 * NOTE:  "Median" is the value in a group of numbers where half the group is >
 *      this value, and half the group < the value.  This is generally a better
 *      indicator of the group than "mean" is since it reduces the significance
 *      of very big or very small outliers.
 * NOTE:  You're NOT calculating the median - the median age for each country
 *      is a data value in the input file.
 * INPUT:  MedianAge.csv
 *      [NOTE:  Countries with missing data were removed from file].
 *      Fields:  Name,Continent,Region,LifeExp,Population,MedianAge
 *      Header record - so skip over that.
 * OUTPUT:  to Console window
 *      See sample report below in a comment in the code.
 *      [NOTE:  Data in sample report is NOT ACCURATE, it's just to show format].
 * PROCESSING:
 *      - use array of continent names for printing and help in "searching"
 *              for the correct category
 *      - use methods, whichRow & whichCol to determine appropriate counter
 *          to ++
 *      - can calculate the stats using a STREAM algorithm, so no need to store
 *          the raw data in parallel arrays
 *****************************************************************************/
package medianagetable;

import java.io.*;
import java.util.Scanner;

public class MedianAgeTable {

    static final int NUM_CONTINENTS = 6;
    static final int NUM_CATS = 4;

    public static void main(String[] args) throws IOException {
        // - - - - - - - - - - - - - - - - - - - - - - DECLARATIONS & OPEN FILE
        File f = new File("MedianAge.csv");
        Scanner inFile = new Scanner(f);
        String[] contNames = {"Africa", "Asia", "Europe",
                "North America", "Oceania", "South America"};
        String record;                                          // a line
        String[] field = new String[6];
        String name;
        String continent;
        int age;

        //2D matrix of counters (declare & initialized to 0's)
        int[][] counters = new int[NUM_CONTINENTS][NUM_CATS];
        for (int i = 0; i < NUM_CONTINENTS; i++) {
            for (int j = 0; j < NUM_CATS; j++) {
                counters[i][j] = 0;
            }
        }

        int tempMinAge = Integer.MAX_VALUE;
        String tempMinName = "";
        int tempMaxAge = Integer.MIN_VALUE;
        String tempMaxName = "";
        int usaAge =-1;
        // - - - - - - - - - - - - - - - - - PROCESS FILE TO CALCULATE DO STATS

        inFile.nextLine();                           // skip over Header Record

        while (inFile.hasNext()) {
            record = inFile.nextLine();
            field = record.split(",");
            name = field[0];
            continent = field[1];
            age = Integer.parseInt(field[5]);

            if (age < tempMinAge) {
                tempMinAge = age;
                tempMinName = name;
            }

            if (age > tempMaxAge) {
                tempMaxAge = age;
                tempMaxName = name;
            }

            if (name.compareTo("United States") == 0)
                usaAge = age;

            int row = whichRow(contNames, continent);
            int col = whichCol(age);
            counters[row][col]++;

        } // END OF WHILE
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  END TASKS
        printReport(contNames, counters,tempMaxAge,tempMaxName,tempMinAge,tempMinName,usaAge);
        inFile.close();
    }

    //*************************************************************************
    private static int whichRow(String[] contNames, String continent) {
        for (int i = 0; i < NUM_CONTINENTS; i++) {
            if (contNames[i].compareTo(continent) == 0) {
                return i;
            }
        }
        return -1;
    }

    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    private static int whichCol(int age) {
        //<= 21  22-28  29-37  >= 38
        int col = -1;
        if (age <= 21) {
            col = 0;
        }
        else if (age >= 22 && age <= 28) {
            col = 1;
        }
        else if (age >= 29 && age <= 37) {
            col = 2;
        }
        else {
            col = 3;
        }
        return col;
    }
    //*************************************************************************
    
/*--------------------------------------------------------------- SAMPLE REPORT
MEDIAN AGE REPORT

              # COUNTRIES IN EACH CATEGORY
CONTINENT      <= 21  22-28  29-37  >= 38
---------      -----  -----  -----  -----
Africa            10      5      1      0      
Asia               9      4      2      4        
Europe            11     10      4      2
North America      0      4      3      2
Oceania            9      5      3      2
South America      1      3      5      3 

MEDIAN AGES FOR SPECIFIC COUNTRIES:
HIGHEST MEDIAN AGE:  47  Japan
LOWEST MEDIAN AGE:   15  Niger
MEDIAN AGE FOR HERE: 38  United States
------------------------------------------------------------------------------*/

    //*************************************************************************
    private static void printReport(String[] contNames, int[][] counters, int maxAge, String maxAgeName,
                                    int minAge, String minAgeName, int usaAge) {

        System.out.println("MEDIAN AGE REPORT\n");
        System.out.println("              # COUNTRIES IN EACH CATEGORY");
        System.out.println("CONTINENT      <= 21  22-28  29-37  >= 38");
        System.out.println("---------      -----  -----  -----  -----");
//
        // ToDo:  finish rest of report - the 2D table
        //          i.e., print contName (using %-14s)
        //                  loop to print counts (using %6d)


        System.out.println("\nMEDIAN AGES FOR SPECIFIC COUNTRIES:");
        System.out.printf("HIGHEST MEDIAN AGE:  %2d  %-30s", maxAge, maxAgeName);
        System.out.printf("\nLOWEST MEDIAN AGE:   %2d  %-30s", minAge, minAgeName);
        System.out.printf("\nMEDIAN AGE FOR HERE: %2d  %-30s", usaAge, "United States");
    }
}