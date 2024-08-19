package telran.java53.post.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	String author;
	String message;
	LocalDateTime dateCreated;
	Integer likes;
	
    public CommentDto(String author, String message) {
        this.author = author;
        this.message = message;
        this.dateCreated = LocalDateTime.now(); 
        this.likes = 0;                         
    }
}
