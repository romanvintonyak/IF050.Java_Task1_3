import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Queue;

public class Parser implements Runnable {

    private Queue<Article> articles;

    public Parser(Queue<Article> articles) {
        this.articles = articles;
    }

    public static volatile boolean isDone;

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect("http://habrahabr.ru/").get();
            Elements links = doc.select("a.post_title");

            for (Element link : links) {
                synchronized (articles) {
                    while (articles.size() >= 1) {
                        try {
                            articles.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("parsing...");
                    articles.add(new Article(link.text(), link.attr("href")));
                    try {
                        Thread.sleep(1000); //low-speed internet imitation
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    articles.notify();
                }
            }
            done();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void done() {
        this.isDone = true;
    }
}