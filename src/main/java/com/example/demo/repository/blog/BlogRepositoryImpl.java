package com.example.demo.repository.blog;

import com.example.demo.model.blog.BlogPost;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
@Repository
public class BlogRepositoryImpl implements BlogRepository {
    private final EntityManager em;
    private final Session session;
    private static Logger logger = LoggerFactory.getLogger(BlogRepositoryImpl.class);

    public BlogRepositoryImpl(EntityManager em) {
        this.em = em;
        this.session = em.unwrap(Session.class);
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlogPost> cq = cb.createQuery(BlogPost.class);

        Root<BlogPost> root = cq.from(BlogPost.class);

        cq.select(root);
        cq.orderBy(cb.desc(root.get("date")));

        TypedQuery<BlogPost> query = session.createQuery(cq);
        List<BlogPost> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public BlogPost getBlogPostByTitle(String title) throws Exception {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BlogPost> cq = cb.createQuery(BlogPost.class);

        Root<BlogPost> root = cq.from(BlogPost.class);

        root.fetch("blogContents", JoinType.LEFT);
        cq.where(cb.equal(root.get("title"), title));
        BlogPost blogPost = session.createQuery(cq).getSingleResult();

        return blogPost;
    }
}

