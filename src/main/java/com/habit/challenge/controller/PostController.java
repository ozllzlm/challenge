package com.habit.challenge.controller;

import com.habit.challenge.request.PostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j          // log 출력
@RestController             // 응답하는 패키지 안에 응답하는 형태로 반환
public class PostController {

    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT

    @GetMapping("/gets")  // http://localhost:8080/gets?title=study&content=mvc 연습
    public String get(@RequestParam String title,@RequestParam String content) {  // ctrl+shift+t -> test 코드 만들기
        log.info("title={} content={}", title , content);
        return "hello";
    }

    @PostMapping("/posts")  // http://localhost:8080/posts?title=study&content=mvc 연습
    public Map<String, String> post(@RequestBody @Valid PostRequest params/*, BindingResult result*/) {
      /*  if(result.hasErrors()) {
            List<FieldError> fieldError = result.getFieldErrors();
            FieldError firstFieldError  = fieldError.get(0);
            String fielName = firstFieldError.getField();
            String errorMessage = firstFieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put(fielName, errorMessage);
            return error;
        }*/
        // 데이터를 검증하는 이유
        // 1. client 개발자가 깜빡할 수 있다. 실수로 값을 안보낼 수 있다.
        // 2. cliemt bug로 값이 누락될 수 있다.
        // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있ㄷ.
        // 5. 서버 개발자의 편안함을 위해서
        // 6. 매번 메서드마다 값을 검증 해야한다.

        log.info("params={}", params);



        /* String title = params.getTitle();
        String content = params.getContent();


     **** @Valid로 퉁칠 수 있다.^^
      if(title == null || title.equals("")){*/
            // error
            // 1. 빡세다
            //******* 2. 무언가 3번이상 반복작업을 할때 내가 뭔가 잘못하고 있는건 아닐지 의심한다.
            // 3. 누락가능성
            // 4. 생각보다 검증해야할것이 많다.(꼼꼼하지 않을 수 있다.)
            // 5. 뭔가 개발자스럽지 않다. -> 간지 안남^_^
       /*     throw new Exception("title값이 없어유ㅠㅠㅠ");
        }*/

        return Map.of();
    }

}
