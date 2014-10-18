import java.util.*;

public class Main {


    public static void main(String[] args) throws InterruptedException {


        Queue<Article> articles = new LinkedList<Article>();

        Thread parserThread = new Thread(new Parser(articles));
        Thread readerThread = new Thread(new Reader(articles));

        parserThread.start();
        readerThread.start();

    }
}

