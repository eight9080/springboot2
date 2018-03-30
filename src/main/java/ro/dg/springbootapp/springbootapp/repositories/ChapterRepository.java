package ro.dg.springbootapp.springbootapp.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ro.dg.springbootapp.springbootapp.domain.model.Chapter;

public interface ChapterRepository  extends ReactiveCrudRepository<Chapter, String>{
}
