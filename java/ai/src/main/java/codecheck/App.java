package codecheck;
import java.util.*;

public class App {
    public static String[] LABELS = {"FIRST", "SECOND"};  // labels for players for easier output
    
	public static int main(String[] args) {
        // assuming we have to get atleast one word in word group, check if we have enough args
        if (args.length < 3) {
            return 1;
        }
        return 0;
    }
}
