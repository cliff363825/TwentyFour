package com.onevgo.springboot.elastic;

import com.onevgo.springboot.elastic.bean.Article;
import com.onevgo.springboot.elastic.bean.Book;
import com.onevgo.springboot.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticApplicationTests {
    @Autowired
    private JestClient jestClient;
    @Autowired
    private BookRepository bookRepository;
    @Test
    public void contextLoads() {
    }

    @Test
    public void saveIndex() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("zhangsan");
        article.setContent("hello world");

        // 构建一个索引功能
        Index index = new Index.Builder(article).index("onevgo").type("news").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void search() {
        String query = "{\"query\":{\"match\":{\"content\":\"hello\"}}}";

        // 构建搜索功能
        Search search = new Search.Builder(query).addIndex("onevgo").addType("news").build();
        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        Book book = new Book();
        book.setId(1);
        book.setBookName("西游记");
        book.setAuthor("吴承恩");
        bookRepository.index(book);
    }
}
