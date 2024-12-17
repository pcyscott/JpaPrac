package com.mysite.jpa.global.initData;

import com.mysite.jpa.domain.post.post.entity.Post;
import com.mysite.jpa.domain.post.post.service.PostService;
import com.mysite.jpa.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;

    @Bean
    @Order(1)
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args-> {
            if(postService.count()>0) return;

            postService.write("title1", "content1");
            postService.write("title2", "content2");
            postService.write("title3", "content3");
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner baseInitDataApplicationRunner2() {
        return args-> {
            Ut.thread.sleep(1000);
            Post post1 = postService.findById(1L).get();
            postService.modify(post1,"title1-1","content1-1");

    };
    }
}