import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import java.util.Map;


public class pageRank {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		pageRankMethod();
	}


	public static void pageRankMethod() throws IOException{
		// P is the set of all pages; |P| = N
		HashSet<String> P = new HashSet<String>();
		// S is the set of sink nodes, i.e., pages that have no out links
		HashSet<String> S = new HashSet<String>();
		// M(p) is the set (without duplicates) of pages that link to page p
		HashMap<String, Set<String>>Mp= new HashMap<String,Set<String>>();
		// L(q) is the number of out-links (without duplicates) from page q
		HashMap<String, Integer>Lq= new HashMap<String,Integer>();
		// d is the PageRank damping factor; use d = 0.85 as a fairly typical value
		double d=0.85;



		LinkedHashMap<String, ArrayList<String>> mapper = new LinkedHashMap<String, ArrayList<String>>();
		ArrayList<String> inlinks = new ArrayList<String>();
		///////////get all links in an arraylist


		Scanner s = new Scanner(new File("/Users/revanthrajualluri/Desktop/inLinks_WG1.txt"));
		s.useDelimiter("\n");
		int noOfInlinks = 0;
		while(s.hasNext()){

			String line = s.next();
			Scanner s1 = new Scanner(line);
			s1.useDelimiter(" ");
			ArrayList<String> allLinks = new ArrayList<String>();
			while(s1.hasNext())	{
				String s2 = s1.next();
				allLinks.add(s2);
				noOfInlinks++;

			}

			String key = allLinks.remove(0);
			mapper.put(key, allLinks);
			s1.close();

		}

		//System.out.println(mapper);
		s.close();


		//////P////////
		for(String key : mapper.keySet()){
			P.add(key);
		}
		//System.out.println(P);
		///////p//////

		//////// Lq
		for(String key : mapper.keySet())
		{
			Lq.put(key, 0);
		}
		for(ArrayList<String> values : mapper.values()){
			for(String value: values){
				Lq.put(value, (Lq.get(value) + 1));
			}
		}
		//System.out.println(Lq);
		///////Lq

		//get link nodes
		for (String key : Lq.keySet())
		{
			if(Lq.get(key) == 0)
			{
				S.add(key);
			}
		}
		//System.out.println(S);
		///////get link nodes

		LinkedHashMap<String, Double> PR = new LinkedHashMap<String, Double>();
		LinkedHashMap<String, Double> NewPR = new LinkedHashMap<String, Double>();

		double n = 0.0; 
		double D = 0.95;
		double sinkPR = 0;
		double entropy = 0;
		double perplexity = 0;
		boolean Convergence = false;
		double prevperplexity = 0;
		int counter = 1;
		/////algorithm build/////////////////
		for(String key : mapper.keySet()){
			n = 1/(double)mapper.size();
			PR.put(key, (double)n); 
			NewPR.put(key, 0.00);
		}
		//System.out.println(mapper);
		//System.out.println(PR);
        int perpcounter = 1;
		while (!Convergence){
			entropy = 0;
			prevperplexity = perplexity;
			sinkPR  = 0;
			for(String key : S){
				sinkPR = sinkPR + PR.get(key);
			}
			for(String key : mapper.keySet()){
				double t=0;
				t= (1-d)/(double)mapper.size();
				NewPR.put(key, t);
				t = t+ d*sinkPR/(double)mapper.size();
				NewPR.put(key, t);
				//	System.out.println(mapper);

				for(String key1 : mapper.get(key)){
					double	a=PR.get(key1);
					//System.out.println(key1 + ":" + a);
					double  c=Lq.get(key1);
					//System.out.println(key1 + ":" + c);
					//System.out.println(key1);
					t = t + (double)(d*a/c);
					NewPR.put(key,t);
				}
			}
			for(String newkey : mapper.keySet()){
				PR.put(newkey, NewPR.get(newkey));
			}
			//System.out.println(PR);



			for(String page: PR.keySet()){
				double currentPR = PR.get(page);
				entropy += currentPR * (Math.log(currentPR)/Math.log(2));

			}
			entropy = 0 - entropy;
			perplexity = Math.pow(2,entropy);

			if ((Math.abs(prevperplexity - perplexity) < 1)){
				counter++;
			}
			else {
				counter = 0;

			}
			if(counter == 4)
			{
				Convergence = true;
			}


			System.out.println("perplexity counter: " + perpcounter++ + "---"+ perplexity);
		}

		//sort to 50 page ranks
		Set<Entry<String, Double>> set = PR.entrySet();
		ArrayList<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(set);
		int top50 = 0;
		Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
		{
			public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
			{
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );
		for(Map.Entry<String, Double> entry:list){
			System.out.println(entry.getKey()+" ==== "+entry.getValue() + ": " + top50++);
		}
        /////////////////////////////////////
		//inlinks ranks sort
		Set<Entry<String, Integer>> set2 = Lq.entrySet();
		ArrayList<Entry<String, Integer>> list2 = new ArrayList<Entry<String, Integer>>(set2);
		int top50in = 0;
		Collections.sort( list2, new Comparator<Map.Entry<String, Integer>>()
		{
			public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
			{
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );
		for(Map.Entry<String, Integer> entry:list2){
			System.out.println(entry.getKey()+" ==== "+entry.getValue() + ": " + top50in++);
		}
		
		
		
		////////sort to top 50 inlinks

		//print to sysout
		/*for (Entry<String, Double> entry : PR.entrySet()) {
		    String key = entry.getKey().toString();
		    Double value = entry.getValue();
		    System.out.println(key + ":" + value);
		}*/
		// print to sysout

		//print to file
		int counter1 = 0;
		int top50s = 1;
		try {
			FileWriter writeToFile;
			writeToFile = new FileWriter("/Users/revanthrajualluri/Desktop/WG1-0.95.txt");

			for(Map.Entry<String, Double> entry:list){

				String key = entry.getKey().toString();
				Double value = entry.getValue();

				writeToFile.write(key + ":" + value + "--- rank is: " 
						+ top50s++ + "\n");

			}
			for (Entry<String, Integer> entry : Lq.entrySet()){
				Integer value = entry.getValue();
				if(value == 0){
					counter1++;
				}
			}
			writeToFile.write("number of sink nodes : " + counter1 + "\n");

			writeToFile.write("Total inlinks : " + noOfInlinks);
			writeToFile.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//print to file



	}

}
