package com.sringblogv1.springblog.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.model.Category;
import com.sringblogv1.springblog.model.Post;
import com.sringblogv1.springblog.services.AccountService;
import com.sringblogv1.springblog.services.CategoryService;
import com.sringblogv1.springblog.services.PostService;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class ControllerPost {
  @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/blog-post/{id}") // Define a path variable named "id"
    public String getPost(@PathVariable Long id, Model model, Principal principal) { // Use @PathVariable to capture the id
        if(id != null){
        Optional<Post> post = postService.getById(id);
           // String authUser = "email";

        // Check if post is present in Optional
            if (post.isPresent()) {
                model.addAttribute("post", post.get()); // Add the post to the model
                /*
                if(principal != null){
                    authUser = principal.getName();
                }if (authUser.equals(post.getAccount().getEmail())){
                    model.addAttribute("isOwner", true);
                }else{
                    model.addAttribute("isOwner", false);
                } */



                return "view/blog_post";
            } else {
                // Handle case where post is not found
                return "view/404"; // Return an error view
            }
        }else{
            return "view/404";
        }
    }
    
    @GetMapping("/addblog")
    @PreAuthorize("isAuthenticated()")
        public String addBlog(Model model, Principal principal){
        model.addAttribute("currentPage", "blog");
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        String authUser = "email";
        if(principal !=null){
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if(optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "admin/addblog";
        }else{
            return "redirect:/";
        }    
    }
    @PostMapping("/addblog")
    @PreAuthorize("isAuthenticated()")
    public String addPostHandler(@ModelAttribute Post post, Principal principal){
        String authUser ="email";
        if(principal !=null){
            authUser = principal.getName();
        }
        if(post.getAccount().getEmail().compareToIgnoreCase(authUser) <0){
            return "redirect:/?error";
        }
        postService.save(post);
        return "redirect:/blog-post/" + post.getId();


   }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model) {
        Optional <Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "admin/postedit";
        }else{
            return "/404";
        }
    }
    @PostMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable  Long id, @ModelAttribute Post post){
        Optional <Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postService.save(existingPost);
        }
        return "redirect:/blog-post/"+post.getId();
    }
    
}
