package com.mysite.restapi.article.answer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/articles/{articleId}/answers")
@RequiredArgsConstructor
@RestController
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<Answer> create(@PathVariable Long articleId,
                                         @RequestBody @Valid AnswerDto answerDto) {
        Answer answer = answerService.createAnswer(articleId, answerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping()
    public ResponseEntity<List<Answer>> getAnswerList(@PathVariable Long articleId) {
        List<Answer> answerList = answerService.findAll(articleId);
        return ResponseEntity.ok(answerList);
    }

    @PutMapping("{id}")
    public ResponseEntity<Answer> modify(@PathVariable Long articleId,
                                         @PathVariable Long id,
                                         @RequestBody @Valid AnswerDto answerDto) {
        Answer modifiedAnswer = answerService.modify(articleId, id, answerDto);
        return ResponseEntity.ok(modifiedAnswer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long articleId,
                                       @PathVariable Long id) {
        answerService.delete(articleId, id);
        return ResponseEntity.noContent().build();
    }
}
