import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        //створюємо список, який будуть використовувати два потоки
        List<Article> articles = new LinkedList<Article>();

        Thread parserThread = new Thread(new Parser(articles));
        Thread readerThread = new Thread(new Reader(articles));

        parserThread.start();
        readerThread.start();

    }
}

