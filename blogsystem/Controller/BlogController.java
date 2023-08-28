package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
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

    @GetMapping("get-all-user-blog/{id}")
    public ResponseEntity getAllUserBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId()));
    }

    @GetMapping("get-blog-by-id/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(id));
    }

    @GetMapping("get-blogs-by-title/{title}")
    public ResponseEntity getAllUserBlogs(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
}
