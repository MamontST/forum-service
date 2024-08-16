package telran.java53.post.service;

import java.util.List;
import java.util.Set;

import telran.java53.forum.dto.DatePeriodDto;
import telran.java53.forum.dto.PostAddDto;
import telran.java53.forum.dto.PostDto;

public interface ForumService {

	PostDto addPost(PostAddDto postAddDto);

	PostDto findPostById(Long postId);

	boolean addLike(Long postId);

	PostDto findPostByAuthor(String user);

	PostDto addComment(Long postId, String user);

	PostDto deletePost(Long postId);

	List<PostDto> findPostByTags(Set<String> tags);

	List<PostDto> findPostByPeriod(DatePeriodDto datePeriodDto);

	PostDto updatePost(Long postId);

}
