package com.example.demo.model.study;

import com.example.demo.model.Link;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "study_link")
public class StudyLink extends Link {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "study_content_id")
    private StudyContent studyContent;

    public StudyLink() {
    }

    public StudyLink(long id, String tag, String link, StudyContent studyContent) {
        super(id, tag, link);
        this.studyContent = studyContent;
    }

    public StudyContent getStudyContent() {
        return studyContent;
    }

    public void setStudyContent(StudyContent studyContent) {
        this.studyContent = studyContent;
    }
}
