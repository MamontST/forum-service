package telran.java53.post.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java53.post.model.Post;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {

	Stream<Post> getAllBy();
	
}
