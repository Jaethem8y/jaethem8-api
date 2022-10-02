package com.example.demo.controller;

import com.example.demo.dto.BlogPostDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.service.blog.BlogService;
import com.example.demo.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RequiredArgsConstructor
@RestController
@EnableWebMvc
public class CrudController {
    private final BlogService blogService;
    private final StudyService studyService;

    @GetMapping("/blogPost")
    public List<BlogPostDTO> getBlogPosts() {
        return blogService.getAllBlogPost();
    }

    @GetMapping("/blogPost/{title}")
    public BlogPostDTO getBlogPostByTitle(@PathVariable String title) throws Exception {
        return blogService.getBlogPostByTitle(title);
    }

    @GetMapping("/studyPost")
    public List<PostDTO> getStudyPosts() {
        return studyService.getAllStudyPost();
    }

    @GetMapping("/studyPost/{title}")
    public PostDTO getStudyPostByTitle(@PathVariable String title) throws Exception {
        return studyService.getStudyPostByTitle(title);
    }

}
