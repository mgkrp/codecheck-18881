package codecheck;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class App {
    public static String[] LABELS = {"FIRST", "SECOND"};  // labels for players for easier output
    
	public static void main(String[] args) {
		// assuming we have to get at least one word in word group, check if we have enough args
        if (args.length < 4) {
        	System.err.println("Not enough arguments");
            System.exit(1);
        }
        String[] playersPath = new String[2];  // array of players' AI path
        playersPath[0] = args[0];  // path for player 1 AI
        playersPath[1] = args[1];  // path for player 2 AI
        File player1 = new File(playersPath[0]);
        File player2 = new File(playersPath[1]);
        if (!(player1.isFile() && player2.isFile())){
        	System.err.println("Can't find a file in the given path");
        	System.exit(2);
        }
        String lastWord = args[2].toLowerCase();  // last word used in the game
        String firstChar = "";  // declaration of string's first char
        // Map for a word group, key = first symbol of string, value = list of strings
        
        Map<String, List<String>> wordGroup = new HashMap<String, List<String>>(); 
        List<String> wordGroupCmd = new ArrayList<String>();  // list of words to pass it later to the player
        for (int i=3; i<args.length; i++) {
            wordGroupCmd.add(args[i].toLowerCase());
            firstChar = String.valueOf(args[i].charAt(0)).toLowerCase();  // get the first char
            if (wordGroup.get(firstChar) == null) {  // if list is not created yet
                wordGroup.put(firstChar, new ArrayList<String>(Arrays.asList(args[i].toLowerCase())));
            } else {
                wordGroup.get(firstChar).add(args[i].toLowerCase());
            }
        }
        int currentPlayer = 0;  // current player to answer, 0 for first, 1 for second
        boolean gameRunning = true;  // boolean to check if game should continue or not
        String judgment = "OK";  // string for valid/invalid output
        String lastCharLW = "";  // declaring a variable for the last char of the last word 
        while (gameRunning) {
        	// build a command prompt
        	List<String> params = new ArrayList<String>(wordGroupCmd);  // create params for processbuilder
        	params.add(0, lastWord);
        	params.add(0, playersPath[currentPlayer]);
            params.add(0, "C:/Program Files/Git/usr/bin/bash.exe");  // windows problems
            ProcessBuilder pb = new ProcessBuilder(params);  // build a process
			Process proc = null;
            try {
				proc = pb.start();
			} catch (IOException e) {
				System.err.println("IO exception during AI program launch");
                System.exit(3);
			}  
			// launch an AI program
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String answer = null;
			try {
				answer = in.readLine();  
                in.close();
			} catch (IOException e) {
				System.err.println("IO exception during answer reading");
                System.exit(4);
			} 
			if (answer == null) {
				answer = "";  // if we didn't get any output from AI, set it to an empty answer
			}
            answer = answer.replace("\n", "");  //remove any line breaks and line ends
            answer = answer.replace("\r", "");
            if (answer == "") {  // if answer is empty - invalid
                gameRunning = false;
            } else {
                lastCharLW = String.valueOf(lastWord.charAt(lastWord.length() - 1));  // last char of the last word
                firstChar = String.valueOf(answer.charAt(0));  // first char of the answer
                // check if either answer starts from the wrong letter or doesn't exists in the word set
                if ((firstChar.equals(lastCharLW))){
                    if (wordGroup.get(firstChar) != null){  // to prevent null pointer exception
                    	if (wordGroup.get(firstChar).contains(answer)){
                    		wordGroup.get(firstChar).remove(answer);  // remove answer from the word group
                    		wordGroupCmd.remove(answer);
                    	} else {
                    		gameRunning = false; 
                    	}
                    } else {
                    	gameRunning = false; 
                    }                          
                } else {
                	gameRunning = false; 
                }                
            }
            if (!gameRunning) {
                judgment = "NG";  // change judgment if answer is invalid
            }     
            lastWord = answer;  // set last used word to an answer given 
            System.out.format("%s (%s): %s\n", LABELS[currentPlayer], judgment, answer);  // print out current turn
            currentPlayer = 1 - currentPlayer;  // change current player        
        }
        System.out.format("WIN - %s", LABELS[currentPlayer]);  // declare a winner
        System.exit(0);
	}
}




