package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.delivery.domain.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
