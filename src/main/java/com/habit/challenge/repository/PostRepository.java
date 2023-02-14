package com.habit.challenge.repository;

import com.habit.challenge.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


}

