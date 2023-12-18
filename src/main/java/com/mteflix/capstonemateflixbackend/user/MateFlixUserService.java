package com.mteflix.capstonemateflixbackend.user;


import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.globalDTO.Response;
import com.mteflix.capstonemateflixbackend.notification.NotificationRequest;
import com.mteflix.capstonemateflixbackend.notification.NotificationService;
import com.mteflix.capstonemateflixbackend.profile.Profile;
import com.mteflix.capstonemateflixbackend.profile.ProfileRepository;

import com.mteflix.capstonemateflixbackend.token.VerificationToken;
import com.mteflix.capstonemateflixbackend.token.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mteflix.capstonemateflixbackend.user.Authority.USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class MateFlixUserService implements UserService{
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    private final ProfileRepository profileRepository;
    private final NotificationService notificationService;


    @Override
    public User createUser(CreateUserRequest createUserRequest) throws MateFlixException {
        // Check if the user already exists
        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new MateFlixException("User with this email already exists");
        }

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        // Create a new user
        User newUser = new User();
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPassword(encodedPassword);
        Profile profile = new Profile();
        profile.setFirstName(createUserRequest.getFirstName());
        profile.setLastName(createUserRequest.getLastName());
        String token =  UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, newUser);
        tokenRepository.save(verificationToken);
       newUser.setToken(verificationToken);
       Profile profile1 =   profileRepository.save(profile);
        newUser.setProfile(profile1);
        newUser.setAuthorities(List.of(USER));

        return userRepository.save(newUser);
    }


    @Override
    public User saveUser (User user) {
        return userRepository.save(user);
    }

    @Override
    public Response sendFriendRequest(FriendRequest friendRequest) throws MateFlixException {
        Optional<User> sender = userRepository.findByEmail(friendRequest.getSender());
        Optional<User> recipient = userRepository.findByEmail(friendRequest.getRecipient());
        if (recipient.isEmpty()) {
            throw  new MateFlixException("User not found");
        }
        if (recipient.get().getFriends().contains(sender.get())) {
            throw new MateFlixException("Already friends with each other");
        }
        recipient.get().getFriendRequest().add(sender.get());
        sender.get().getRecievedFriendRequest().add(recipient.get());
        NotificationRequest senderNotification = new NotificationRequest();
        senderNotification.setContent("Dear " + sender.get().getProfile().getFirstName() + " your request to " + recipient.get().getProfile().getFirstName() + " has been sent successfully");
        senderNotification.setRecipientEmail(sender.get().getEmail());
        notificationService.sendNotification(senderNotification);


        NotificationRequest recieverNotification = new NotificationRequest();
        senderNotification.setContent("Dear " + recipient.get().getProfile().getFirstName() + "  " + sender.get().getProfile().getFirstName() + " wants to be your mate");
        senderNotification.setRecipientEmail(recipient.get().getEmail());
        notificationService.sendNotification(recieverNotification);
        return new Response("request successfuly sent");
    }

    @Override
    public Response acceptFriendRequest(FriendRequest friendRequest) throws MateFlixException {
        Optional<User> sender = userRepository.findByEmail(friendRequest.getSender());
        Optional<User> recipient = userRepository.findByEmail(friendRequest.getRecipient());
        if (recipient.isEmpty()) {
            throw  new MateFlixException("User not found");
        }
        if (recipient.get().getFriends().contains(sender.get())) {
            throw new MateFlixException("Already friends with each other");
        }
        if (!sender.get().getFriendRequest().contains(recipient.get())) {
            throw new MateFlixException("Send friend request first");
        }
        recipient.get().getFriends().add(sender.get());
        sender.get().getFriends().add(recipient.get());
        NotificationRequest senderNotification = new NotificationRequest();
        senderNotification.setContent("Dear " + sender.get().getProfile().getFirstName() + " " + recipient.get().getProfile().getFirstName() + " is now your MATE");
        senderNotification.setRecipientEmail(sender.get().getEmail());
        notificationService.sendNotification(senderNotification);
sender.get().getFriendRequest().remove(recipient.get());
recipient.get().getRecievedFriendRequest().remove(sender.get());

        NotificationRequest recieverNotification = new NotificationRequest();
        senderNotification.setContent("Dear " + recipient.get().getProfile().getFirstName() + "  " + sender.get().getProfile().getFirstName() + " is now your mate");
        senderNotification.setRecipientEmail(recipient.get().getEmail());
        notificationService.sendNotification(recieverNotification);
        return new Response("request successfuly sent");
    }


    @Override
    public User getUserById(Long id) throws MateFlixException {
        return userRepository.findById(id).orElseThrow(
                ()-> new MateFlixException(String.format("user with id %d not found", id))
        );
    }
    @Override
    public UserResponse getUserBy(Long id) throws MateFlixException {
        User user = getUserById(id);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        log.info("users:: {}", users);
        return users.stream()
                    .map(user->modelMapper.map(user, UserResponse.class))
                    .toList();

    }

    @Override
    public Optional<User> getUserBy(String email) {
        return userRepository.findByEmail(email);
    }

    private void updatePassword(String email, String newPassword) throws MateFlixException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new MateFlixException("User not found with email: " + email));


        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


    public String initiatePasswordReset(String email) throws MateFlixException {
//        Optional<User> user = userRepository.findByEmail(email);
//        if (user.isEmpty()) {
//            throw new MateFlixException("User not found");
//        }
//        EmailRequest emailRequest = new EmailRequest();
//        emailRequest.setTo(user.get().getEmail());
//        Token token = tokenService.generateToken();
//        user.get().setToken(token);
//        String verificationLink = "http://api/v1/auth/verify/";
//        emailRequest.setBody("Welcome to mate flix. kindly verify your account. Your verification code is    " + user.get().getToken() + "    or click on this link    "  + verificationLink);
//
//        emailRequest.setSubject("MATE FLIX PASSWORD REQUEST");
//        mateEmailService.sendSimpleMail(emailRequest);


       return "Password reset initiated for email";

    }

//    @Override
//    public String resetPassword(String token, String newPassword, String email) throws MateFlixException {
//        Optional<User> user = userRepository.findByEmail(email);
//        if (user.isEmpty()) {
//            throw new MateFlixException("User not found");
//        }
//        Token token1 = user.get().getToken();
//        if (!tokenService.isValidToken(token) || !token1.getToken().equals(token)) {
//            throw new InvalidTokenException("Invalid or expired token");
//        }
//        // Update user's password
//        updatePassword(user.get().getEmail(), newPassword);
//
//        // Remove token after use
//      user.get().setToken(null);
//
//
//return "Password reset successfully";
//
//
//
//    }
//
//
//
//
//
//



}
