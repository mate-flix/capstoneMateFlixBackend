package com.mteflix.capstonemateflixbackend.auth;
import com.mteflix.capstonemateflixbackend.mailConfig.EmailRequest;
import com.mteflix.capstonemateflixbackend.mailConfig.MateEmailService;
import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.profile.Profile;
import com.mteflix.capstonemateflixbackend.profile.ProfileRepository;
import com.mteflix.capstonemateflixbackend.user.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import com.mteflix.capstonemateflixbackend.user.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mteflix.capstonemateflixbackend.user.Authority.USER;
@Slf4j
@Service
@AllArgsConstructor
public class MetaFlixAuthService implements AuthService{
    private final UserRepository userRepository;
    private final MateEmailService mateEmailService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
private final ProfileRepository profileRepository;


    // TO DO
    // EMAIL VERIFICATION
    // 0AUTH
    @Override
    public AuthResponse register(AuthRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Profile profile = new Profile();
        profile.setFirstName(registerRequest.getFirstName());
        profile.setLastName(registerRequest.getLastName());
        profileRepository.save(profile);
      user.setProfile(profile);
        user.setAuthorities(List.of(USER));
        user.setToken(generateToken());
        User savedUser = userRepository.save(user);
        EmailRequest emailRequest = new EmailRequest();
       emailRequest.setTo(savedUser.getEmail());
      emailRequest.setBody("<p>Welcome to mate flix. kindly verify your account\" + savedUser.getToken() + \"or click on this link \" + verificationLink</p>");
    String verificationLink = "http://api/v1/auth/verify/" + savedUser.getToken();
    emailRequest.setSubject("MATE FLIX EMAIL VERIFICATION");
        mateEmailService.sendSimpleMail(emailRequest);
        return new AuthResponse(savedUser.getId());
    }

private String generateToken () {
        UUID token = UUID.randomUUID();

        return  token.toString().replace("-", "");
}

    public boolean verifyUserEmailByCode(String email, String verificationCode) {
        // Find the user in the database based on the email and verification code
        Optional<User> userOptional = userRepository.findByEmailAndToken(email, verificationCode);

        if (userOptional.isPresent()) {
            // Mark the user as verified
            User user = userOptional.get();
            user.setVerified(true);
            user.setToken(null); // Clear the verification code
            userRepository.save(user);
            return true;
        }

        return false;
    }
    public boolean verifyUserEmailByLink(String verificationCode) {
        // Find the user in the database based on the verification code
        Optional<User> userOptional = userRepository.findByToken(verificationCode);

        if (userOptional.isPresent()) {
            // Mark the user as verified
            User user = userOptional.get();
            user.setVerified(true);
            user.setToken(null); // Clear the verification code
            userRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public User getUserById(Long id) throws MateFlixException {
        return userRepository.findById(id).orElseThrow(
                ()-> new MateFlixException(String.format("user with id %d not found", id))
        );
    }

    @Override
    public AuthResponse getUserBy(Long id) throws MateFlixException {
        User user = getUserById(id);
        return modelMapper.map(user, AuthResponse.class);
    }

    @Override
    public List<AuthResponse> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        log.info("users:: {}", users);
        return users.stream()
                .map(user->modelMapper.map(user, AuthResponse.class))
                .toList();

    }

    @Override
    public User getUserBy(String email)  {
        return userRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException(
                        String.format("user with email %s not found", email)
                ));
    }
}
