package src.main;  // specify package to be used in other *.java files

public class randomgen {
    public static int random(int maxRange) {    
        /* Random number generator */
        return (int) Math.round((Math.random() * maxRange));   // Random number between 1 to maxrange
    }
    
}
