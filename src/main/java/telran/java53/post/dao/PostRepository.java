package telran.java53.post.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java53.post.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {

	Stream<Post> findByAuthorIgnoreCase(String author);
	
	@Query("{'tags': {$in: ?0}}")
	Stream<Post> findByTagsIn(List<String> tags);

	@Query("{'dateCreated': { $gte: ?0, $lte: ?1}}")
	Stream<Post> findByDateCreatedBetween(LocalDateTime localDateTime, LocalDateTime localDateTime2);
	
}
