package com.mindtree.springbootapi.dao;

import com.mindtree.springbootapi.models.Chapter;
import org.springframework.data.repository.CrudRepository;

public interface ChapterRepository extends CrudRepository<Chapter,Integer> {
}
