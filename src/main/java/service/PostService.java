package service;

import model.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PostService {

    private Map<UUID, Post> posts = new HashMap<>();

    public void doPost(){
        posts.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "Programmer"));
        posts.put(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), new Post(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), "Middle"));
    }

    public Post findPostByUUID(UUID uuid) {
        return posts.get(uuid);
    }
}
