package com.coal.service;

import com.coal.domain.CoalConf;
import com.coal.repository.CoalConfRepository;
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
 * Service Implementation for managing {@link CoalConf}.
 */
@Service
@Transactional
public class CoalConfService {

    private final Logger log = LoggerFactory.getLogger(CoalConfService.class);

    private final CoalConfRepository coalConfRepository;

    public CoalConfService(CoalConfRepository coalConfRepository) {
        this.coalConfRepository = coalConfRepository;
    }

    /**
     * Save a coalConf.
     *
     * @param coalConf the entity to save.
     * @return the persisted entity.
     */
    public CoalConf save(CoalConf coalConf) {
        log.debug("Request to save CoalConf : {}", coalConf);
        return coalConfRepository.save(coalConf);
    }

    /**
     * Partially update a coalConf.
     *
     * @param coalConf the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CoalConf> partialUpdate(CoalConf coalConf) {
        log.debug("Request to partially update CoalConf : {}", coalConf);

        return coalConfRepository
            .findById(coalConf.getId())
            .map(
                existingCoalConf -> {
                    if (coalConf.getProject() != null) {
                        existingCoalConf.setProject(coalConf.getProject());
                    }
                    if (coalConf.getPhone() != null) {
                        existingCoalConf.setPhone(coalConf.getPhone());
                    }
                    if (coalConf.getSend() != null) {
                        existingCoalConf.setSend(coalConf.getSend());
                    }
                    if (coalConf.getSoft2() != null) {
                        existingCoalConf.setSoft2(coalConf.getSoft2());
                    }
                    if (coalConf.getSoft3() != null) {
                        existingCoalConf.setSoft3(coalConf.getSoft3());
                    }
                    if (coalConf.getPayStatus() != null) {
                        existingCoalConf.setPayStatus(coalConf.getPayStatus());
                    }
                    if (coalConf.getNote1() != null) {
                        existingCoalConf.setNote1(coalConf.getNote1());
                    }
                    if (coalConf.getNote2() != null) {
                        existingCoalConf.setNote2(coalConf.getNote2());
                    }
                    if (coalConf.getCoalType() != null) {
                        existingCoalConf.setCoalType(coalConf.getCoalType());
                    }
                    if (coalConf.getUp_M() != null) {
                        existingCoalConf.setUp_M(coalConf.getUp_M());
                    }
                    if (coalConf.getUp_M1() != null) {
                        existingCoalConf.setUp_M1(coalConf.getUp_M1());
                    }
                    if (coalConf.getUp_M2() != null) {
                        existingCoalConf.setUp_M2(coalConf.getUp_M2());
                    }
                    if (coalConf.getUp_M3() != null) {
                        existingCoalConf.setUp_M3(coalConf.getUp_M3());
                    }
                    if (coalConf.getUp_M4() != null) {
                        existingCoalConf.setUp_M4(coalConf.getUp_M4());
                    }
                    if (coalConf.getUp_A() != null) {
                        existingCoalConf.setUp_A(coalConf.getUp_A());
                    }
                    if (coalConf.getUp_A1() != null) {
                        existingCoalConf.setUp_A1(coalConf.getUp_A1());
                    }
                    if (coalConf.getUp_A2() != null) {
                        existingCoalConf.setUp_A2(coalConf.getUp_A2());
                    }
                    if (coalConf.getUp_A3() != null) {
                        existingCoalConf.setUp_A3(coalConf.getUp_A3());
                    }
                    if (coalConf.getUp_A4() != null) {
                        existingCoalConf.setUp_A4(coalConf.getUp_A4());
                    }
                    if (coalConf.getUp_V() != null) {
                        existingCoalConf.setUp_V(coalConf.getUp_V());
                    }
                    if (coalConf.getUp_V1() != null) {
                        existingCoalConf.setUp_V1(coalConf.getUp_V1());
                    }
                    if (coalConf.getUp_V2() != null) {
                        existingCoalConf.setUp_V2(coalConf.getUp_V2());
                    }
                    if (coalConf.getUp_V3() != null) {
                        existingCoalConf.setUp_V3(coalConf.getUp_V3());
                    }
                    if (coalConf.getUp_V4() != null) {
                        existingCoalConf.setUp_V4(coalConf.getUp_V4());
                    }
                    if (coalConf.getLow_m() != null) {
                        existingCoalConf.setLow_m(coalConf.getLow_m());
                    }
                    if (coalConf.getLow_m1() != null) {
                        existingCoalConf.setLow_m1(coalConf.getLow_m1());
                    }
                    if (coalConf.getLow_m2() != null) {
                        existingCoalConf.setLow_m2(coalConf.getLow_m2());
                    }
                    if (coalConf.getLow_m3() != null) {
                        existingCoalConf.setLow_m3(coalConf.getLow_m3());
                    }
                    if (coalConf.getLow_m4() != null) {
                        existingCoalConf.setLow_m4(coalConf.getLow_m4());
                    }
                    if (coalConf.getEggCone1() != null) {
                        existingCoalConf.setEggCone1(coalConf.getEggCone1());
                    }
                    if (coalConf.getEggCone2() != null) {
                        existingCoalConf.setEggCone2(coalConf.getEggCone2());
                    }
                    if (coalConf.getEggCone3() != null) {
                        existingCoalConf.setEggCone3(coalConf.getEggCone3());
                    }
                    if (coalConf.getPrice1() != null) {
                        existingCoalConf.setPrice1(coalConf.getPrice1());
                    }
                    if (coalConf.getPrice2() != null) {
                        existingCoalConf.setPrice2(coalConf.getPrice2());
                    }

                    return existingCoalConf;
                }
            )
            .map(coalConfRepository::save);
    }

    /**
     * Get all the coalConfs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CoalConf> findAll(Pageable pageable) {
        log.debug("Request to get all CoalConfs");
        return coalConfRepository.findAll(pageable);
    }

    /**
     * Get one coalConf by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CoalConf> findOne(Long id) {
        log.debug("Request to get CoalConf : {}", id);
        return coalConfRepository.findById(id);
    }

    /**
     * Delete the coalConf by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CoalConf : {}", id);
        coalConfRepository.deleteById(id);
    }

    public Page<CoalConf> findByCondition(long start, long stop, String phone, String send, String payStatus, Pageable pageable) {
        Instant start_time = Instant.ofEpochMilli(start).atZone(ZoneId.of("+8")).toInstant();
        Instant stop_time = Instant.ofEpochMilli(stop).atZone(ZoneId.of("+8")).toInstant();
        return coalConfRepository.findBySendContainingAndPayStatusContainingAndPhoneContainingAndCreatedDateBetween(
            send,
            payStatus,
            phone,
            start_time,
            stop_time,
            pageable
        );
    }
}
