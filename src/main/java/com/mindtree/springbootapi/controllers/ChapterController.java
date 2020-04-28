package com.mindtree.springbootapi.controllers;

import com.mindtree.springbootapi.models.Chapter;
import com.mindtree.springbootapi.models.ListResponse;
import com.mindtree.springbootapi.models.Response;
import com.mindtree.springbootapi.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample/api/topics/{topicId}/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping
    public ListResponse<Chapter> getChapters(@PathVariable Integer topicId){
        return chapterService.getChapters(topicId);
    }

    @GetMapping("{id}")
    public Response<Chapter> getChapter(@PathVariable Integer topicId,@PathVariable Integer id){
        return chapterService.getChapter(topicId,id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Response<Chapter> addChapter(@PathVariable Integer topicId, @RequestBody Chapter chapter){
       return chapterService.addChapter(topicId,chapter);
    }
}
