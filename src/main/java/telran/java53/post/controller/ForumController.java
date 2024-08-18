package telran.java53.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java53.post.dto.CommentAddDto;
import telran.java53.post.dto.DatePeriodDto;
import telran.java53.post.dto.PostAddDto;
import telran.java53.post.dto.PostDto;
import telran.java53.post.service.ForumService;

@RestController
@RequiredArgsConstructor
public class ForumController {

	final ForumService forumService;

	@PostMapping("/forum/post/{user}")
	public PostDto addPost(@PathVariable("user") String author, @RequestBody PostAddDto postAddDto) {
		return forumService.addPost(author, postAddDto);
	}

	@DeleteMapping("/forum/post/{postId}")
	public PostDto removePost(@PathVariable String postId) {
		return forumService.removePost(postId);
	}

	@PutMapping("/forum/post/{postId}")
	public PostDto updatePost(@PathVariable String postId, @RequestBody PostAddDto postAddDto) {
		return forumService.updatePost(postId, postAddDto);
	}

	@PutMapping("/forum/post/{postId}/like")
	public void addLike(@PathVariable String postId) {
		forumService.addLike(postId);
	}

	@PutMapping("/forum/post/{postId}/comment/{user}")
	public PostDto addComment(@PathVariable String postId, @PathVariable("user") String author, @RequestBody CommentAddDto commentAddDto) {
		return forumService.addComment(postId, author, commentAddDto);
	}

	@GetMapping("/forum/post/{postId}")
	public PostDto findPostById(@PathVariable String postId) {
		return forumService.findPostById(postId);
	}

	@GetMapping("/forum/posts/author/{user}")
	public List<PostDto> findPostByAuthor(@PathVariable("user") String author) {
		return forumService.findPostByAuthor(author);
	}

	@PostMapping("/forum/posts/tags")
	public List<PostDto> findPostByTags(List<String> tags) {
		return forumService.findPostByTags(tags);
	}

	@PostMapping("/posts/period")
	public List<PostDto> findPostByPeriod(@RequestBody DatePeriodDto datePeriodDto) {
		return forumService.findPostByPeriod(datePeriodDto);
	}

}
