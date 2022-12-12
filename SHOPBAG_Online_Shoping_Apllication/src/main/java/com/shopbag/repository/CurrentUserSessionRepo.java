package com.shopbag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopbag.model.CurrentUserSession;

public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {
	
	CurrentUserSession findByUuid(String uuid);

}
