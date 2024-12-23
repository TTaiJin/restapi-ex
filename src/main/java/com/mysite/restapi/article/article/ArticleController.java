package com.mysite.restapi.article.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    //모든 게시글 조회
    @GetMapping()
    public ResponseEntity<List<Article>> getArticleList() {
        List<Article> articles = articleService.findAll();
        return ResponseEntity.ok(articles);
    }

    //id로 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleService.findById(id);
        return ResponseEntity.ok(article);
    }

    //글 작성
    @PostMapping()
    public ResponseEntity<Article> createArticle(@RequestBody @Valid ArticleDto articleDto) {
        Article article = articleService.create(articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    //글 수정
    @PutMapping("{id}")
    public ResponseEntity<Article> modifyArticle(@PathVariable Long id,
                                                 @RequestBody @Valid ArticleDto articleDto) {
        Article article = articleService.modify(id, articleDto);
        return ResponseEntity.ok(article);
    }

    //글 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
