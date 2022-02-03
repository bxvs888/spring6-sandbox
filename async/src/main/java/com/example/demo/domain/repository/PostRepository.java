package com.example.demo.domain.repository;

import com.example.demo.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Async
    CompletableFuture<List<Post>> readAllBy();
}
