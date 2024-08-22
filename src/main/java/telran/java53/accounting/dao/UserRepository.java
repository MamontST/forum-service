package telran.java53.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java53.accounting.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
