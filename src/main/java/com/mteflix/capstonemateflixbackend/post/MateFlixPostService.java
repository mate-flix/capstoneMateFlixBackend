package com.mteflix.capstonemateflixbackend.post;

import com.mteflix.capstonemateflixbackend.auth.CloudService;
import com.mteflix.capstonemateflixbackend.exceptions.MateFlixException;
import com.mteflix.capstonemateflixbackend.exceptions.MediaUploadException;
import com.mteflix.capstonemateflixbackend.user.User;
import com.mteflix.capstonemateflixbackend.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.rmi.server.LogStream.log;

@Service
@AllArgsConstructor
@Slf4j
public class MateFlixPostService implements PostService {
    private final CloudService cloudService;
    private final PostRepository postRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    @Override
    public UploadPostResponse upload(String creatorEmail, String title, String description,  MultipartFile image) throws MateFlixException {
       log(creatorEmail);
        Optional<User> user = userService.getUserBy(creatorEmail);

       if (user.isEmpty()) {
           throw new MateFlixException("USER NOT FOUND");
       }
        String url = cloudService.upload(image);


        Post post = new Post();
        post.setUrl(url);
        post.setUploader(user.get());
        post.setTitle(title);
        post.setDescription(description);

        Post savedMedia = postRepository.save(post);
        return buildUploadMediaResponse(savedMedia);
    }

    private static UploadPostResponse buildUploadMediaResponse(Post media){
        UploadPostResponse response = new UploadPostResponse();
        response.setMessage("Media upload successful");
        response.setMediaId(media.getId());
        return response;
    }

    @Override
    public Post getPostById(Long id) throws MateFlixException {
        return postRepository.findById(id).orElseThrow( ()-> new MateFlixException(String.format("Media with id %d not found", id)));
    }



    @Override
    public GetPostResponse getPostBy(Long id) throws MateFlixException {
        Post user = getPostById(id);
        return modelMapper.map(user, GetPostResponse.class);
    }

    @Override
    public List<GetPostResponse> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Post> userPage = postRepository.findAll(pageable);
        List<Post> users = userPage.getContent();
        return users.stream()
                .map(user->modelMapper.map(user, GetPostResponse.class))
                .toList();

    }

}
