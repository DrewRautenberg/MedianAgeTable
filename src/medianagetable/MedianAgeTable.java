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

    public static void main(String[] args) throws IOException {
        // - - - - - - - - - - - - - - - - - - - - - - DECLARATIONS & OPEN FILE
        File f = new File("MedianAge.csv");
        Scanner inFile = new Scanner(f);
        String[] contNames = { "Africa", "Asia", "Europe",
                "North America", "Oceania", "South America" };
        String record;                                          // a line
        String[]field = new String[6];
        String name;
        String continent;
        int age;
        // ToDo:  need 2D matrix of counters (declare & initialize it to 0's)


        int tempMinAge = Integer.MAX_VALUE;
        String tempMinName = "";
        int tempMaxAge = Integer.MIN_VALUE;
        String tempMaxName = "";
        int usaAge;
        // - - - - - - - - - - - - - - - - - PROCESS FILE TO CALCULATE DO STATS

        inFile.nextLine();                           // skip over Header Record

        while (inFile.hasNext()) {
            record = inFile.nextLine();
            field = record.split(",");
            name = field[0];
            continent = field[1];
            age = Integer.parseInt(field[5]);
            // ToDo:  check for tempMinAge (& store age & name, if appropriate)


            // ToDo:  ditto for tempMaxAge


            if (name.compareTo("United States") == 0)
                usaAge = age;
            // ToDo: add 1 to appropriate counter, using whichRow and whichCol
            //          method calls to decide the right counter to ++



        } // END OF WHILE       
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  END TASKS
        printReport();                  // ToDo:  add parameters
        inFile.close();
    }
    //*************************************************************************
//    private static int whichRow() {

    // ToDo: add parameters, write method body, do return

//    }
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
//    private static int whichCol() {

    // ToDo: add parameters, write method body, do return

//    }
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
    private static void printReport() {         // ToDo:  add parameters

        System.out.println("MEDIAN AGE REPORT\n");
        System.out.println("              # COUNTRIES IN EACH CATEGORY");
        System.out.println("CONTINENT      <= 21  22-28  29-37  >= 38");
        System.out.println("---------      -----  -----  -----  -----");
//
        // ToDo:  finish rest of report - the 2D table
        //          i.e., print contName (using %-14s)
        //                  loop to print counts (using %6d)



        System.out.println("\nMEDIAN AGES FOR SPECIFIC COUNTRIES:");
//        System.out.println("HIGHEST MEDIAN AGE:  %2d  %-30s",
//                maxAge,maxAgeName);
//        System.out.println("LOWEST MEDIAN AGE:   %2d  %-30s",
//                minAge,minAgeName);
//        System.out.println("MEDIAN AGE FOR HERE: %2d  %-30s",
//                usaAge,"United States");
    }
}