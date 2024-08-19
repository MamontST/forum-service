package telran.java53.post.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java53.post.dto.CommentDto;

@Getter
@Setter
@Document(collection = "posts")
@NoArgsConstructor
public class Post {
	String id;
	String title;
	String content;
	String author;
	LocalDateTime dateCreated = LocalDateTime.now();
	HashSet<String> tags;
	Integer likes = 0;
	List<CommentDto> comments = new ArrayList<>();

	public Post(String id, String title, String content, String author,
			HashSet<String> tags) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.tags = tags;
	}

}
