public class Article {

    private String title;

    private String href;

    public Article(String title, String href) {
        this.title = title;
        this.href = href;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
