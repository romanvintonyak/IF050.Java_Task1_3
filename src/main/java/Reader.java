import java.util.List;

/**
 * Created by roman on 15.10.14.
 */
public class Reader implements Runnable {
    private List<Article> articles;

    int countOfRecords = 10; //кількість записів на habrahabr

    public Reader(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000); //даємо можливість Parser'у відпрацювати першому
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int index = 0;

        while (index < countOfRecords) {
            synchronized (articles) {
                //читаємо елемент з списку і виводимо його
                System.out.println("reading...");
                System.out.println(articles.get(index));

                index++;

                try {
                    //імітуємо паузу і перемикаємося на Parser
                    Thread.sleep(500);
                    articles.notify();
                    if(index != countOfRecords){
                        articles.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}
