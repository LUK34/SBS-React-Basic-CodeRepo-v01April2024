package kw.kng.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.UserSetup;

@Repository
public interface UserSetupRepository extends JpaRepository<UserSetup, Long> 
{
	Boolean existsByEmail(String email);
	Optional<UserSetup> findByEmail(String email);

}

/*
 we are using `existsBy` keyword . This is similar to `findBy` methods. 
 The Boolean existsByEmail(String email) is used to block the registeration of the user into the database if the email occurs more than once
 */
