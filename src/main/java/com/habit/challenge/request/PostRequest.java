package com.habit.challenge.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter                     // Getter 생성
@Setter                     // Setter 생성
@AllArgsConstructor         // 필드에 있는걸 생성자로 만들어서 쓰겠다
@ToString
public class PostRequest {

    @NotBlank
    private String title;
    @NotBlank(message = "컨텐츠를 입력해주세요")
    private String content;

    // Alt+insert -> Constructor 자동 생성 가능

}
