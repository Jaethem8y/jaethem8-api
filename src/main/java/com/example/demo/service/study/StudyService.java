package com.example.demo.service.study;

import com.example.demo.dto.PostDTO;
import com.example.demo.model.study.StudyPost;

import java.util.List;

public interface StudyService {
    public List<PostDTO> getAllStudyPost();
    public PostDTO getStudyPostByTitle(String title) throws Exception;
    public StudyPost mapStudyPost(PostDTO postDTO, StudyPost studyPost);
    public PostDTO studyPostToDTO(StudyPost studyPost);
}
