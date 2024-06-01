package com.socialnetwork.sg.repository;

import com.socialnetwork.sg.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findPostByCreatedBy_Id(Integer userid);
}
