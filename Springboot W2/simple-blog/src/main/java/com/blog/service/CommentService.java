package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;
import com.blog.vo.Post;

import java.util.List;

@Service
public class CommentService {

	@Autowired
	CommentJpaRepository commentJpaRepository; 
	
	public boolean saveComment(Comment comment) {
		Comment result = commentJpaRepository.save(comment);
		boolean isSuccess = true;
		
		if(result == null) {
			isSuccess = false; 
		}
		
		return isSuccess;
	}
	
	public List<Comment> getComments(Long postId){
		List<Comment> comments = commentJpaRepository.findByOrderByRegDateDesc();
		return comments;
	}
	
	public Comment getComment(Long id) {
	    Comment comment = commentJpaRepository.findOneById(id); 
	    return comment;
	}
	
	public boolean deleteComment(Long id) {
		Comment result = commentJpaRepository.findOneById(id);
		
		if(result == null)
			return false;
		
		commentJpaRepository.deleteById(id);
		return true;
	}
	
	public List<Comment> searchComments(Long postId, String query) {
	    List<Comment> comments = commentJpaRepository.findByPostIdAndCommentContaining(postId, query);
	    return comments;
	}

}
