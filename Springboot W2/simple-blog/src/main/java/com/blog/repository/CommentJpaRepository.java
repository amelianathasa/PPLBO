package com.blog.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.vo.Comment;
import com.blog.vo.Post;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Serializable>{
	Comment findOneById(Long id);
	List<Comment> findByOrderByRegDateDesc();
	List<Comment> findByPostIdAndCommentContaining(Long postId, String query);

}
