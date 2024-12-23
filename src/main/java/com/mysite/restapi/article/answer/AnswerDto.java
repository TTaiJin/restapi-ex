package com.mysite.restapi.article.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {

    @NotEmpty(message = "공백은 안됩니다.")
    private String content;

    private Long articleId;
}
