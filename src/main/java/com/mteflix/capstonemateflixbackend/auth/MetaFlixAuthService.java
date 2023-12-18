package com.mteflix.capstonemateflixbackend.auth;
import com.mteflix.capstonemateflixbackend.mail.EmailRequest;
import com.mteflix.capstonemateflixbackend.mail.MateEmailService;
import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.token.VerificationToken;
import com.mteflix.capstonemateflixbackend.token.VerificationTokenRepository;
import com.mteflix.capstonemateflixbackend.user.CreateUserRequest;
import com.mteflix.capstonemateflixbackend.user.User;
import com.mteflix.capstonemateflixbackend.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import static com.mteflix.capstonemateflixbackend.user.Authority.USER;


@Slf4j
@Service
@AllArgsConstructor
public class MetaFlixAuthService implements AuthService {

    private final MateEmailService mateEmailService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final VerificationTokenRepository tokenRepository;


    @Override
    public AuthResponse register(AuthRequest registerRequest) throws MateFlixException {

        CreateUserRequest createUserRequest = modelMapper.map(registerRequest, CreateUserRequest.class);
        User user = userService.createUser(createUserRequest);

        user.setAuthorities(List.of(USER));
        initiateUserVerificationEmail(user.getEmail());
        return new AuthResponse(user.getId());
    }



    public void initiateUserVerificationEmail(String email) throws MateFlixException {
        Optional<User> user = userService.getUserBy(email);
        if (user.isEmpty()) {
            throw new MateFlixException("User not found");
        }
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(user.get().getEmail());

        String verificationLink = "http://localhost:8080/api/v1/auth/verifyEmail?token="+user.get().getToken().getToken();
        emailRequest.setBody("Welcome to mate flix. kindly verify your account. Your verification code is    " + user.get().getToken().getToken() + "    or click on this link    " + verificationLink);



        emailRequest.setSubject("MATE FLIX PASSWORD REQUEST");
        mateEmailService.sendSimpleMail(emailRequest);


    }


    public ValidateResponse verify(String token) throws MateFlixException {
        Optional<VerificationToken> theToken = tokenRepository.findVerificationTokensByToken(token);
        if (theToken.isPresent() && theToken.get().getUser().isEnabled()){
            throw  new MateFlixException("This account has already been verified, please, login");
        }
        String verificationResult = validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return new ValidateResponse("Email verified successfully. Now you can login to your account");
        } else {
       throw new MateFlixException("Invalid verification token");}
    }


    private String validateToken(String theToken) {
        Optional<VerificationToken> token = tokenRepository.findVerificationTokensByToken(theToken);
        if(token.isEmpty()){
            return "Invalid verification token";
        }
        User user = token.get().getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.get().getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            tokenRepository.delete(token.get());
            return "Token already expired";
        }
        user.setEnabled(true);
        userService.saveUser(user);
        return "valid";
    }

}





