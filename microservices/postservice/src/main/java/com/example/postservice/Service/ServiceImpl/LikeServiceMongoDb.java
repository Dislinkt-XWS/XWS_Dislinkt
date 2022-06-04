package com.example.postservice.Service.ServiceImpl;

import com.example.postservice.Model.Post;
import com.example.postservice.Service.LikeService;
import com.example.postservice.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceMongoDb implements LikeService {
    @Autowired
    private PostService postService;

    @Override
    public Post likePost(String postId, String authorization) {
        Post post = postService.get(postId).get();
        String userId = postService.findCurrentUser(authorization);
        if(post.userLiked(userId)) {
            return post;
        }
        if(post.userDisliked(userId)) {
            post.removeDislike(userId);
        }
        post.addLike(userId);
        postService.update(post);
        return post;
    }

    @Override
    public Post disLikePost(String postId, String authorization) {
        Post post = postService.get(postId).get();
        String userId = postService.findCurrentUser(authorization);
        if(post.userDisliked(userId)) {
            return post;
        }
        if(post.userLiked(userId)) {
            post.removeLike(userId);
        }
        post.addDislike(userId);
        postService.update(post);
        return post;
    }

    @Override
    public Post unLikePost(String postId, String authorization) {
        Post post = postService.get(postId).get();
        String userId = postService.findCurrentUser(authorization);
        if(post.userLiked(userId)) {
            post.removeLike(userId);
            postService.update(post);
        }
        return post;
    }

    @Override
    public Post unDislikePost(String postId, String authorization) {
        Post post = postService.get(postId).get();
        String userId = postService.findCurrentUser(authorization);
        if(post.userDisliked(userId)) {
            post.removeDislike(userId);
            postService.update(post);
        }
        return post;
    }
}
