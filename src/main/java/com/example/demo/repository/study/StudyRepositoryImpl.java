package com.example.demo.repository.study;

import com.example.demo.model.study.StudyPost;
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
public class StudyRepositoryImpl implements StudyRepository {
    private final EntityManager em;
    private final Session session;
    private static Logger logger = LoggerFactory.getLogger(StudyRepositoryImpl.class);

    public StudyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.session = em.unwrap(Session.class);
    }

    @Override
    public List<StudyPost> getAllStudyPost() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<StudyPost> cq = cb.createQuery(StudyPost.class);

        Root<StudyPost> root = cq.from(StudyPost.class);

        cq.select(root);
        cq.orderBy(cb.desc(root.get("date")));

        TypedQuery<StudyPost> query = session.createQuery(cq);
        List<StudyPost> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public StudyPost getStudyPostByTitle(String title) throws Exception {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<StudyPost> cq = cb.createQuery(StudyPost.class);

        Root<StudyPost> root = cq.from(StudyPost.class);

        root.fetch("studyContents", JoinType.LEFT);
        cq.where(cb.equal(root.get("title"), title));
        StudyPost studyPost = session.createQuery(cq).getSingleResult();

        return studyPost;
    }
}
