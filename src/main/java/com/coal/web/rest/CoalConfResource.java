package com.coal.web.rest;

import com.coal.domain.CoalConf;
import com.coal.repository.CoalConfRepository;
import com.coal.service.CoalConfService;
import com.coal.util.ObjectNodeUtil;
import com.coal.web.rest.errors.BadRequestAlertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.coal.domain.CoalConf}.
 */
@RestController
@RequestMapping("/api")
public class CoalConfResource {

    private final Logger log = LoggerFactory.getLogger(CoalConfResource.class);

    private static final String ENTITY_NAME = "coalConf";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoalConfService coalConfService;
    private final CoalConfRepository coalConfRepository;
    private final ObjectMapper objectMapper;

    public CoalConfResource(CoalConfService coalConfService, CoalConfRepository coalConfRepository, ObjectMapper objectMapper) {
        this.coalConfService = coalConfService;
        this.coalConfRepository = coalConfRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * {@code POST  /coal-confs} : Create a new coalConf.
     *
     * @param coalConf the coalConf to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coalConf, or with status {@code 400 (Bad Request)} if the coalConf has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coal-confs")
    public ResponseEntity<CoalConf> createCoalConf(@RequestBody CoalConf coalConf) throws URISyntaxException {
        log.debug("REST request to save CoalConf : {}", coalConf);
        if (coalConf.getId() != null) {
            throw new BadRequestAlertException("A new coalConf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoalConf result = coalConfService.save(coalConf);
        return ResponseEntity
            .created(new URI("/api/coal-confs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /coal-confs/:id} : Updates an existing coalConf.
     *
     * @param id       the id of the coalConf to save.
     * @param coalConf the coalConf to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coalConf,
     * or with status {@code 400 (Bad Request)} if the coalConf is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coalConf couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coal-confs/{id}")
    public ResponseEntity<CoalConf> updateCoalConf(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CoalConf coalConf
    ) throws URISyntaxException {
        log.debug("REST request to update CoalConf : {}, {}", id, coalConf);
        if (coalConf.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coalConf.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coalConfRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CoalConf result = coalConfService.save(coalConf);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coalConf.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /coal-confs/:id} : Partial updates given fields of an existing coalConf, field will ignore if it is null
     *
     * @param id       the id of the coalConf to save.
     * @param coalConf the coalConf to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coalConf,
     * or with status {@code 400 (Bad Request)} if the coalConf is not valid,
     * or with status {@code 404 (Not Found)} if the coalConf is not found,
     * or with status {@code 500 (Internal Server Error)} if the coalConf couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/coal-confs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CoalConf> partialUpdateCoalConf(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CoalConf coalConf
    ) throws URISyntaxException {
        log.debug("REST request to partial update CoalConf partially : {}, {}", id, coalConf);
        if (coalConf.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coalConf.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coalConfRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CoalConf> result = coalConfService.partialUpdate(coalConf);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coalConf.getId().toString())
        );
    }

    /**
     * {@code GET  /coal-confs} : get all the coalConfs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coalConfs in body.
     */
    @GetMapping("/coal-confs")
    public ResponseEntity<List<CoalConf>> getAllCoalConfs(Pageable pageable) {
        log.debug("REST request to get a page of CoalConfs");
        Page<CoalConf> page = coalConfService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/coal-confs/condition")
    public ObjectNode getCoalConfs(
        @RequestParam Long start,
        @RequestParam Long stop,
        @RequestParam String phone,
        @RequestParam String payStatus,
        @RequestParam String send,
        Pageable pageable
    ) {
        ObjectNode result = ObjectNodeUtil.createObjectNode();
        log.debug("REST request to get a page of CoalConfs");
        Page<CoalConf> page = coalConfService.findByCondition(start, stop, phone, send, payStatus, pageable);
        ArrayNode coalArr = ObjectNodeUtil.createArrayNode();
        page
            .getContent()
            .forEach(
                coal -> {
                    coalArr.add(ObjectNodeUtil.convertValue(coal, ObjectNode.class));
                }
            );
        long size = page.getTotalElements();
        int income = 0;
        int havePay = 0;
        int noPay = 0;
        if (page.getContent().size() > 0) {
            Page<CoalConf> totalList = coalConfService.findByCondition(
                start,
                stop,
                phone,
                send,
                payStatus,
                PageRequest.of(0, (int) size, page.getSort())
            );
            for (CoalConf coalConf : totalList.getContent()) {
                if (coalConf.getPrice2() != null) {
                    income += coalConf.getPrice2();
                }
                if (coalConf.getPayStatus() != null && !"".equals(coalConf.getPayStatus())) {
                    if ("NO_PAY".equals(coalConf.getPayStatus())) {
                        if (coalConf.getPrice2() != null) {
                            noPay += coalConf.getPrice2();
                        }
                    } else {
                        if (coalConf.getPrice2() != null) {
                            havePay += coalConf.getPrice2();
                        }
                    }
                }
            }
        }
        result.put("total", size).put("income", income).put("havePay", havePay).put("noPay", noPay).set("list", coalArr);
        return result;
    }

    /**
     * {@code GET  /coal-confs/:id} : get the "id" coalConf.
     *
     * @param id the id of the coalConf to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coalConf, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coal-confs/{id}")
    public ResponseEntity<CoalConf> getCoalConf(@PathVariable Long id) {
        log.debug("REST request to get CoalConf : {}", id);
        Optional<CoalConf> coalConf = coalConfService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coalConf);
    }

    /**
     * {@code DELETE  /coal-confs/:id} : delete the "id" coalConf.
     *
     * @param id the id of the coalConf to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coal-confs/{id}")
    public ResponseEntity<Void> deleteCoalConf(@PathVariable Long id) {
        log.debug("REST request to delete CoalConf : {}", id);
        coalConfService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/coal-confs/delete/{id}")
    public ObjectNode delete(@PathVariable Long id) {
        coalConfService.delete(id);
        Optional<CoalConf> one = coalConfService.findOne(id);
        if (one.isPresent()) {
            return ObjectNodeUtil.createObjectNode().put("res", false);
        }
        return ObjectNodeUtil.createObjectNode().put("res", true);
    }

    @GetMapping("/coal-confs/save")
    public CoalConf save(@RequestParam String coalConf) {
        try {
            CoalConf newCoalConf = objectMapper.readValue(coalConf, CoalConf.class);
            newCoalConf = coalConfService.save(newCoalConf);
            return newCoalConf;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
