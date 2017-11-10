package pojext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class yez {
    public static String[] LABELS = {"FIRST", "SECOND"};  // labels for players for easier output
    
	public static void main(String[] args) {
        // assuming we have to get at least one word in word group, check if we have enough args
        if (args.length < 3) {
            System.exit(1);
        }
        String[] playersPath = new String[2];  // array of player's AI path
        playersPath[0] = args[0];  // path for player 1 AI
        playersPath[1] = args[1];  // path for player 2 AI
        String lastWord = args[2];  // last word used in the game
        String firstChar = "";  // declaration of string's first char
        // Map for a word group, key = first symbol of string, value = list of strings
        
        Map<String, List<String>> wordGroup = new HashMap<String, List<String>>(); 
        List<String> wordGroupCmd = new ArrayList<String>();  // list of words to pass it later to the player
        for (int i=3; i<args.length; i++) {
            wordGroupCmd.add(args[i]);
            firstChar = String.valueOf(args[i].charAt(0));  // get the first char
            if (wordGroup.get(firstChar) == null) {  // if list is not created yet
                wordGroup.put(firstChar, new ArrayList(Arrays.asList(args[i])));
            } else {
                wordGroup.get(firstChar).add(args[i]);
            }
        }
        
        int currentPlayer = 0;  // current player to answer, 0 for first, 1 for second
        boolean gameRunning = true;  // boolean to check if game should continue or not
        String judgment = "OK";  // string for valid/invalid output
        String lastCharLW = "";  // declaring a variable for the last char of the last word 
        while (gameRunning) {
        	// build a command prompt
        	wordGroupCmd.add(0, lastWord);
        	wordGroupCmd.add(0, playersPath[currentPlayer]);
            ProcessBuilder pb = new ProcessBuilder(wordGroupCmd);  // build a process
            Process proc;
			try {
				proc = pb.start();
			} catch (IOException e) {
                System.exit(1);
			}  
			// launch an AI program
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String answer;
			try {
				answer = in.readLine();
			} catch (IOException e) {
                System.exit(1);
			}
            answer = answer.replace("\n", "");  //remove any line breaks
            if (answer == "") {  // if answer is empty - invalid
                gameRunning = false;
            } else {
                lastCharLW = String.valueOf(lastWord.charAt(lastWord.length() - 1));  // last char of the last word
                firstChar = String.valueOf(answer.charAt(0));  // first char of the answer
                // check if either answer starts from the wrong letter or doesn't exists in the word set
                if (!((firstChar.equals(lastCharLW)) && (wordGroup.get(firstChar).contains(answer)))) {
                    gameRunning = false;  // mark invalid answer 
                } else {
                	wordGroup.get(firstChar).remove(answer);
                }
                if (!gameRunning) {
                    judgment = "NG";  // change judgment if answer is invalid
                }     
                lastWord = answer;  // set last used word to an answer given  
                
            }
            System.out.format("%s (%s): %s\n", LABELS[currentPlayer], judgment, answer);
            currentPlayer = 1 - currentPlayer;  // change current player        
        }
        System.out.format("WIN - %s", LABELS[currentPlayer]);
        System.exit(0);
	}
}


