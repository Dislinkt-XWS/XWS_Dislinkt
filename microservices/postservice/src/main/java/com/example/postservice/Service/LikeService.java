package com.example.postservice.Service;

import com.example.postservice.Model.Post;

public interface LikeService {
    Post likePost(String postId, String userId);
    Post disLikePost(String postId, String userId);
    Post unLikePost(String postId, String userId);
    Post unDislikePost(String postId, String userId);
}
