package com.example.blog.Controller;

import com.example.blog.Model.Blog;
import com.example.blog.Model.User;
import com.example.blog.Service.BlogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/blog/")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("get")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @PostMapping("add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user , @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Blog added");
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog, @PathVariable Integer id){
        blogService.updateBlog(user, blog, id);
        return ResponseEntity.status(200).body("Blog updated");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer id){
        blogService.deleteBlog(user, id);
        return ResponseEntity.status(200).body("Blog deleted");
    }

    @GetMapping("get-all-user-blog")
    public ResponseEntity getAllUserBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getAllUserBlogs(user.getId()));
    }

    @GetMapping("get-blog-by-id/{id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user,  @PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(user , id));
    }

    @GetMapping("get-blogs-by-title/{title}")
    public ResponseEntity getAllUserBlogs(@AuthenticationPrincipal User user, @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user, title));
    }
}
