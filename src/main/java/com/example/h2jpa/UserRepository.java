package com.example.h2jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

	List<User> findAllByOrderByNameAsc();

	List<User> findAllByOrderByNameDesc();

}
