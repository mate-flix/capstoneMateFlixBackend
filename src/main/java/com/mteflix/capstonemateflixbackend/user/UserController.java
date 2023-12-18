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

    @PostMapping ("/send-friend-request")
    public ResponseEntity<?> sendFriendRequest (@RequestBody FriendRequest friendRequest) {
        try {
         return ResponseEntity.ok(userService.sendFriendRequest(friendRequest));
        } catch (MateFlixException exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @PostMapping ("/accept-friend-request")
    public ResponseEntity<?> acceptFriendRequest (@RequestBody FriendRequest friendRequest) {
        try {
            return ResponseEntity.ok(userService.acceptFriendRequest(friendRequest));
        } catch (MateFlixException exception) {
            return ResponseEntity.badRequest().body(exception);
        }}
//    @PostMapping("/initiate")
//    public ResponseEntity<String> initiatePasswordReset(@RequestParam String email) {
//        try {
//            userService.initiatePasswordReset(email);
//            return ResponseEntity.ok("Password reset initiated. Check your email for further instructions.");
//        } catch (MateFlixException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/reset")
//    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword, @RequestParam String email) {
//        try {
//            userService.resetPassword(token, newPassword, email);
//            return ResponseEntity.ok("Password reset successful");
//        } catch (MateFlixException | InvalidTokenException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}
