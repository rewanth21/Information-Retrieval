package BM25Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class documents {


	String documentName;
	long length;
	LinkedHashMap<String,Integer> tCount = new LinkedHashMap<>();

	public String getDocName() {
		return documentName;
	}

	public long getLength() {
		return length;
	}

	public LinkedHashMap<String, Integer> getTermCount() {
		return tCount;
	}


	public documents(String documentName) {
		this.documentName = documentName;

		File f = new File("/Users/revanthrajualluri/Desktop/IRdocs/" + documentName + ".txt");
		boolean doesFileExist = f.exists();

		// To find the Length of the document 
		if(doesFileExist){
			this.length = f.length();
		}

		// To find the term frequency for all the terms in the document
		try {
			Scanner s = new Scanner(f);
			s.useDelimiter(" ");
			while(s.hasNext()){
				String str = s.next();
				if(tCount.containsKey(str)){
					int count = tCount.get(str) + 1;
					tCount.put(str,count);
				}
				else {
					tCount.put(str,  1);
				}
			}	
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
	}
}
