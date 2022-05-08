package com.example.postservice.Service.ServiceImpl;

import com.example.postservice.Model.Post;
import com.example.postservice.Repository.PostRepository;
import com.example.postservice.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceMongoDb implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Optional<Post> get(String id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post p, String authorization) {
        p.setId(UUID.randomUUID().toString());
        var userId = findCurrentUser(authorization);
        if (userId == null)
            return null;

        p.setUserId(userId);
        p.setDatePosted(LocalDateTime.now());
        p.setUserLikes(new ArrayList<>());
        p.setUserDislikes(new ArrayList<>());

        return postRepository.save(p);
    }

    @Override
    public void delete(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findByUserId(String userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post update(Post p) {
        return postRepository.save(p);
    }

    public String findCurrentUser(String authorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();
        var currentUserId = restTemplate.exchange("http://user-service:8761/api/users/current-user",
                HttpMethod.GET, entity, String.class);

        System.out.println("OVO JE TRENUTNI USER: " + currentUserId.getBody());
        return currentUserId.getBody();
    }

    @Override
    public List<String> uploadImages(List<MultipartFile> images) {
        List<String> imagePaths = new ArrayList<>();

        for (var file : images) {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            String fileName = file.getOriginalFilename();
            System.out.println("NAZIV FAJLA " + fileName);

            var location = "./src/main/resources/static/images/";

            File newFile = new File(location + fileName);
            System.out.println("LOKACIJA FAJLA: " + newFile.getAbsolutePath());

            try {
                inputStream = file.getInputStream();
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }

                outputStream = new FileOutputStream(newFile);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            imagePaths.add(newFile.getAbsolutePath().substring(3));
        }

        return imagePaths;
    }
}
