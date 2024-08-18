package telran.java53.post.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java53.post.dao.PostRepository;
import telran.java53.post.dto.CommentAddDto;
import telran.java53.post.dto.DatePeriodDto;
import telran.java53.post.dto.PostAddDto;
import telran.java53.post.dto.PostDto;
import telran.java53.post.dto.exceptions.PostNotFoundException;
import telran.java53.post.model.Post;


@Component
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

	final PostRepository postRepository;
	final ModelMapper modelMapper;
	
	@Override
	public PostDto addPost(String author, PostAddDto postAddDto) {
		Post post = modelMapper.map(postAddDto, Post.class);
		post.setAuthor(author);
//		post.setLikes(0);
//		post.setDateCreated(LocalDateTime.now());
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String postId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
		if (post.getLikes() == null) {
			post.setLikes(1);
		} else {
			post.setLikes(post.getLikes()+1);
		}
		postRepository.save(post);
	}

	@Override
	public List<PostDto> findPostByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto addComment(String postId, String author, CommentAddDto commentAddDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto removePost(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> findPostByTags(List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> findPostByPeriod(DatePeriodDto datePeriodDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto updatePost(String postId, PostAddDto postAddDto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
