package com.epam.esm.service;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.util.EsmPagination;

import java.util.Set;

/**
 * Order service layer.
 */
public interface OrderService extends AbstractService<OrderDto> {
    /**
     * Create order DTO.
     *
     * @param userId            user ID.
     * @param giftCertificateId gift certificate ID.
     * @return Created order DTO.
     */
    OrderDto create(long userId, long giftCertificateId);

    /**
     * Find all orders DTO by user ID.
     *
     * @param userId        User ID.
     * @param esmPagination Pagination entity.
     * @return Set of found users.
     */
    Set<OrderDto> findAllByUserId(long userId, EsmPagination esmPagination);
}