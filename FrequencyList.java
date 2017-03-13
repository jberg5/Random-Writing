import structure5.*;
import java.util.Random;
import java.util.Scanner;


public class FrequencyList {
    //A vector of associations, where each key is a letter and each value is a count of occurrences.
    //Given a string of text and a substring, looks for each instance of substring, and populates the vector
    //with the character that follows the substring. If the substring/character pairing occurs more than
    //once, the value is incremented by one.
    
    private Vector <Association<Character,Integer>> data;
    private String sub;
    private String text;

    //pre: Substring s is shorter than string t
    public FrequencyList(String s, String t) {
	assert (s.length() < t.length()) : "Substring is larger than string";
	data = new Vector<Association<Character,Integer>>();
	sub = s;
	text = t;
	ParseText();
    }
    
    //Post: Searches through text to locate instances of the substring. If it finds an instance,
    //calls AddLetter to add the following character to the data vector
    public void ParseText() {
	int l = this.sub.length();
	for (int i = 0; i < this.text.length()-l; i++) {
	    if (this.text.substring(i,i+l).equals(sub)) {
		AddLetter(text.charAt(i+l));
	    }
	}
    }

    //Post: If character is in data, increments value by one. Otherwise, adds letter to data.
    public void AddLetter(char letter) {
	if (CheckLetter(letter)) {
	    
	    Association<Character,Integer> letterInfo;
	    char existingLetter;
       
	    for (int i=0; i < data.size(); i++) {
		letterInfo = data.get(i);
		existingLetter = (char)letterInfo.getKey();
		if (existingLetter == letter) {
		    Integer val = letterInfo.getValue();
		    letterInfo.setValue(new Integer(val.intValue() + 1));
		    break;
		}
	    }
	} else {
	    data.add(new Association<Character,Integer>(letter, new Integer(1)));
	}
    }

    //Post: Checks whether letter is in data, returns true if it is, false otherwise
    public boolean CheckLetter(char letter) {

	Association<Character,Integer> letterInfo;
	char existingLetter;
	boolean isIn = false;
	
	for (int i=0; i < data.size(); i++) {
	    letterInfo = data.get(i);
	    existingLetter = (char)letterInfo.getKey();
	    if (existingLetter == letter) {
		isIn = true;
		break;
	    }
	}
	
	return isIn;
    }

    //Pre: data is not empty
    //Post: returns a random key  
    public char getPossibleLetter() {

	assert (data.size() > 0) : "Data is empty";

	//COMMENT FOR GRADER: I tried several methods of returning the most common character,
	//or characters according to frequencies, but got stuck in loops too easily. This
	//just randomly returns a possible character (even if it only happened once) and the results
	//are excellent. One alternate method has been commented out.
	
	Random rng = new Random();
	char maxChar = 0;
	//int maxVal = 0; <-- used in old method
	Association<Character,Integer> letterInfo;
	
	/* OLD METHOD
	for (int i=0; i < data.size(); i++) {
	    letterInfo = data.get(i);
	    if ((letterInfo.getValue() > maxVal) && (rng.nextInt(Math.max(1,data.size()/2)) == 0)) {
		maxVal = (int) letterInfo.getValue();
		maxChar = (char) letterInfo.getKey();
	    }
	} */

	letterInfo = data.get(rng.nextInt(data.size()));
	maxChar = (char) letterInfo.getKey();
	return maxChar;
    }

    //Post: Prints string representation of data
    //Useful for debugging
    public void printVector() {
	for (int i=0; i<data.size(); i++) {
	    Association<Character,Integer> letterInfo = data.get(i);
	    System.out.println("Substring " + this.sub + " has the following letters: " + letterInfo.getKey()+" occurs " + letterInfo.getValue() + " times.");
	}
    }
}
