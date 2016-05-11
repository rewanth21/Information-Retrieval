import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class UnfocusedCrawl {



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		getAllLinks();
		
	}

	
	
	public static void getAllLinks() throws IOException {


		ArrayList<String> newlist = new ArrayList<String>();


		BufferedReader br = null;
		BufferedReader newbr = null;
		LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();

		String str;





		ArrayList<String> initialarray = new ArrayList<String>();

		try {
			Scanner s = new Scanner(new File("UnfocusedDFS1.txt"));
			while(s.hasNext()){
				newlist.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(String strn : newlist){
			String s1 = strn.substring(30);
			map.put(s1, initialarray);
		}
		
		for (String s1 : newlist){
			String l = s1.substring(30);
			try{
				Document doc = Jsoup.connect(s1).get();
				Elements urls = doc.select("a[href]");
				for (Element e : urls){
					String nextURL = e.attr("abs:href");
					nextURL =nextURL.split("#")[0];
					if (( (nextURL.lastIndexOf(":") < 6)) && 
							nextURL.matches("https://en.wikipedia.org/wiki/.*$")){
						for (String s2 : newlist){
							if (nextURL.equals(s2)){
								ArrayList<String> a1 = new ArrayList<String>();
								String s3 = s2.substring(30);
								a1 = map.get(s3);
								if(!a1.contains(l)){
									a1.add(l);
									map.put(s3, a1); 
								}
							}
						}
					}
				}
				
			}
			catch (IOException e) {
				
			}
		}
        
		try {
			 FileWriter writeToFile;
			 writeToFile = new FileWriter("directedgraph.txt");
			Set<String> s = map.keySet();
			for (String key : s){				
				writeToFile.write(key);
				for (String value : map.get(key)){
					writeToFile.write("  " + value);
				}
				writeToFile.write("\n");
			}
			writeToFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		


		/*for(String link : newlist){
			Document doc = Jsoup.connect(link).get();
			Elements urls = doc.select("a[href]");

			for (Element u : urls)
			{
				String nexturl = u.attr("abs:href");
				nexturl = nexturl.split("#")[0];
				if (( (nexturl.lastIndexOf(":") < 6)) && nexturl.startsWith("https://en.wikipedia.org/wiki/"))
				{
					for (String lst : newlist){
						if (nexturl.equals(lst)){
							ArrayList<String> a = new ArrayList<String>();

							lst = lst.substring(lst.lastIndexOf("/"));
							link = link.substring(link.lastIndexOf("/"));
							a = map.get(lst);
							if (!a.contains(link)){
								a.add(link);
								map.put(lst,a);
							}

						}
					}

				}
			} 
		} */

		
		/*for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()){
			System.out.print(entry.getKey() + " " );
			for(String counter : entry.getValue()){
				System.out.print(counter + " ");
			}System.out.println();
		}*/


	}
}




