package com.mastercode.fitmaster.specifications;

import com.mastercode.fitmaster.dto.requests.search.MemberSearchRequest;
import com.mastercode.fitmaster.model.entities.MemberEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecifications {

    public static Specification<MemberEntity> matchesSearchRequest(MemberSearchRequest searchRequest) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            if (searchRequest.getFullName() != null) {
                predicate = builder.and(predicate, builder.or(
                        builder.like(root.get("firstName"), "%" + searchRequest.getFullName() + "%"),
                        builder.like(root.get("lastName"), "%" + searchRequest.getFullName() + "%")
                ));
            }
            if (searchRequest.getGender() != null) {
                predicate = builder.and(predicate, builder.equal(root.get("gender"), searchRequest.getGender()));
            }
            if (searchRequest.getStatus() != null) {
                predicate = builder.and(predicate, builder.equal(root.get("status"), searchRequest.getStatus()));
            }

            return predicate;
        };
    }
}
