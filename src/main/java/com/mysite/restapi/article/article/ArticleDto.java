package com.mysite.restapi.article.article;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {

    @NotEmpty(message = "공백은 안됩니다.")
    private String title;

    @NotEmpty(message = "공백은 안됩니다.")
    private String content;
}
