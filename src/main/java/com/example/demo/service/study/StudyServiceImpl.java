package com.example.demo.service.study;

import com.example.demo.dto.ContentDTO;
import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.LinkDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.model.study.StudyContent;
import com.example.demo.model.study.StudyImage;
import com.example.demo.model.study.StudyLink;
import com.example.demo.model.study.StudyPost;
import com.example.demo.repository.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyServiceImpl implements StudyService {
    private final StudyRepository studyRepository;
    private static Logger logger = LoggerFactory.getLogger(StudyServiceImpl.class);

    @Override
    @Transactional
    public List<PostDTO> getAllStudyPost() {
        List<StudyPost> studyPosts = studyRepository.getAllStudyPost();
        List<PostDTO> postDTOS = new ArrayList<>();

        studyPosts.forEach(studyPost -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setTitle(studyPost.getTitle());
            postDTO.setDescription(studyPost.getDescription());
            postDTOS.add(postDTO);
        });
        return postDTOS;
    }

    @Override
    @Transactional
    public PostDTO getStudyPostByTitle(String title) throws Exception {
        return studyPostToDTO(studyRepository.getStudyPostByTitle(title));
    }

    @Override
    public StudyPost mapStudyPost(PostDTO postDTO, StudyPost studyPost) {
        studyPost.setTitle(postDTO.getTitle());
        studyPost.setDate(new Timestamp(System.currentTimeMillis()));
        studyPost.setDescription(postDTO.getDescription());

        List<StudyContent> studyContents = new ArrayList<>();
        postDTO.getContents().forEach(contentDTO -> {
            StudyContent studyContent = new StudyContent();
            studyContent.setLocation(contentDTO.getLocation());
            studyContent.setHeader(contentDTO.getHeader());
            studyContent.setContent(contentDTO.getContent());
            studyContent.setCode(contentDTO.getCode());

            List<StudyImage> studyImages = new ArrayList<>();
            contentDTO.getImages().forEach(imageDTO -> {
                StudyImage studyImage = new StudyImage();
                studyImage.setImage(imageDTO.getImage());
                studyImage.setStudyContent(studyContent);
                studyImages.add(studyImage);
            });
            studyContent.setStudyImages(studyImages);

            Set<StudyLink> studyLinks = new HashSet<>();
            contentDTO.getLinks().forEach(linkDTO -> {
                StudyLink studyLink = new StudyLink();
                studyLink.setTag(linkDTO.getTag());
                studyLink.setStudyContent(studyContent);
                studyLinks.add(studyLink);
            });

            studyContent.setStudyPost(studyPost);
            studyContent.setStudyLinks(studyLinks);
            studyContents.add(studyContent);
        });
        studyPost.setStudyContents(studyContents);
        return studyPost;
    }

    @Override
    public PostDTO studyPostToDTO(StudyPost studyPost) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(studyPost.getTitle());
        postDTO.setDescription(studyPost.getDescription());
        postDTO.setPubDate(studyPost.getDate());

        List<ContentDTO> contents = new ArrayList<>();

        studyPost.getStudyContents().forEach(studyContent -> {
            ContentDTO contentDTO = new ContentDTO();
            contentDTO.setHeader(studyContent.getHeader());
            contentDTO.setLocation(studyContent.getLocation());
            contentDTO.setContent(studyContent.getContent());
            contentDTO.setCode(studyContent.getCode());

            List<LinkDTO> links = new ArrayList<>();

            studyContent.getStudyLinks().forEach(studyLink -> {
                LinkDTO linkDTO = new LinkDTO();
                linkDTO.setLink(studyLink.getLink());
                linkDTO.setTag(studyLink.getTag());
                links.add(linkDTO);
            });

            contentDTO.setLinks(links);

            List<ImageDTO> images = new ArrayList<>();

            studyContent.getStudyImages().forEach(studyImage -> {
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImage(studyImage.getImage());
                images.add(imageDTO);
            });

            contentDTO.setImages(images);
            contents.add(contentDTO);
        });

        postDTO.setContents(contents);

        return postDTO;
    }
}
