package com.mteflix.capstonemateflixbackend.user;

import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws MateFlixException {
        try {
            return ResponseEntity.ok(userService.getUserBy(id));
        }catch (MateFlixException exception){
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(userService.getUsers(page, size));
    }
}
