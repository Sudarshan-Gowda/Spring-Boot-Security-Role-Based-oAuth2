/**
 * 
 */
package com.star.sud.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.sud.user.model.TUser;

/**
 * @author Sudarshan
 *
 */
@Repository
public interface UserDao extends JpaRepository<TUser, Long> {

	/**
	 * @param username
	 */
	TUser findByUsername(String username);

	/**
	 * @param email
	 */
	TUser findByEmail(String email);

}
