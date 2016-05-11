

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
public class dfsUnFocused {

	
	public static int final_depth = 5;
	public static int depth_counter = 1;
	static String seed_url = "https://en.wikipedia.org/wiki/Sustainable_energy";
	public static final int MAX_PAGES_TO_SEARCH = 1000;
	public static int PAGES_SEARCHED = 0;
	
	private static List<String> pagestoVisit = new LinkedList<String>();
	
	public static void beginCrawl(String url, int depth_counter) throws InterruptedException
	{
		if (!pagestoVisit.contains(url))
				{
			     	pagestoVisit.add(url);
				    PAGES_SEARCHED = 1;
				    System.out.println(PAGES_SEARCHED + ":" + url );
				}
		
		CrawlNextUrl(url, depth_counter);
		
	}
	
	public static void CrawlNextUrl(String url, int depth_counter) throws InterruptedException{
try {
	if ( (depth_counter <= 5) && (PAGES_SEARCHED < 1000))
	{
		Thread.sleep(1000);
		Document doc = Jsoup.connect(url).get();	
		Elements urls = doc.select("a[href]");
		
		for (Element u : urls)
		{
			String nexturl = u.attr("abs:href");
			nexturl = nexturl.split("#")[0];
			depthCrawler(nexturl, depth_counter);
		}
			
	}
	    	  } catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void depthCrawler(String nexturl, int depth_counter) throws InterruptedException
	{
		if (!pagestoVisit.contains(nexturl))
		{
	
			
			if (( (nexturl.lastIndexOf(":") < 6)) && 
					nexturl.matches("https://en.wikipedia.org/wiki/.*$") && 
					 (PAGES_SEARCHED < MAX_PAGES_TO_SEARCH))
			{
				pagestoVisit.add(nexturl);
				
				PAGES_SEARCHED++;
				System.out.println(PAGES_SEARCHED + ":" + nexturl );
				
				CrawlNextUrl(nexturl, depth_counter + 1);
			}
			
		}
		
		 
	}
	
	public static void printPages(){
		try{
			int counter = 0;
			 Iterator<String> itr = pagestoVisit.iterator();
	    	  FileWriter fw = new FileWriter("UnfocusedDFS1.txt");
	    	 
	    	  
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
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		beginCrawl(seed_url, 1);
		printPages();
		
	}

}
