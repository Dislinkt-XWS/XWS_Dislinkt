package com.example.postservice.Service;

import com.example.postservice.Model.Post;

public interface LikeService {
    Post likePost(String postId, String authorization);
    Post disLikePost(String postId, String authorization);
    Post unLikePost(String postId, String authorization);
    Post unDislikePost(String postId, String authorization);
}
