package telran.java53.post.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	  String id;
	  String title;
	  String content;
	  String author;
	  LocalDateTime dateCreated;
	  
	  @Singular
	  HashSet<String> tags;
	  Integer likes;
	  
	  @Singular
	  List<CommentDto> comments;
	  
}
