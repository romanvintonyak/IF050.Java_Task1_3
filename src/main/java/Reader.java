import java.util.Queue;

public class Reader implements Runnable {

    private Queue<Article> articles;

    public Reader(Queue<Article> articles) {
        this.articles = articles;
    }

    @Override
    public void run() {
        while (!Parser.isDone) {
            synchronized (articles) {
                while (articles.size() == 0) {
                    try {
                        articles.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("reading...");
                System.out.println(articles.poll());
                articles.notify();
            }
        }
    }
}
