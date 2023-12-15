package com.sonssoft.consultation.dto.interfaces;

import com.querydsl.core.types.OrderSpecifier;

import java.util.List;

public interface Sort {
    List<OrderSpecifier<?>> getSorts();
}
