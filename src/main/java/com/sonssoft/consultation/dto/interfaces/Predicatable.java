package com.sonssoft.consultation.dto.interfaces;

import com.querydsl.core.types.Predicate;

import java.util.List;

public interface Predicatable {

    List<Predicate> getPredicates();
}
