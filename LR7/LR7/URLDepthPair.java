package LR7;

public class URLDepthPair {
    private String url;
    private int depth;
    
    
    public URLDepthPair(String url, int depth) {
        this.url = url;
        this.depth = depth;
      
    }
    
    public String getURL() {
        return url;
    }

    public int getDepth() {
        return depth;
    }
    
    
    public String toString() {
        return "<URL href=\"" + url +  "\" depth=\"" + depth + "\" \\>";
    }
}

