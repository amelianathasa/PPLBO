package com.blog.controller;

//import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.service.CommentService;
import com.blog.vo.Comment;
import com.blog.vo.Result;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService; 
	
	@PostMapping("/comment")
	public Object savePost(jakarta.servlet.http.HttpServletResponse response, @RequestBody Comment commentParam) {
		Comment comment = new Comment(commentParam.getPostId(), commentParam.getUser(), commentParam.getComment());
		boolean isSuccess = commentService.saveComment(comment);
		
		if(isSuccess) {
			return new Result(200, "Success");
		}else {
			response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new Result(500, "Fail");
		}
	}
	
	@GetMapping("/comments")
	public List<Comment> getComments(@RequestParam("post_id") Long postId){
		List<Comment> comments = commentService.getComments(postId);
		return comments;
	}
	
	@GetMapping("/comment")
	public Object getComment(@RequestParam("id") Long id) {
		Comment comment = commentService.getComment(id);
		return comment;
	}
	
	@DeleteMapping("/comment")
	public Object deleteComment(HttpServletResponse response, @RequestParam("id") Long id) {
		boolean isSuccess = commentService.deleteComment(id);
		
//		log.info("id ::: " + id);
		
		if(isSuccess) {
			return new Result(200, "Success");
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new Result(500, "Fail");
		}
	}
	
	@GetMapping("/comments/search")
	public List<Comment> searchComments(@RequestParam("post_id") Long postId, @RequestParam("query") String query) {
	    List<Comment> comments = commentService.searchComments(postId, query);
	    return comments;
	}

	
}
