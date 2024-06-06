package com.example.FitDoc.controller;

import com.example.FitDoc.model.Post;
import com.example.FitDoc.repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Marks this class as a REST controller, meaning it's ready for use by Spring MVC to handle web requests.
@RestController
// Maps incoming web requests to /posts. All request methods in this class will start with this URI.
@RequestMapping("/posts")
public class PostController {
    // Automatically injects the PostRepository instance created by Spring. Enables access to Post database operations.
    @Autowired
    private PostRepository postRepository;
    // Handles HTTP GET requests for /posts. Returns all posts  as a JSON string.
    @GetMapping
    public String getAllPosts() {
        // Retrieves all posts from the database
        List<Post> posts = postRepository.findAll();
        // Converts list of posts to JSON format and returns it
        return new Gson().toJson(posts);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

//    @PutMapping("/{id}")
//    public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
//        updatedPost.setId(id); // Ensure the ID is set
//        return postRepository.save(updatedPost);
//    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        postRepository.deleteById(id);
    }
}