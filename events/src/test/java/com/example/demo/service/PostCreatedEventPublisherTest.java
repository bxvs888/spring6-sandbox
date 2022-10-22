package com.example.demo.service;

import com.example.demo.event.transactional.PostCreated;
import com.example.demo.event.transactional.PostCreatedEventListener;
import com.example.demo.event.transactional.PostCreatedEventPublisher;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(classes = PostCreatedEventPublisherTest.TestConfig.class)
public class PostCreatedEventPublisherTest {

    @Configuration
    @ComponentScan(basePackageClasses = PostCreated.class)
    static class TestConfig {

    }

    @Autowired
    PostCreatedEventPublisher eventPublisher;

    @Autowired
    PostCreatedEventListener handler;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testEventPublish() {
        this.eventPublisher.publishPostCreated(new PostCreated(UUID.randomUUID(), "test", LocalDateTime.now()));

        Awaitility.await().atMost(Duration.ofMillis(500))
                .untilAsserted(() -> assertThat(handler.getEvents().size()).isEqualTo(1));
    }
}
