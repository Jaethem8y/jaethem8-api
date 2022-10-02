package com.example.demo.service.blog;

import com.example.demo.dto.BlogPostDTO;
import com.example.demo.model.blog.BlogPost;

import java.util.List;

public interface BlogService {
    public List<BlogPostDTO> getAllBlogPost();
    public BlogPostDTO getBlogPostByTitle(String title) throws Exception;
    public BlogPost mapBlogPost(BlogPostDTO blogPostDTO, BlogPost blogPost);
    public BlogPostDTO blogPostToDTO(BlogPost blogPost);

}