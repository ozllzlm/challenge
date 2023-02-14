package com.habit.challenge.service;

import com.habit.challenge.domain.Post;
import com.habit.challenge.repository.PostRepository;
import com.habit.challenge.request.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostRequest postRequest) {
        Post post = new Post(postRequest.getTitle(), postRequest.getContent());
        postRepository.save(post);
    }
}


