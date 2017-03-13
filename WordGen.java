/*
3.2) add(v) appends a value to the end, add(i,v) inserts a value at index i and shifts every subsequent element by one
3.3) set does not shift any values, it merely changes the value at a given index
3.4) remove(v) finds the first occurrence of v and removes it, remove(i) removes the element at position i, no matter what it is

3.6) you can customize the interface to have specialized operations. Examples might include flipBit or componentwise addition.

 */

import structure5.*;
import java.util.Random;
import java.util.Scanner;

public class WordGen {

    //Reads in a file, analyzes the occurence of characters in the text, and generates "faketext" that resembles the original

    private static int level;
    private static Table dict;
    private static String fakeText;
    private static final int textLength = 500;

    //Pre: User provides a command line input describing the level of the analysis, i.e. 5
    //Post: updates level and creates a Table object from the level and text
    //also populates and prints fakeText
    public static void main(String args[]) {
	
	Scanner in = new Scanner(System.in);
	StringBuffer textBuffer = new StringBuffer();
	while (in.hasNextLine()) {
	    String line = in.nextLine();
	    textBuffer.append(line);
	    textBuffer.append("\n");
	}

        String text = textBuffer.toString();

	if (args.length == 0) {
	    System.out.println("Usage: java WordGen <level>");
	} else {
	    level = Integer.parseInt(args[0]);
	    dict = new Table(level,text);
	    fakeText = genSeed(text,level);

	    assert (level < text.length()) : "Level of analysis must be less than the length of text.";

	    for (int i = 0; i<textLength; i++) {
		String currentString = fakeText.substring(Math.max(fakeText.length() - level, 0));
		dict.appendData(currentString);
		if (checkString(currentString)) {
		    fakeText = fakeText + getLetter(currentString);
		} else { fakeText = fakeText + randomLetter(); }
	    }

	    System.out.println(fakeText);

	}
    }

    //Searches table for input string, if it finds it, calls the getPossibleLetter method on the string's frequency list
    //to return a possible letter that could follow the string
    public static char getLetter(String s) {
	Association<String,FrequencyList> stringInfo;
	String existingString;
	char letter = '\0';
	
	for (int i=0; i<dict.getSize(); i++) {
	    stringInfo = dict.getEntry(i);
	    existingString = stringInfo.getKey();
	    if (existingString.equals(s)) {
	        FrequencyList freq = stringInfo.getValue();
		letter = freq.getPossibleLetter();
      	    }
	}

	return letter;
    }

    //Post: returns a pseudo-random popular letter from the alphabet
    public static char randomLetter() {
	String alphabet = "rstlneaio";
	Random rng = new Random();

	return alphabet.charAt(rng.nextInt(alphabet.length()));
    }

    //Post: returns whether string is in the table
    public static boolean checkString(String s) {
	Association<String,FrequencyList> stringInfo;
	String existingString;
	boolean isIn = false;
	
	for (int i=0; i < dict.getSize(); i++) {
	    stringInfo = dict.getEntry(i);
	    existingString = stringInfo.getKey();
	    if (existingString.equals(s)) {
		isIn = true;
		break;
	    }
	}

	return isIn;
    }

    //Post: returns the first k (analysis level) characters of the text
    public static String genSeed(String text, int k) {
	return text.substring(0,k);
    }

    
}
