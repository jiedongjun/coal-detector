package com.coal.repository;

import com.coal.domain.CoalConf;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CoalConf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoalConfRepository extends JpaRepository<CoalConf, Long> {
    Page<CoalConf> findBySendContainingAndPayStatusContainingAndPhoneContainingAndCreatedDateBetween(
        String send,
        String payStatus,
        String phone,
        Instant start,
        Instant stop,
        Pageable pageable
    );
}
