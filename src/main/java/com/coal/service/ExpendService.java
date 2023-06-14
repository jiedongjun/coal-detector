package com.coal.service;

import com.coal.domain.CoalConf;
import com.coal.domain.Expend;
import com.coal.repository.ExpendRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Expend}.
 */
@Service
@Transactional
public class ExpendService {

    private final Logger log = LoggerFactory.getLogger(ExpendService.class);

    private final ExpendRepository expendRepository;

    public ExpendService(ExpendRepository expendRepository) {
        this.expendRepository = expendRepository;
    }

    /**
     * Save a expend.
     *
     * @param expend the entity to save.
     * @return the persisted entity.
     */
    public Expend save(Expend expend) {
        log.debug("Request to save Expend : {}", expend);
        return expendRepository.save(expend);
    }

    /**
     * Partially update a expend.
     *
     * @param expend the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Expend> partialUpdate(Expend expend) {
        log.debug("Request to partially update Expend : {}", expend);

        return expendRepository
            .findById(expend.getId())
            .map(
                existingExpend -> {
                    if (expend.getPayTime() != null) {
                        existingExpend.setPayTime(expend.getPayTime());
                    }
                    if (expend.getAmount() != null) {
                        existingExpend.setAmount(expend.getAmount());
                    }
                    if (expend.getDirection() != null) {
                        existingExpend.setDirection(expend.getDirection());
                    }
                    if (expend.getPayWay() != null) {
                        existingExpend.setPayWay(expend.getPayWay());
                    }
                    if (expend.getWriter() != null) {
                        existingExpend.setWriter(expend.getWriter());
                    }

                    return existingExpend;
                }
            )
            .map(expendRepository::save);
    }

    /**
     * Get all the expends.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Expend> findAll(Pageable pageable) {
        log.debug("Request to get all Expends");
        return expendRepository.findAll(pageable);
    }

    /**
     * Get one expend by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Expend> findOne(Long id) {
        log.debug("Request to get Expend : {}", id);
        return expendRepository.findById(id);
    }

    /**
     * Delete the expend by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Expend : {}", id);
        expendRepository.deleteById(id);
    }

    public Page<Expend> findByCondition(long start, long stop, String writer, Pageable pageable) {
        Instant start_time = Instant.ofEpochMilli(start).atZone(ZoneId.of("+8")).toInstant();
        Instant stop_time = Instant.ofEpochMilli(stop).atZone(ZoneId.of("+8")).toInstant();
        return expendRepository.findByWriterContainingAndPayTimeBetween(writer, start_time, stop_time, pageable);
    }
}
