package com.mindtree.springbootapi.services;

import com.mindtree.springbootapi.dao.ChapterRepository;
import com.mindtree.springbootapi.dao.TopicRepository;
import com.mindtree.springbootapi.exceptions.ApiException;
import com.mindtree.springbootapi.models.Chapter;
import com.mindtree.springbootapi.models.ListResponse;
import com.mindtree.springbootapi.models.Response;
import com.mindtree.springbootapi.models.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private TopicRepository topicRepository;

    public ListResponse<Chapter> getChapters(Integer topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ApiException(String.format("Topic with id %d not found", topicId), HttpStatus.NOT_FOUND));
        return new ListResponse<>(200, "OK", topic.getChapters(), topic.getChapters().size());
    }

    public Response<Chapter> getChapter(Integer topicId, Integer chapterId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new ApiException(String.format("Topic with id %d not found", topicId), HttpStatus.NOT_FOUND));
        Chapter chapter = topic.getChapters()
                .stream()
                .filter(c -> c.getCid() == chapterId).findFirst()
                .orElseThrow(() -> new ApiException(String.format("Chapter with id %d not found under topic with id %d", chapterId,topicId), HttpStatus.NOT_FOUND));
        return new Response<>(200, "OK", chapter);
    }

    public Response<Chapter> addChapter(Integer topicId,Chapter chapter) {
       return topicRepository.findById(topicId).map(topic -> {
             chapter.setTopic(topic);
             return new Response<>(201,"CREATED",chapterRepository.save(chapter));
        }).orElseThrow(() -> new ApiException(String.format("Topic with %d not found", topicId), HttpStatus.NOT_FOUND));
    }
}
