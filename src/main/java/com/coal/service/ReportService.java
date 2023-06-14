package com.coal.service;

import com.coal.domain.Report;
import com.coal.repository.ReportRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Report}.
 */
@Service
@Transactional
public class ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Save a report.
     *
     * @param report the entity to save.
     * @return the persisted entity.
     */
    public Report save(Report report) {
        log.debug("Request to save Report : {}", report);
        return reportRepository.save(report);
    }

    /**
     * Partially update a report.
     *
     * @param report the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Report> partialUpdate(Report report) {
        log.debug("Request to partially update Report : {}", report);

        return reportRepository
            .findById(report.getId())
            .map(
                existingReport -> {
                    if (report.getCoalConfId() != null) {
                        existingReport.setCoalConfId(report.getCoalConfId());
                    }
                    if (report.getPhone() != null) {
                        existingReport.setPhone(report.getPhone());
                    }
                    if (report.getCoalType() != null) {
                        existingReport.setCoalType(report.getCoalType());
                    }
                    if (report.getCheckDate() != null) {
                        existingReport.setCheckDate(report.getCheckDate());
                    }
                    if (report.getUp_M4() != null) {
                        existingReport.setUp_M4(report.getUp_M4());
                    }
                    if (report.getUp_A4() != null) {
                        existingReport.setUp_A4(report.getUp_A4());
                    }
                    if (report.getUp_V4() != null) {
                        existingReport.setUp_V4(report.getUp_V4());
                    }
                    if (report.getUp_S() != null) {
                        existingReport.setUp_S(report.getUp_S());
                    }
                    if (report.getUp_C() != null) {
                        existingReport.setUp_C(report.getUp_C());
                    }
                    if (report.getReport1() != null) {
                        existingReport.setReport1(report.getReport1());
                    }
                    if (report.getReport2() != null) {
                        existingReport.setReport2(report.getReport2());
                    }
                    if (report.getReport3() != null) {
                        existingReport.setReport3(report.getReport3());
                    }
                    if (report.getEggCone2() != null) {
                        existingReport.setEggCone2(report.getEggCone2());
                    }
                    if (report.getUp_Aar() != null) {
                        existingReport.setUp_Aar(report.getUp_Aar());
                    }
                    if (report.getUp_Ad() != null) {
                        existingReport.setUp_Ad(report.getUp_Ad());
                    }
                    if (report.getUp_Var() != null) {
                        existingReport.setUp_Var(report.getUp_Var());
                    }
                    if (report.getUp_Vd() != null) {
                        existingReport.setUp_Vd(report.getUp_Vd());
                    }
                    if (report.getUp_Vdaf() != null) {
                        existingReport.setUp_Vdaf(report.getUp_Vdaf());
                    }
                    if (report.getUp_H() != null) {
                        existingReport.setUp_H(report.getUp_H());
                    }
                    if (report.getSlime() != null) {
                        existingReport.setSlime(report.getSlime());
                    }
                    if (report.getCleanCoal() != null) {
                        existingReport.setCleanCoal(report.getCleanCoal());
                    }
                    if (report.getGanGue() != null) {
                        existingReport.setGanGue(report.getGanGue());
                    }
                    if (report.getNote() != null) {
                        existingReport.setNote(report.getNote());
                    }

                    return existingReport;
                }
            )
            .map(reportRepository::save);
    }

    /**
     * Get all the reports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Report> findAll(Pageable pageable) {
        log.debug("Request to get all Reports");
        return reportRepository.findAll(pageable);
    }

    /**
     * Get one report by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Report> findOne(Long id) {
        log.debug("Request to get Report : {}", id);
        return reportRepository.findById(id);
    }

    /**
     * Delete the report by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Report : {}", id);
        reportRepository.deleteById(id);
    }

    public Report findByCoalConfId(long coalConfId) {
        return reportRepository.findByCoalConfId(coalConfId);
    }
}
