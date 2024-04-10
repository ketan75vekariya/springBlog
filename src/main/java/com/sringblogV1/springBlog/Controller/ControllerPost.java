package com.sringblogv1.springblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sringblogv1.springblog.model.Post;
import com.sringblogv1.springblog.services.PostService;



@Controller
public class ControllerPost {
  @Autowired
    private PostService postService;

    @GetMapping("/blog-post/{id}") // Define a path variable named "id"
    public String getPost(@PathVariable Long id, Model model) { // Use @PathVariable to capture the id
        if(id != null){
        Optional<Post> post = postService.getById(id);
        // Check if post is present in Optional
            if (post.isPresent()) {
                model.addAttribute("post", post.get()); // Add the post to the model
                return "view/blog_post";
            } else {
                // Handle case where post is not found
                return "view/404"; // Return an error view
            }
        }else{
            return "view/404";
        }
    }
    @GetMapping("/404")
    public String getPageNotFoundErro(Model model) {
        return "view/404";
    }
    
}
