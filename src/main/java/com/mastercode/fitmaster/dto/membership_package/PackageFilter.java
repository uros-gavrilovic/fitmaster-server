package com.mastercode.fitmaster.dto.membership_package;

import com.mastercode.fitmaster.model.util.PaginationAndSort;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Optional;

@Data
public class PackageFilter extends PaginationAndSort {
	private Optional<String> name = Optional.empty();
	private Optional<Integer[]> duration = Optional.empty();
	private Optional<BigDecimal[]> price = Optional.empty();
	private Optional<String> currency = Optional.empty();
}
