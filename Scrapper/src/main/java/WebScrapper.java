import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.SimpleFormatter;

public class WebScrapper {
    public static Logger LOGGER = Logger.getLogger(WebScrapper.class.getName());
    public static FileHandler fileTxt;
    public static SimpleFormatter formatterTxt;

    public static void main(String[]args) throws InterruptedException, IOException {
        formatterTxt = new SimpleFormatter();
        fileTxt = new FileHandler("Logging.txt");
        fileTxt.setFormatter(formatterTxt);
        LOGGER.setLevel(Level.FINE);
        LOGGER.addHandler(fileTxt);

        for(;;){
            scrapeTheWeb();
            Thread.sleep(60000);
        }
    }

    private static void scrapeTheWeb(){
        Document doc= null;
        Element vid= null;
        String source= null;
        try{
             doc = Jsoup.connect("https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0")
                      .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                     .referrer("http://www.google.com").get();
             vid=doc.getElementById("fer_video");
             source=vid.select("source").first().attr("src");
        }
        catch(IOException e){

        }
        System.out.println(doc.title());
        System.out.println(source);
        LOGGER.fine(System.currentTimeMillis()+":"+source);

    }
}
