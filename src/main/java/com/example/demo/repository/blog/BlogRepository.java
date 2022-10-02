package com.example.demo.repository.blog;

import com.example.demo.model.blog.BlogPost;

import java.util.List;

public interface BlogRepository {
    public List<BlogPost> getAllBlogPost();
    public BlogPost getBlogPostByTitle(String title) throws Exception;
}
