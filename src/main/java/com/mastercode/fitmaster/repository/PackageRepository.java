package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
	PackageEntity getByPackageID(Long id);

	@Procedure(procedureName = "create_package", outputParameterName = "p_package_id")
	Long createProcedure(
			@Param("p_name") String name,
			@Param("p_duration") Integer duration,
			@Param("p_price") BigDecimal price,
			@Param("p_currency") String currency
	);

	@Procedure(procedureName = "update_package", outputParameterName = "o_package_id")
	Long updateProcedure(
			@Param("p_package_id") Long id,
			@Param("p_name") String name,
			@Param("p_duration") Integer duration,
			@Param("p_price") BigDecimal price,
			@Param("p_currency") String currency
	);

	@Procedure(procedureName = "delete_package")
	void deleteProcedure(
			@Param("p_package_id") Long id
	);

	@Query(
			value = "SELECT * FROM search_packages(:pageSize, :offset, :name, CAST(:duration AS INTEGER[]), CAST(:price AS NUMERIC[]), :currency, :sortField, :sortDirection)",
			nativeQuery = true
	)
	List<Object[]> searchProcedure(
			@Param("pageSize") Integer pageSize,
			@Param("offset") Long offset,
			@Param("name") String name,
			@Param("duration") Integer[] duration,
			@Param("price") BigDecimal[] price,
			@Param("currency") String currency,
			@Param("sortField") String sortField,
			@Param("sortDirection") String sortDirection
	);
}
