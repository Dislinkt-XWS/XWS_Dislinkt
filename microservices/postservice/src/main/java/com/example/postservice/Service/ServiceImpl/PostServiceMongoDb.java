package com.example.postservice.Service.ServiceImpl;

import com.example.postservice.Model.Post;
import com.example.postservice.Repository.PostRepository;
import com.example.postservice.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

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
    public Post save(Post p, String authorization) throws Exception {
        p.setId(UUID.randomUUID().toString());
        var userId = findCurrentUser(authorization);

        if (userId == null)
            throw new Exception("No user is logged in!");

        p.setUserId(userId);
        p.setDatePosted(LocalDateTime.now());
        p.setUserLikes(new ArrayList<>());
        p.setUserDislikes(new ArrayList<>());

        return p;
    }

    @Override
    public Post saveToDatabase(Post post) {
        return postRepository.save(post);
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

    public String findCurrentUser(String authorization) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            var currentUserId = restTemplate.exchange("http://user-service:8761/api/users/current-user",
                    HttpMethod.GET, entity, String.class);

            System.out.println("OVO JE TRENUTNI USER: " + currentUserId.getBody());
            if (currentUserId.getStatusCode() == HttpStatus.OK)
                return currentUserId.getBody();
        } catch (HttpClientErrorException e) {
            return null;
        }

        return null;
    }

    @Override
    public String uploadImages(MultipartFile file) {
        var imagePath = "";

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

        imagePath = newFile.getAbsolutePath().substring(3);

        return imagePath;
    }

    @Override
    public String getBase64(Post post) throws IOException {
        var path = Paths.get(post.getImagePath());
        return Base64.getEncoder().encodeToString(Files.readAllBytes(path));
    }
}
