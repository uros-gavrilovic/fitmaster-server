package com.mastercode.fitmaster.dto.membership_package;

import java.math.BigDecimal;
import java.util.Currency;

public record PackageDto(
		Long id,
		String name,
		Integer duration,
		BigDecimal price,
		String currency
//		Currency currency
) {}
