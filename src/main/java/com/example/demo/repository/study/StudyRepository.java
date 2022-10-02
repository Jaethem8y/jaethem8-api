package com.example.demo.repository.study;

import com.example.demo.model.study.StudyPost;

import java.util.List;

public interface StudyRepository {
    public List<StudyPost> getAllStudyPost();
    public StudyPost getStudyPostByTitle(String title) throws Exception;
}
