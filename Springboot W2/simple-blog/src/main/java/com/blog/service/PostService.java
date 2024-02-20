package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.repository.PostRepository;
import com.blog.vo.Post;
import com.blog.repository.PostJpaRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository; 
	
	@Autowired
	PostJpaRepository jpaRepository;
	
	public Post getPost(Long id) {
	    Post post = jpaRepository.findOneById(id); 
	    return post;
	}
	
	public List<Post> getPosts(){
		List<Post> posts = jpaRepository.findAllByOrderByUpdtDateDesc();
		return posts;
	}
	
	public List<Post> getPostsOrderByUpdtAsc(){
		List<Post> posts = jpaRepository.findAllByOrderByUpdtDateAsc();
		return posts;
	}
	
	public List<Post> getPostsOrderByRegDesc(){
		List<Post> postList = postRepository.findPostOrderByRegDateDesc();
		return postList;
	}
	
	public List<Post> searchPostByTitle(String query){
		List<Post> posts = jpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
		return posts;
	}
	
	public List<Post> searchPostByContent(String query){
		List<Post> posts = jpaRepository.findByContentContainingOrderByUpdtDateDesc(query);
		return posts;
	}
	
	public boolean savePost(Post post) {
		Post result = jpaRepository.save(post);
		boolean isSuccess = true; 
		
		if(result == null) {
			isSuccess = false; 
		}
		return isSuccess;
	}
	
	public boolean deletePost(Long id) {
		Post result = jpaRepository.findOneById(id);
		
		if(result == null)
			return false;
		
		jpaRepository.deleteById(id);
		return true;
	}
	
	public boolean updatePost(Post post) {
		Post result = jpaRepository.findOneById(post.getId());
		
		if(result == null)
			return false; 
		
		if(!StringUtils.isEmpty(post.getTitle())) {
			result.setTitle(post.getTitle());
		}
		
		if(!StringUtils.isEmpty(post.getContent())) {
			result.setContent(post.getContent());
		}
		
		jpaRepository.save(result);
		
		return true;
	}
	
	public List<Post> searchPostByTitleAndOrder(String query){
		List<Post> posts = jpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
		return posts;
	}
}
