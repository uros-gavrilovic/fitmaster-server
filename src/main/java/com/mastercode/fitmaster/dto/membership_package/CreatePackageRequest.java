package com.mastercode.fitmaster.dto.membership_package;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record CreatePackageRequest (
		Long id,

		@NotBlank
		String name,

		@Min(1)
		Integer duration,

		@Min(0)
		BigDecimal price,

		String currency
) {}
