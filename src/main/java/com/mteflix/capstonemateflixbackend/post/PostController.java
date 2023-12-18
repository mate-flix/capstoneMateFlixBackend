package com.mteflix.capstonemateflixbackend.post;


import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.exceptions.MediaUploadException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> uploadMedia(@RequestParam String creatorEmail,
                                         @RequestParam String title,
                                         @RequestParam String description,
                                         @RequestParam MultipartFile image) throws MateFlixException  {

        var response = postService.upload(creatorEmail, title, description, image);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        try {
            return ResponseEntity.ok(postService.getPostBy(id));
        }catch (MateFlixException exception){
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(postService.getUsers(page, size));
    }

}
