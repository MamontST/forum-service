package telran.java53.post.dto;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostAddDto {
	String title;
	String content;
	
	@Singular
	HashSet<String> tags;
}
