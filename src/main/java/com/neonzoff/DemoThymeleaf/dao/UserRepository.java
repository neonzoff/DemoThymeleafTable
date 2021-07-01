package com.neonzoff.DemoThymeleaf.dao;

import com.neonzoff.DemoThymeleaf.model.UserModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tseplyaev Dmitry
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserModel, Long> {
}
