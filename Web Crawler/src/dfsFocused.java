



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

public class dfsFocused {

	
	public static int final_depth = 5;
	public static int depth_counter = 1;
	static String seed_url = "https://en.wikipedia.org/wiki/Sustainable_energy";
	public static final int MAX_PAGES_TO_SEARCH = 1000;
	public static int PAGES_SEARCHED = 0;
	static String word = "solar";
	
	private static List<String> pagestoVisit = new LinkedList<String>();
	private static List<String> pagesWithWord = new LinkedList<String>();
	
	public static void beginCrawl(String url, String word) throws InterruptedException
	{
		if (!pagestoVisit.contains(url))
				{
					
			     	pagestoVisit.add(url);
			     	
				    PAGES_SEARCHED = 1;
				 //   System.out.println(PAGES_SEARCHED + ":" + url );
				}
		
		CrawlNextUrl(url, depth_counter, word);
		
	}
	
	public static void CrawlNextUrl(String url, int depth_counter, String word) throws InterruptedException{
try {
	if ( (depth_counter <= 5) && (PAGES_SEARCHED <= 1000))
	{
		//Thread.sleep(1000);
		Document doc = Jsoup.connect(url).get();	
		Elements urls = doc.select("a[href]");
		
		for (Element u : urls)
		{
			String nexturl = u.attr("abs:href");
			nexturl = nexturl.split("#")[0];
			
			
			depthCrawler(nexturl, depth_counter, word);
			
			
		}
			
	}
	    	  } catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void depthCrawler(String nexturl, int depth_counter, String word) throws InterruptedException
	{
		if (!pagestoVisit.contains(nexturl))
		{
	
			
			if (( (nexturl.lastIndexOf(":") < 6)) && 
					nexturl.matches("https://en.wikipedia.org/wiki/.*$") && 
					 (PAGES_SEARCHED < MAX_PAGES_TO_SEARCH) && containsword(nexturl, word))
			{
				pagestoVisit.add(nexturl);
				
				
				//System.out.println(PAGES_SEARCHED + ":" + nexturl );
				System.out.println(PAGES_SEARCHED++ + ":" + "Solar Present in - " + nexturl);
				CrawlNextUrl(nexturl, depth_counter + 1, word);
			}
			//else
			//	System.out.println(pagestoVisit.size() + ":" + "solar not found and disformed - " + nexturl);
			
		}
		
		 
	}
	
	public static void printPages(){
		int counter = 0;
		try{
			 Iterator<String> itr = pagestoVisit.iterator();
	    	  FileWriter fw = new FileWriter("FocusedDFS1.txt");
	    	 
	    	  
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
	
	//public static void getword(List<String> pagestoVisit, String word)
	//{
		
	//	for(String s : pagestoVisit)
		//{
			//pagesWithWord.add(s);
			//  System.out.println(s);
			 // containsword(s, word);
		//}
		
	
		
	//}
	
	public static boolean containsword(String url, String word)
	{
		boolean trigger = false;
		int counter = 0;
    	try{
    	  
    		Document doc = Jsoup.connect(url).get();	
			
			if (doc.title().toLowerCase().contains(word) ||
					url.toLowerCase().contains(word)
            		|| doc.body().text().toLowerCase().contains(word)) 
			{
				trigger = true;
				 
				//System.out.println(counter++ + ":" + url);
				pagesWithWord.add(url);
			}
			else 
				trigger = false;
    	}catch(IOException e)
    	{
    		e.printStackTrace();
    	}
		return trigger;
    	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner s = new Scanner(System.in);
	    	
	    	System.out.print("Enter the seed document link:");
	    	seed_url = s.nextLine(); 
			
			System.out.print("Enter search phrase:");
	    	word = s.nextLine().toLowerCase();    	
	    	word = word + " " ; 
	    	beginCrawl(seed_url, "solar");
		} catch (InterruptedException e) {
			System.out.println("SLEEP EXCEPTION");
			e.printStackTrace();
		}
		
		printPages();
		
	}

}




