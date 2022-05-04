package com.example.testtask.repository;

import com.example.testtask.domain.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface ClanRepository extends JpaRepository<Clan, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Clan c where c.id = :id")
    Optional<Clan> findOneForUpdate(@Param("id") Long id);

}
