import structure5.*;
import java.util.Random;
import java.util.Scanner;

public class Table {
    //Table is a vector of associations, where each key is a string of length k and each value is a corresponding FrequencyList object
    //Must call appendData() to begin populating Table
    
    Vector <Association<String,FrequencyList>> data;
    String text;
    int k;

    //Pre: k is less than the length of text t (handled in main)
    public Table(int k,String t) {
	data = new Vector<Association<String,FrequencyList>>();
	this.k = k;
	text = t;
    }

    //Pre: text exists, k is greater than zero
    //Makes sure addString() does not add a duplicate entry to the vector
    public void appendData(String s) {
	if (!checkString(s)) {
	    addString(s);
	}
    }

    //Pre: String does not already exist in vector
    //Post: New association of a substring and a frequency list added to data
    public void addString(String s) {
	if (text.contains(s)) {
	    data.add(new Association<String,FrequencyList>(s, new FrequencyList(s,text)));
	}
    }

    //checks if a string is already present in the Table
    public boolean checkString(String s) {
	Association<String,FrequencyList> stringInfo;
	String existingString;
	boolean isIn = false;

	for (int i = 0; i < data.size(); i++) {
	    stringInfo = data.get(i);
	    existingString = stringInfo.getKey();
	    if (existingString.equals(s)) {
		isIn = true;
		break;
	    }
	}

	return isIn;
    }

    //Used for debugging, displays table
    public void printVector() {
	for (int i = 0; i < data.size(); i++) {
	    Association<String,FrequencyList> stringInfo = data.get(i);
	    stringInfo.getValue().printVector();
	}
    }

    public int getSize() {
	return data.size();
    }

    public Association<String,FrequencyList> getEntry(int i) {
	return data.get(i);
    }
}
