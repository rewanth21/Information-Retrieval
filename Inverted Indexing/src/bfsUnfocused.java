import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class bfsUnfocused {

	static String seed_url = "https://en.wikipedia.org/wiki/Sustainable_energy";
	public static final int MAX_PAGES_TO_SEARCH = 1000;
	public static int PAGES_SEARCHED = 0;
	public static int depth_counter = 2;
	 
	public static int Ng = 2;
	public static TreeMap<String, LinkedHashMap<String, Integer>>  words = new TreeMap<>();
	public static HashMap<String, Integer> totalFreq = new HashMap();
	public static Set<String> pagesVisited = new HashSet<String>();
	public static Queue<String> pagesToVisit = new LinkedList<String>();
	public static ArrayList<String>   output = new ArrayList<String>();
	
	public static void search(String url) throws IOException
	{
		output.clear();
        int counter = 1;
		pagesVisited.add(url);
		PAGES_SEARCHED = 1;
		output.add(url);
		
		enQueue(url);
		while(!pagesToVisit.isEmpty() && PAGES_SEARCHED < 1000)
		{
			String getPage = pagesToVisit.remove();
			output.add(getPage);
			PAGES_SEARCHED++;
			pagesVisited.add(getPage);
		
			enQueue(getPage);
		}
		for(String s : output)
		{
			System.out.println(counter + s);
			counter++;
		}

	}

	public static void enQueue(String url)
	{
		try{
			Document doc = Jsoup.connect(url).get();
			
			Elements urls = doc.select("a[href]");

			for (Element u : urls)
			{
                //Thread.sleep(1000);
				String nexturl = u.attr("abs:href");
				nexturl = nexturl.split("#")[0];
				if ((!pagesVisited.contains(nexturl)) &&
						( (nexturl.lastIndexOf(":") < 6)) && 
						(!nexturl.matches("https://en.wikipedia.org/wiki/Main_Page.*$"))
						&&
						nexturl.startsWith("https://en.wikipedia.org/wiki")  
						)
					pagesToVisit.add(nexturl);
				
			}
		}catch(IOException e){

			e.printStackTrace();
		}
	}

	public static void parseUrl(ArrayList<String> link) throws IOException{
		for(String s: link){
		//take each string which is a url from the 'output' list and use jsoup to parse each link
	    // and store the title and content in a separate file for each url.
		
		org.jsoup.nodes.Document doc = Jsoup.connect(s).timeout(20000).get();
		doc.select("sup").remove();
		
		File file = new File("/Users/revanthrajualluri/Desktop/IRdocs/" + returnFileName(s));
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		String filename = returnFileName(s);
		//parse for title,parah,head
		org.jsoup.select.Elements contents = doc.select("title,p,h");
		


		for(Element p: contents){
			
			String tmp = p.text().toLowerCase();
			 tmp = tmp.replaceAll("[\\Q][(){};!^\"?\\E]", "");
			// tmp = tmp.replaceAll("\\p{Punct}+", "");
			
			 tmp = tmp.replaceAll("/", " ");
			 tmp = tmp.replaceAll("(?<![0-9])[\\,\\.]", "");
			 tmp = tmp.replaceAll("[\\,\\.](?![0-9])", "");
			 bw.write(tmp + "\n");
			 nGramFunc(filename, tmp);
		}
		
		
		fw.close();
		

         
		fw.close();
		}
		
		File file2 = new File("/Users/revanthrajualluri/Desktop/ngram2df.txt");
		FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
		BufferedWriter bw2 = new BufferedWriter(fw2);
		for(String word : words.keySet()){
			
			System.out.println(word + ": " + words.get(word));
			 //bw2.write(word + ": " + words.get(word) + "\n");
		}
	
		for(String word : words.keySet()){
			int frequency =0;
			LinkedHashMap<String, Integer> list = new LinkedHashMap<>();
			list = words.get(word);
			for(String docid: list.keySet()){
				frequency = frequency + list.get(docid);
				totalFreq.put(word, frequency);
			}
			
			
			
		}
		
		Comparator<Map.Entry<String, Integer>> byMapValues = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> l, Map.Entry<String, Integer> r) {
			return l.getValue().compareTo(r.getValue());
			}
			};
			List<Map.Entry<String, Integer>> temp = new ArrayList<Map.Entry<String, Integer>>();
			temp.addAll(totalFreq.entrySet());
			Collections.sort(temp,byMapValues);
			
		for(int i = temp.size() - 1; i >= 0; i--){
			System.out.println(temp.get(i).getKey().trim() + "\t" + temp.get(i).getValue());
			// bw2.write(temp.get(i).getKey().trim() + "\t" + temp.get(i).getValue() + "\n");
		}
		
		
		for(String word2 : words.keySet()){
			LinkedHashMap<String, Integer> list = new LinkedHashMap<>();
			list = words.get(word2);
			Set<String> doclist = list.keySet();
			
			System.out.println(word2 + "\t" + doclist + "\t" + doclist.size());
			bw2.write(word2 + "\t" + doclist + "\t" + doclist.size() + "\n");
		}
		fw2.close();
		
	}
	
	public static void nGramFunc(String fn, String tmp){


		String[] temp = tmp.split(" ");
		for(int i = 0; i<temp.length-Ng+1; i++){
            String str = "";
            int start = i;
            int end = i+Ng;
            
            for(int k = start; k<end;k++){
            	str = str + " " + temp[k];
            }

			
				
				if(words.containsKey(str)){
					LinkedHashMap<String, Integer> fileWithTerms = new LinkedHashMap<>();
					fileWithTerms = words.get(str);
					if(fileWithTerms.containsKey(fn)){
						fileWithTerms.put(fn, fileWithTerms.get(fn) + 1);
						
					}
					else{
						fileWithTerms.put(fn,  1);
						
					}
					words.put(str, fileWithTerms);
				}
				else{
					LinkedHashMap<String, Integer> fileWithTerms = new LinkedHashMap<>();
					fileWithTerms.put(fn, 1);
					words.put(str, fileWithTerms);
				}
			
		}
	}

	
	public static String returnFileName(String url){
		int pos = url.lastIndexOf("/");
		String x =url.substring(pos+1 , url.length()).toLowerCase();
		//x = x.replaceAll("\\p{Punct}+", "");
		x = x.replaceAll("[\\Q][_-\\E]", "");
		
		return x+".txt"; 
		
			
		}
	
	

	

	public static void printPages(){
		try{
			int counter = 0;
			 Iterator<String> itr = output.iterator();
	    	  FileWriter fw = new FileWriter("UnFocusedBFS1.txt");
	    	 
	    	  
	    	  while(itr.hasNext()){
	    		  
	    		  fw.write(itr.next() + "\n");
	    		  counter++;
	    	  }
	    	  System.out.println("number of urls : " + counter);
	    	  fw.close();
		}
		catch (IOException e){
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		int counter = 1;
		Scanner s = new Scanner(System.in);
    	
    	System.out.print("Enter the seed document link:");
    	seed_url = s.nextLine(); 
		
		//System.out.print("Enter search phrase:");
    	//keyWord = s.nextLine().toLowerCase();    	
    	//keyWord = keyWord + " " ; 
    	
		search(seed_url);
		parseUrl(output);
		//printPages();
		
	}
	
}
