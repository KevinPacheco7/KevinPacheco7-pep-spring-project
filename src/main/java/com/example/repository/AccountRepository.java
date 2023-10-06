package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account WHERE account_id = :idVar",nativeQuery = true )
    Account findAccountByIdAccount(@Param("idVar") int id);

    @Query(value = "SELECT * FROM account WHERE username = :userVar",nativeQuery = true)
    Account findAccountByUser(@Param("userVar") String user);
}
