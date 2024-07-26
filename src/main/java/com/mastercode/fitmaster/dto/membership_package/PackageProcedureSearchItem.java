package com.mastercode.fitmaster.dto.membership_package;

import java.math.BigDecimal;
import java.util.Currency;

public record PackageProcedureSearchItem (
		Long packageID,
		String name,
		Integer duration,
		BigDecimal price,
		Currency currency
) {}
