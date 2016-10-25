import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
    	
//    	Scanner keyboard = new Scanner(System.in);
//    	System.out.println("Enter a URL to edit: ");
//    	
//    	String keyinput = keyboard.nextLine();
//    	
//    	System.out.println(keyinput);
//    	
//    
    	
    	StringBuffer html = new StringBuffer();
    	
    	URL url = null;
		try {
			url = new URL("https://www.expedia.co.uk/Bath-Hotels.d6084496.Travel-Guide-Hotels?regionId=6084496&semcid=UK.UB.GOOGLE.SEARCH.HOTEL&kword=%20bath_%20expedia!m.ZzZz.4780000282736.0.143192143840.%2Bbath%20%2Bexpedia.%20bath_%20expedia&gclid=Cj0KEQjwhbzABRDHw_i4q6fXoLIBEiQANZKGWyvhnlRmrtHai1tJxQa1tAqmjU25SVzTnowlzffKyPUaAn6s8P8HAQ");
			BufferedReader in = new BufferedReader(
			new InputStreamReader(url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				html.append(inputLine);
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		final Pattern titlePattern = Pattern.compile("<title>(.+?)</title>");
		final Pattern metaDescriptionPattern = Pattern.compile("<meta name=\"description\" content=\"(.*?)\" \\/>");
		final Pattern metaKeywordsPattern = Pattern.compile("<meta name=\"keywords\" content=\"(.*?)\" \\/>");

		
    	final Matcher titleMatcher = titlePattern.matcher(html);
    	final Matcher metaDescriptionMatcher = metaDescriptionPattern.matcher(html);
    	final Matcher metaKeywordsMatcher = metaKeywordsPattern.matcher(html);


    	
    	titleMatcher.find();
    	metaDescriptionMatcher.find();
    	metaKeywordsMatcher.find();
    	
    	String titleTagContent = titleMatcher.group(1);
    	String metaDescriptionTagContent = metaDescriptionMatcher.group(1);
    	String metaNameTagContent = metaKeywordsMatcher.group(1);
    	
    	System.out.println("Title: " + titleTagContent);
    	System.out.println("Meta Name: " + metaNameTagContent);
    	System.out.println("Meta Description: " + metaDescriptionTagContent);
    	
    	String[] titleHistory = new String[4];
    	titleHistory[0] = url.toString();
    	titleHistory[1] = "Title";
    	titleHistory[2] = titleTagContent;
    	titleHistory[3] = titleTagContent;

    	String[] descriptionHistory = new String[4];
    	descriptionHistory[0] = url.toString();
    	descriptionHistory[1] = "Description";
    	descriptionHistory[2] = metaDescriptionTagContent;
    	descriptionHistory[3] = metaDescriptionTagContent;
    	
    	String[] keywordHistory = new String[4];
    	keywordHistory[0] = url.toString();
    	keywordHistory[1] = "Keyword";
    	keywordHistory[2] = metaNameTagContent;
    	keywordHistory[3] = metaNameTagContent;
    	
		Scanner reader = new Scanner(System.in);  // Reading from System.in
    	while(true){
    		
    		System.out.println("Edit a web page (1)\nEditing report (2)\nExit application (3)");
    		int na = reader.nextInt();
    		String resp;
    		switch(na)
    		{
    		case 1:	
    			System.out.println("change title (1)\nchange description (2)\nchange keyword (3)");
        		int n = reader.nextInt();
        		String t;
        		switch(n)
        		{
        		case 1:	System.out.println("Enter new title");
        				 t = reader.next();
        				 titleHistory[2] = titleHistory[3];
        				 titleHistory[3] = t;
        			break;
        		case 2:	System.out.println("Enter new description");
        				t = reader.next();
        				descriptionHistory[2] = descriptionHistory[3];
        				descriptionHistory[3] = t; 
        			break;
        		case 3:	System.out.println("Enter new keyword");
        				t = reader.next();
        				keywordHistory[2] = keywordHistory[3];
        				keywordHistory[3] = t;
        			break;
        		default:System.out.println("whooooooooops");

        			break;
        			
        		}
    			break;
    		case 2:
    			for (int i = 0; i < titleHistory.length; i++){
    				System.out.println(titleHistory[i]);
    			}
    			
				for (int i = 0; i < descriptionHistory.length; i++){
    				System.out.println(descriptionHistory[i]);
				}
				
				for (int i = 0; i < keywordHistory.length; i++){
    				System.out.println(keywordHistory[i]);
				}
    			break;
    		case 3:	
    			System.exit(0);
    			break;

    			
    		}
   
    		
			

		}
    	

    	
    }
}
