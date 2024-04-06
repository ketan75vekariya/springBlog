package com.sringblogv1.springblog.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sringblogv1.springblog.model.Post;
import com.sringblogv1.springblog.services.PostService;
@Component
public class SeedData implements CommandLineRunner {
  @Autowired
  private PostService postService;
  @Override
  public void run(String... args) throws Exception {
    List<Post> posts= postService.getAll();
    if(posts.size() == 0){
      Post post01 = new Post();
      post01.setTitle("Post 01 Lorem ipsum dolor sit amet consectetur adipisicing elit.");
      post01.setBody("Post01 body Expedita doloremque praesentium corrupti harum ipsum commodi. Id est maxime a magni nulla eligendi odit possimus, eos saepe tenetur explicabo eveniet eius?...........................");
      postService.save(post01);

      Post post02 = new Post();
      post02.setTitle("Post02 Expedita doloremque praesentium corrupti harum ipsum");
      post02.setBody("Post01 body Expedita doloremque praesentium corrupti harum ipsum commodi. Id est maxime a magni nulla eligendi odit possimus, eos saepe tenetur explicabo eveniet eius?......................................................");
      postService.save(post02);
    }
  }
  
}
