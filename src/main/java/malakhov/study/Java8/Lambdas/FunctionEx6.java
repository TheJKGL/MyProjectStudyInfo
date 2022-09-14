package malakhov.study.Java8.Lambdas;

import java.util.function.Function;

public class FunctionEx6 {

    public static void main(String[] args) {

        Function<String, Integer> func = content -> content.length();

        Function<Article, String> before = article -> article.getContent();

        Article article = new Article("Java Tutorial", "Java Tutorial Content...");
        Object object = new Object();

        //Значала будет достан контент с помощью функции before, а затем будет
        //найдена длина контента с помощью функции func.
        int contentLength = func.compose(before).apply(article);

        System.out.println("The length of the article content: " + contentLength);
    }
}

class Article {
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
}
