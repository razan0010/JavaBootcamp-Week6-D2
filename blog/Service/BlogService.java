package com.example.blog.Service;

import com.example.blog.API.ApiException;
import com.example.blog.Model.Blog;
import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import com.example.blog.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public void addBlog(Integer user_id, Blog blog){
        User user = authRepository.findUserById(user_id);

        blog.setUser(user);
        blogRepository.save(blog);

    }

    public void updateBlog(User user , Blog blog , Integer blog_id){

        Blog blog1 = blogRepository.findBlogByIdAndUser(blog_id , user);

        if (blog1 == null){
            throw new ApiException("Not found");
        }

        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blog1.setUser(user);
        blogRepository.save(blog1);
    }

    public void deleteBlog(User user , Integer blog_id){
        Blog blog = blogRepository.findBlogByIdAndUser(blog_id , user);

        if (blog == null){
            throw new ApiException("Not found");
        }

        blogRepository.delete(blog);

    }

//Get All user blogs
    public List<Blog> getAllUserBlogs(Integer user_id){
        User user = authRepository.findUserById(user_id);

        if (user == null){
            throw new ApiException("Not found");
        }

        return blogRepository.findAllByUser(user);
    }

//Get blog by Id
    public Blog getBlogById(User user , Integer blog_id){
        return blogRepository.findBlogByIdAndUser(blog_id , user);
    }

//Get blog by title
    public Blog getBlogByTitle(User user , String title){
        return blogRepository.findBlogByTitleAndUser(title, user);
    }

}
