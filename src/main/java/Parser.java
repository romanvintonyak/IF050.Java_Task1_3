import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by roman on 15.10.14.
 */
public class Parser implements Runnable {

    private List<Article> articles;

    public Parser(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public void run() {

        try {
            Document doc = Jsoup.connect("http://habrahabr.ru/").get();
            Elements links = doc.select("a.post_title");

            for (Element link : links) {
                synchronized (articles) {

                    //добавляємо новий елемент в список
                    System.out.println("parging...");
                    articles.add(new Article(link.text(), link.attr("href")));
                    try {
                        //імітуємо паузу і перемикаємося на Reader
                        Thread.sleep(500);
                        articles.notify();
                        articles.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}