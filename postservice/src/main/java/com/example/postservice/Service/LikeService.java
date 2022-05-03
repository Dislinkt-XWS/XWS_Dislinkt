package com.example.postservice.Service;

import com.example.postservice.Model.Post;

public interface LikeService {
    Post likePost(String userId, String postId);
    Post disLikePost(String userId, String postId);
    Post unLikePost(String userId, String postId);
    Post unDislikePost(String userId, String postId);
}
