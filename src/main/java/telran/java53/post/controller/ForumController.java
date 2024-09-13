	package telran.java53.post.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
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
	public List<PostDto> findPostsByAuthor(@PathVariable("user") String author) {
		return forumService.findPostsByAuthor(author);
	}

//	@PostMapping("/forum/posts/tags")
	@GetMapping("/forum/posts/tags")
//	public List<PostDto> findPostByTags(@RequestBody List<String> tags) {
	public List<PostDto> findPostsByTags(@RequestParam List<String> values) {
//		return forumService.findPostByTags(tags);
		return forumService.findPostsByTags(values);
	}

//	@PostMapping("/forum/posts/period")
	@GetMapping("/forum/posts/period")
//	public List<PostDto> findPostByPeriod(@RequestBody DatePeriodDto datePeriodDto) {
	public List<PostDto> findPostsByPeriod(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
		return forumService.findPostsByPeriod(new DatePeriodDto(dateFrom,dateTo));
	}

}
