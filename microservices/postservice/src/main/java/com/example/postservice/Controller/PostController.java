package com.example.postservice.Controller;

import com.example.postservice.DTO.NewPostDTO;
import com.example.postservice.DTO.PostDTO;
import com.example.postservice.Model.Post;
import com.example.postservice.Service.LikeService;
import com.example.postservice.Service.PostService;
import com.xwsdislinkt.userservice.FollowersRequest;
import com.xwsdislinkt.userservice.FollowersResponse;
import com.xwsdislinkt.userservice.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeService likeService;

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> create(@ModelAttribute NewPostDTO dto, @RequestHeader String authorization) {
        if ((dto.getTextContent() == null || dto.getTextContent().isEmpty())
            && dto.getImage() == null)
            return new ResponseEntity<>("Each post must have some type of content!", HttpStatus.BAD_REQUEST);

        var imagePath = "";
        if (dto.getImage() != null)
            imagePath = postService.uploadImages(dto.getImage());

        var post = new Post();
        post.setUserId(dto.getUserId());
        post.setTextContent(dto.getTextContent());
        post.setImagePath(imagePath);
        Post newPost = null;
        try {
            newPost = postService.save(post);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to create post because there is no user defined!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(modelMapper.map(postService.saveToDatabase(newPost), PostDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> get() {
        var posts = postService.findAll();
        List<PostDTO> postsDtos = posts
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(postsDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<PostDTO>> findByUserId(@PathVariable("userId") String userId) {
        var posts = postService.findByUserId(userId);
        List<PostDTO> postDTOS = posts
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/newsfeed/{id}")
    public ResponseEntity<List<PostDTO>> getNewsFeed(@PathVariable("id") String id) throws IOException {
        System.out.println("Targeting grpc server: " + userServiceStub.getChannel());
        FollowersRequest followersRequest = FollowersRequest.newBuilder().setUserId(id).build();
        FollowersResponse followersResponse = userServiceStub.followers(followersRequest);
        List<String> userIds = followersResponse.getFollowersList();

        List<Post> posts = postService.findAll();
        List<Post> postsToShow = new ArrayList<>();
        for (String userId : userIds) {
            for (Post post : posts) {
                if (userId.equals(post.getUserId())) {
                    if (post.getImagePath() != null && !post.getImagePath().isEmpty())
                        post.setImagePath(postService.getBase64(post));
                    postsToShow.add(post);
                }
            }
        }

        List<PostDTO> postDTOS = postsToShow
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/like")
    public ResponseEntity<?> likePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine which post to like!", HttpStatus.BAD_REQUEST);
        Post post = likeService.likePost(postId, userId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/dislike")
    public ResponseEntity<?> dislikePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine which post to dislike!", HttpStatus.BAD_REQUEST);
        Post post = likeService.disLikePost(postId, userId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/unLike")
    public ResponseEntity<?> unLikePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine which post to unlike!", HttpStatus.BAD_REQUEST);
        Post post = likeService.unLikePost(postId, userId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/unDislike")
    public ResponseEntity<?> unDislikePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine from which post the like should be removed!", HttpStatus.BAD_REQUEST);
        Post post = likeService.unDislikePost(postId, userId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }
}
