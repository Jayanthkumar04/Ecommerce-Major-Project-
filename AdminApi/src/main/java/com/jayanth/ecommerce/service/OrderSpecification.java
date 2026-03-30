package com.jayanth.ecommerce.service;


import org.springframework.data.jpa.domain.Specification;

import com.jayanth.ecommerce.dto.FilterRequest;
import com.jayanth.ecommerce.entity.Order;

import jakarta.persistence.criteria.Predicate;

public class OrderSpecification {

    public static Specification<Order> filterOrders(FilterRequest filter) {

        return (root, query, cb) -> {

            Predicate predicate = cb.conjunction(); // always true initially

            // 1. Email filter
            if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.like(
                                cb.lower(root.get("email")),
                                "%" + filter.getEmail().toLowerCase() + "%"
                        ));
            }

            // 2. Start Date filter
            if (filter.getStartDate() != null) {
                predicate = cb.and(predicate,
                        cb.greaterThanOrEqualTo(
                                root.get("dateCreated"),
                                filter.getStartDate()
                        ));
            }

            // 3. End Date filter
            if (filter.getEndDate() != null) {
                predicate = cb.and(predicate,
                        cb.lessThanOrEqualTo(
                                root.get("dateCreated"),
                                filter.getEndDate()
                        ));
            }
            
            return predicate;
        };
    }
}