package com.pts.module.primary.repository;

import com.pts.entity.primary.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
    AccountEntity findOneByUsername(String username);

    AccountEntity findByUsername(String username);
}