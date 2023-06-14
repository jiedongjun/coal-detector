package com.coal.web.rest;

import com.coal.domain.Expend;
import com.coal.repository.ExpendRepository;
import com.coal.service.ExpendService;
import com.coal.util.ObjectNodeUtil;
import com.coal.web.rest.errors.BadRequestAlertException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.coal.domain.Expend}.
 */
@RestController
@RequestMapping("/api")
public class ExpendResource {

    private final Logger log = LoggerFactory.getLogger(ExpendResource.class);

    private static final String ENTITY_NAME = "expend";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExpendService expendService;

    private final ExpendRepository expendRepository;

    public ExpendResource(ExpendService expendService, ExpendRepository expendRepository) {
        this.expendService = expendService;
        this.expendRepository = expendRepository;
    }

    /**
     * {@code POST  /expends} : Create a new expend.
     *
     * @param expend the expend to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new expend, or with status {@code 400 (Bad Request)} if the expend has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/expends")
    public ResponseEntity<Expend> createExpend(@RequestBody Expend expend) throws URISyntaxException {
        log.debug("REST request to save Expend : {}", expend);
        Expend result = expendService.save(expend);
        return ResponseEntity
            .created(new URI("/api/expends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/expends/save")
    public ResponseEntity<Expend> saveExpend(@RequestParam String expend) throws URISyntaxException {
        ObjectNode objectNode = ObjectNodeUtil.stringToJson(expend);
        Expend expend1 = new Expend();
        if (objectNode.has("id")) {
            expend1.setId(objectNode.get("id").asLong());
        }
        expend1.setPayTime(Instant.ofEpochMilli(objectNode.get("payTime").asLong()).atZone(ZoneId.of("+8")).toInstant());
        expend1.setAmount((float) objectNode.get("amount").asDouble());
        expend1.setPayWay(objectNode.get("payWay").asText());
        expend1.setWriter(objectNode.get("writer").asText());
        expend1.setDirection(objectNode.get("direction").asText());
        return this.createExpend(expend1);
    }

    /**
     * {@code PUT  /expends/:id} : Updates an existing expend.
     *
     * @param id the id of the expend to save.
     * @param expend the expend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated expend,
     * or with status {@code 400 (Bad Request)} if the expend is not valid,
     * or with status {@code 500 (Internal Server Error)} if the expend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/expends/{id}")
    public ResponseEntity<Expend> updateExpend(@PathVariable(value = "id", required = false) final Long id, @RequestBody Expend expend)
        throws URISyntaxException {
        log.debug("REST request to update Expend : {}, {}", id, expend);
        if (expend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, expend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!expendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Expend result = expendService.save(expend);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, expend.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /expends/:id} : Partial updates given fields of an existing expend, field will ignore if it is null
     *
     * @param id the id of the expend to save.
     * @param expend the expend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated expend,
     * or with status {@code 400 (Bad Request)} if the expend is not valid,
     * or with status {@code 404 (Not Found)} if the expend is not found,
     * or with status {@code 500 (Internal Server Error)} if the expend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/expends/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Expend> partialUpdateExpend(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Expend expend
    ) throws URISyntaxException {
        log.debug("REST request to partial update Expend partially : {}, {}", id, expend);
        if (expend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, expend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!expendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Expend> result = expendService.partialUpdate(expend);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, expend.getId().toString())
        );
    }

    /**
     * {@code GET  /expends} : get all the expends.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of expends in body.
     */
    @GetMapping("/expends")
    public ResponseEntity<List<Expend>> getAllExpends(Pageable pageable) {
        log.debug("REST request to get a page of Expends");
        Page<Expend> page = expendService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /expends/:id} : get the "id" expend.
     *
     * @param id the id of the expend to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the expend, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/expends/{id}")
    public ResponseEntity<Expend> getExpend(@PathVariable Long id) {
        log.debug("REST request to get Expend : {}", id);
        Optional<Expend> expend = expendService.findOne(id);
        return ResponseUtil.wrapOrNotFound(expend);
    }

    /**
     * {@code DELETE  /expends/:id} : delete the "id" expend.
     *
     * @param id the id of the expend to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/expends/{id}")
    public ResponseEntity<Void> deleteExpend(@PathVariable Long id) {
        log.debug("REST request to delete Expend : {}", id);
        expendService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/expends/delete/{id}")
    public ResponseEntity<Void> delExpend(@PathVariable Long id) {
        return this.deleteExpend(id);
    }

    @GetMapping("/expends/condition")
    public ResponseEntity<List<Expend>> getAllExpendsByCondition(
        @RequestParam String writer,
        @RequestParam long start,
        @RequestParam long end,
        Pageable pageable
    ) {
        log.debug("REST request to get a page of Expends");
        Page<Expend> page = expendService.findByCondition(start, end, writer, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
