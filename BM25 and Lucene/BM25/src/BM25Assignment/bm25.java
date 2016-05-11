package BM25Assignment;

import BM25Assignment.documents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class bm25 {

	public static double k1 = 1.2;
	public static double k2 = 100;
	public static double b = 0.75;
	public static LinkedHashMap<String, Double> docs = new LinkedHashMap<>();	
	public static LinkedHashMap<String, Integer> documentFrequency = new LinkedHashMap<>();
	public static String inputFile = "/Users/revanthrajualluri/Desktop/UnFocusedBFS1.txt";
	public static LinkedHashSet<documents> alldocuments = new LinkedHashSet<>();
	public static LinkedHashMap<String, String[]> allqueries = new LinkedHashMap<>();
	public static LinkedHashSet<String> documentList = new LinkedHashSet<>();
	public static LinkedHashMap<String, Integer> qTerms = new LinkedHashMap<>();
	public static double averagedl;
	
	public static void filenames(String fname){
		File inFile = new File(inputFile);
		Scanner s;
		try {
			s = new Scanner(inFile);
			s.useDelimiter("\n");
			while (s.hasNext()){
				String url = s.next().trim();
				
				//String x =url.substring(pos+1 , url.length()).toLowerCase();
				String x = url.substring(url.lastIndexOf("/") + 1);
				//x = x.replaceAll("\\p{Punct}+", "")
				x = x.replaceAll("[\\Q][_-\\E]", "");;
				documentList.add(x);				
			}
			s.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		filenames(inputFile);

		for(String docName : documentList){
			documents doc = new documents(docName);
			alldocuments.add(doc);
		}

		long totalLength = 0;
		for(documents d : alldocuments){
			totalLength += d.getLength();
		}
		averagedl = (double)totalLength/(double)alldocuments.size();

		Scanner queries;
		queries = new Scanner(new BufferedReader(new FileReader ("/Users/revanthrajualluri/Desktop/queries.txt")));
		
		while(queries.hasNextLine()){
			
			String line = queries.nextLine();
			String[] tokens = line.split(" ");
			String queryID = tokens[0];
			String[] queryTerms = new String[tokens.length -1];
			System.arraycopy(tokens, 1, queryTerms , 0 , tokens.length -1);
			allqueries.put(queryID, queryTerms);
		}
		//System.out.println("check1");
		queries.close();
		
		Scanner nGramRead;
		nGramRead = new Scanner(new BufferedReader(new FileReader("/Users/revanthrajualluri/Desktop/ngram1df.txt")));
		
		while(nGramRead.hasNext()){
		//	System.out.println("check2");
			String line = nGramRead.nextLine();
			Scanner sc3 = new Scanner(line);
			sc3.useDelimiter("\t");
			documentFrequency.put(sc3.next(), sc3.nextInt());
			sc3.close();
		}
		nGramRead.close();
        //System.out.println("check3");
		for(String q : allqueries.keySet()){
			String[] queryTerms = allqueries.get(q);
			docretrieval(q,queryTerms);
		}
		/* Set keys = docFrequency.keySet();
		 
		 for (Iterator i = keys.iterator(); i.hasNext();)
				   {
				       String key = (String) i.next();
				       int value =  docFrequency.get(key);
				       System.out.println(key + " = " + value);
				   }*/
	}
	
	public static void docretrieval(String queryID, String[] queryTerms){
		
		for(String query : queryTerms){
			if(qTerms.containsKey(query)){
				int count = qTerms.get(query) + 1;
				qTerms.put(query, count);
			}
			else {
				qTerms.put(query, 1);
			}
		}
		for(documents d : alldocuments){
			double bm25 = BM25Rank(d,qTerms);
			docs.put(d.getDocName(), bm25);
		}
		
		Comparator<Map.Entry<String, Double>> mv = new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Map.Entry<String, Double> one, Map.Entry<String, Double> two) {
				return one.getValue().compareTo(two.getValue());
			}
		};
		
		List<Map.Entry<String, Double>> temp = new ArrayList<Map.Entry<String, Double>>();
		temp.addAll(docs.entrySet());
		Collections.sort(temp,mv);
		
		try {
			FileWriter out = new FileWriter("bm25-query" + queryID + "-tableranks.txt") ;
			int rank = 1;
			for(int i = temp.size() - 1; i >= temp.size() - k2 ; i--){
				out.write(queryID + "--------" + "Q0 --------" + temp.get(i).getKey() + 
						"\t\t" + rank++ + "\t" 
						+ temp.get(i).getValue() + "\tBM25\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static double BM25Rank(documents d, LinkedHashMap<String, Integer> qTerms){
		double K = k1 * ((1-b) + (b * (d.getLength()/averagedl)));
		double ri = 0.00 , R = 0.00, bm25Rank = 0.00;
		for(String term : qTerms.keySet()){
			double qf = (double)qTerms.get(term);
			double fi = 0.00;
			if(d.getTermCount().containsKey(term)){
				fi = (double)d.getTermCount().get(term);
			}
			double ni = 0.00;
			if(documentFrequency.containsKey(term)){
				ni = (double)documentFrequency.get(term);
			}
			double N = (double)alldocuments.size();
			bm25Rank += singleBM25term(ni, N, qf, fi, K, ri, R);
		}		
		return bm25Rank;
	}
	
	
	public static double singleBM25term(double ni,double N,double qf,double fi,double K,double ri,double R){
		double result;
		double one;
		double two;
		double three;
		one = ((ri + 0.5)/(R - ri + 0.5))/((ni - ri + 0.5)/(N - ni - R + ri +0.5));
		two = ((k1 + 1) * fi)/(K + fi);
		three = ((k2+1) * qf)/(k2+qf);
		
		result = Math.log(one) * two * three;
		
		return result;
	}
	
}