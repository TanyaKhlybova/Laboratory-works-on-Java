package LR7;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

class Crawler {

    private HashMap<String, URLDepthPair> links = new HashMap<>();
    static LinkedList<URLDepthPair> pool = new LinkedList<>();
    
    private int depth = 0;
    
    public Crawler(String url, int depth) {
        this.depth = depth;
        pool.add(new URLDepthPair(url, 0));
    }
    
    public void getSites() {
        while (pool.size() > 0)
            parseLink(pool.pop());
        
        for (URLDepthPair link : links.values())
            System.out.println(link);
            
        System.out.println();
        System.out.printf("Found %d URLS\n", links.size());
    }
    
    public static Pattern LINK_REGEX = Pattern.compile(
        "<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1"
    );
    
    private void parseLink(URLDepthPair link) {
               
        links.put(link.getURL(), link);
        
        if (link.getDepth() >= depth){
       
            return;}
        
        try {
        
            URL url = new URL(link.getURL());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            Scanner s = new Scanner(con.getInputStream());
            while (s.findWithinHorizon(LINK_REGEX, 0) != null) {
               
                String newURL = s.match().group(2);
                if (newURL.startsWith("/"))
                    newURL = link.getURL() + newURL;
                else if (!newURL.startsWith("http"))
                    continue;
                URLDepthPair newLink = new URLDepthPair(newURL, link.getDepth() + 1);
                pool.add(newLink);
            }
        } catch (Exception e) {}
    }

   

    public static void main(String[] args) {
    
        Scanner input = new Scanner(System.in);
        System.out.println("Enter URL: ");
        
        String url = input.nextLine();
        System.out.println("Enter depth: ");
        int depth = input.nextInt();
        input.close();
        
        Crawler crawler = new Crawler(url, depth);
        crawler.getSites();
    }
}