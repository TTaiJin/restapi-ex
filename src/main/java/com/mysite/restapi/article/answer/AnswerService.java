package com.mysite.restapi.article.answer;

import com.mysite.restapi.article.article.Article;
import com.mysite.restapi.article.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ArticleService articleService;

    @Transactional
    public Answer createAnswer(Long articleId, AnswerDto answerDto) {
        Article article = articleService.findById(articleId);
        Answer answer = Answer.builder()
                .content(answerDto.getContent())
                .build();
        article.addAnswer(answer);
        return answerRepository.save(answer);
    }

    public List<Answer> findAll(Long articleId) {
        Article article = articleService.findById(articleId);
        return answerRepository.findAllByArticle(article);
    }

    @Transactional
    public Answer modify(Long articleId, Long id, AnswerDto answerDto) {
        Article article = articleService.findById(articleId);
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid answer Id: " + id));

        //Answer의 articeId랑 Article의 Id랑 같은지 비교
        if (!answer.getArticleId().equals(article.getId())) {
            throw new IllegalArgumentException("answer는 article에 속하지 않습니다.");
        }

        answer.setContent(answerDto.getContent());
        return answerRepository.save(answer);
    }

    @Transactional
    public void delete(Long articleId, Long id) {
        Article article = articleService.findById(articleId);
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid answer Id: " + id));

        //Answer의 articeId랑 Article의 Id랑 같은지 비교
        if (!answer.getArticleId().equals(article.getId())) {
            throw new IllegalArgumentException("answer는 article에 속하지 않습니다.");
        }
        answerRepository.delete(answer);
    }
}
