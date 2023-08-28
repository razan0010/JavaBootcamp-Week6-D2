package com.example.blog.Repository;

import com.example.blog.Model.Blog;

import com.example.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAllByUser(User user);
    Blog findBlogByIdAndUser(Integer id, User user);
    Blog findBlogByTitleAndUser(String title , User user);
}
