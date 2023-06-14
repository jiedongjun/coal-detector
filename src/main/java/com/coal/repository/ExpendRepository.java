package com.coal.repository;

import com.coal.domain.Expend;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Expend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExpendRepository extends JpaRepository<Expend, Long> {
    Page<Expend> findByWriterContainingAndPayTimeBetween(String writer, Instant start, Instant end, Pageable pageable);
}
