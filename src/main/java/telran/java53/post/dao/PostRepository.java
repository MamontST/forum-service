package telran.java53.post.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java53.post.model.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {

	Stream<Post> findByAuthorIgnoreCase(String author);
	
//	@Query("{'tags': {$in: ?0}}")
	Stream<Post> findByTagsInIgnoreCase(List<String> tags);

//	@Query("{'dateCreated': { $gte: ?0, $lte: ?1}}")
	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
	
}
