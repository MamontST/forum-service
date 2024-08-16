package telran.java53.post.service;

import java.util.List;
import java.util.Set;

import telran.java53.forum.dto.DatePeriodDto;
import telran.java53.forum.dto.PostAddDto;
import telran.java53.forum.dto.PostDto;

public class ForumServiceImpl implements ForumService {

	@Override
	public PostDto addPost(PostAddDto postAddDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto findPostById(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addLike(Long postId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PostDto findPostByAuthor(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto addComment(Long postId, String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto deletePost(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> findPostByTags(Set<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> findPostByPeriod(DatePeriodDto datePeriod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto updatePost(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
