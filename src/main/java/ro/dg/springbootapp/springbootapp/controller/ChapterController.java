package ro.dg.springbootapp.springbootapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ro.dg.springbootapp.springbootapp.domain.model.Chapter;
import ro.dg.springbootapp.springbootapp.repositories.ChapterRepository;

@RestController
public class ChapterController {

    private final ChapterRepository chapterRepository;

    public ChapterController(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @GetMapping("/chapters")
    public Flux<Chapter> listing(){
        return chapterRepository.findAll();
    }

}
