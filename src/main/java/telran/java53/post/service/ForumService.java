package telran.java53.post.service;

import java.util.List;

import telran.java53.post.dto.CommentAddDto;
import telran.java53.post.dto.DatePeriodDto;
import telran.java53.post.dto.PostAddDto;
import telran.java53.post.dto.PostDto;

public interface ForumService {

	PostDto addPost(String author, PostAddDto postAddDto);

	PostDto removePost(String postId);

	PostDto updatePost(String postId, PostAddDto postAddDto);

	void addLike(String postId);

	PostDto addComment(String postId, String author, CommentAddDto commentAddDto);

	PostDto findPostById(String postId);

	List<PostDto> findPostByAuthor(String author);

	List<PostDto> findPostByTags(List<String> tags);

	List<PostDto> findPostByPeriod(DatePeriodDto datePeriodDto);

}
