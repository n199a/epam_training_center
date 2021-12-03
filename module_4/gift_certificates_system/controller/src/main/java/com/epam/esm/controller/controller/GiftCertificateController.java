package com.epam.esm.controller.controller;

import com.epam.esm.controller.util.hateoas.LinkBuilder;
import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.GiftCertificateUpdateDto;
import com.epam.esm.model.pojo.GiftCertificateSearchParameter;
import com.epam.esm.model.util.MessagePropertyKey;
import com.epam.esm.model.util.UrlMapping;
import com.epam.esm.repository.util.EsmPagination;
import com.epam.esm.service.GitCertificateService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Gift certificate controller.
 * Working with the gift certificate PROJECT layer.
 */
@RestController
@RequestMapping(value = UrlMapping.GIFT_CERTIFICATES)
@Validated
public class GiftCertificateController {
    private final GitCertificateService service;
    private final LinkBuilder<GiftCertificateDto> linkBuilder;

    @Autowired
    public GiftCertificateController(GitCertificateService service, LinkBuilder<GiftCertificateDto> linkBuilder) {
        this.service = service;
        this.linkBuilder = linkBuilder;
    }

    /**
     * Create gift certificate entity.
     *
     * @param gcDto - Gift certificate DTO.
     * @return - Extension of HttpEntity that adds an HttpStatus status code.
     * Used in RestTemplate as well as in @Controller methods.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<GiftCertificateDto> create(@Valid @RequestBody GiftCertificateDto gcDto) {
        GiftCertificateDto certificate = service.create(gcDto);
        linkBuilder.build(certificate);
        return EntityModel.of(certificate);
    }

    /**
     * Finding gift certificate by it is ID.
     *
     * @param id Gift certificate ID.
     * @return Entity of the gift certificate.
     */
    @GetMapping(path = UrlMapping.ID)
    @ResponseStatus(HttpStatus.FOUND)
    public EntityModel<GiftCertificateDto> findById(@Min(value = 1, message = MessagePropertyKey.VALIDATION_ID)
                                                    @PathVariable long id) {
        GiftCertificateDto certificate = service.findById(id);
        linkBuilder.build(certificate);
        return EntityModel.of(certificate);
    }

    /**
     * Find all gift certificates DTO.
     *
     * @param pagination Pagination parameters.
     * @return Set of found gift certificates DTO.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Page<GiftCertificateDto> findAll(@Valid EsmPagination pagination,
                                            @Valid GiftCertificateSearchParameter searchParameter) {
        return ObjectUtils.isEmpty(searchParameter)
                ? service.findAll(pagination)
                : service.findAll(pagination, searchParameter);
    }

    /**
     * Update gift certificate by it is ID.
     *
     * @param id          Gift certificate ID.
     * @param gcUpdateDto Gift certificate DTO.
     */
    @PatchMapping(path = UrlMapping.ID)
    public EntityModel<GiftCertificateDto> update(@PathVariable long id,
                                                  @Valid @RequestBody GiftCertificateUpdateDto gcUpdateDto) {
        GiftCertificateDto certificate = service.update(id, gcUpdateDto);
        linkBuilder.build(certificate);
        return EntityModel.of(certificate);
    }

    /**
     * Delete gift certificate by it is ID.
     *
     * @param id Gift certificate ID.
     */
    @DeleteMapping(path = UrlMapping.ID)
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}