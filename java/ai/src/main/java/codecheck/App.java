package codecheck;

public class App {
    
	public static void main(String[] args) {
		 // we assume AI can receive empty word group, so we have to check if we got last word
        if (args.length < 1) {
            System.out.println("word");
            System.exit(1);
        }
        String lastWord = args[0];  // get last played word
        for (int i=1; i<=args.length-1; i++){  // iterate through word group
            if (lastWord.charAt(lastWord.length()-1) == args[i].charAt(0)) {
                System.out.println(args[i]);  // if word starts with the last char of last word - print it and exit
                System.exit(0);
            }
        }
        System.out.println("fake");  // if we didn't find any valid word in the set - type some random word
        System.exit(0);
    }
}
