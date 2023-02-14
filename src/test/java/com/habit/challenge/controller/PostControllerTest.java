package com.habit.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habit.challenge.domain.Post;
import com.habit.challenge.repository.PostRepository;
import com.habit.challenge.request.PostRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/get요청시 Hello 출력") // 참고용
    void getTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/gets")
                        .param("title", "스터디")
                        .param("content", "mvc연습"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
                .andDo(print());     // 작업결과를 출력해준다 -> 주석하면 출력 안됨

    }
/*

    @Test
    @DisplayName("/post요청시 title 값은 필수임") // 참고용
    void postTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\" : \"\", \"content\" : \"내용\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());

    }
*/

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void test3() throws Exception {  // 가능하면 application/json을 권장합니다. (기존 application/x-www-form-urlencoded)
        // given
        PostRequest request = new PostRequest("제목입니다","내용입니다.");

        ObjectMapper objectMapper =  new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        System.out.println(json);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1L, postRepository.count());
    }

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void test4() throws Exception {  // 가능하면 application/json을 권장합니다. (기존 application/x-www-form-urlencoded)

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다\", \"content\": \"컨텐츠\"}"))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다22222", post.getTitle());
        assertEquals("내용입니다", post.getContent());
    }
}