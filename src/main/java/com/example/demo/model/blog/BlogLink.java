package com.example.demo.model.blog;

import com.example.demo.model.Link;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "blog_link")
public class BlogLink extends Link {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "blog_content_id")
    private BlogContent blogContent;

    public BlogLink() {
    }

    public BlogLink(long id, String tag, String link, BlogContent blogContent) {
        super(id, tag, link);
        this.blogContent = blogContent;
    }

    public BlogContent getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(BlogContent blogContent) {
        this.blogContent = blogContent;
    }

}
