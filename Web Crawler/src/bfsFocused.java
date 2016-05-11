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


public class bfsFocused {

	static String seed_url = "https://en.wikipedia.org/wiki/Sustainable_energy";
	public static final int MAX_PAGES_TO_SEARCH = 1000;
	public static int PAGES_SEARCHED = 0;
	public static int depth_counter = 1;
	public static String keyWord = "solar"; 

	public static Set<String> pagesVisited = new HashSet<String>();
	public static Queue<String> pagesToVisit = new LinkedList<String>();
	public static ArrayList<String>   output = new ArrayList<String>();
	public static void search(String url, String word)
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
			if (findWord(getPage, word))
			{

				pagesVisited.add(getPage);
				output.add(getPage);
				PAGES_SEARCHED++;

			}
			else
			{
				pagesVisited.add(getPage);
			}
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
						nexturl.startsWith("https://en.wikipedia.org/wiki")  
						)
					pagesToVisit.add(nexturl);
			}
		}catch(IOException e){

			e.printStackTrace();
		}
	}

	/*public static void breadthCrawlNext(String url, String word){
    	try {

    		while (!pagesToVisit.isEmpty() && (PAGES_SEARCHED < 1000))
    		{
    			Document doc = Jsoup.connect(url).get();	
    			Elements urls = doc.select("a[href]");

    			for (Element u : urls)
    			{
    				String nexturl = u.attr("abs:href");
    				nexturl = nexturl.split("#")[0];
    				if ((!pagesVisited.contains(nexturl)) &&
    						( (nexturl.lastIndexOf(":") < 6)) && 
    						nexturl.matches("https://en.wikipedia.org/wiki/.*$")  
    						 )
    				{
    				pagesToVisit.add(nexturl);

    				String  findWordFromHere = pagesToVisit.remove();

    				if (findWord(findWordFromHere, word))
    						{

    					      pagesVisited.add(findWordFromHere);

    					      PAGES_SEARCHED++;
    						}
    				else
    				{
    					  pagesVisited.add(nexturl);


    				}


    				}
    			}
    		}
    		    	  } catch (IOException e) {

    				e.printStackTrace();
    			}
    		}*/

	public static boolean findWord(String url, String word){
		boolean trigger = false;
		try{
			//int counter = 0;
			Document doc = Jsoup.connect(url).get();	

			if (doc.title().toLowerCase().contains(word) ||
					url.toLowerCase().contains(word)
					|| doc.body().text().toLowerCase().contains(word)) 
			{
				trigger = true;
				//System.out.println(counter++ + ":" + url);
			}
			else 
				trigger = false;
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return trigger;

	}

	public static void printPages(){
		try{
			int counter = 0;
			 Iterator<String> itr = output.iterator();
	    	  FileWriter fw = new FileWriter("FocusedBFS1.txt");
	    	 
	    	  
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
	
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
    	
    	System.out.print("Enter the seed document link:");
    	seed_url = s.nextLine(); 
		
		System.out.print("Enter search phrase:");
    	keyWord = s.nextLine().toLowerCase();    	
    	keyWord = keyWord + " " ; 
    	
		search(seed_url, keyWord);
		
		printPages();
	}
	
}
