package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.PackageEntity;
import jakarta.transaction.Transactional;
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

	@Transactional
	@Query(
			value = "CALL create_package(:name, :duration, :price, :currency, null)",
			nativeQuery = true
	)
	Object createProcedure(
			@Param("name") String name,
			@Param("duration") Integer duration,
			@Param("price") BigDecimal price,
			@Param("currency") String currency
	);

	@Transactional
	@Query(
			value = "CALL update_package(:package_id, :name, :duration, :price, :currency, null)",
			nativeQuery = true
	)
	Object updateProcedure(
			@Param("package_id") Long id,
			@Param("name") String name,
			@Param("duration") Integer duration,
			@Param("price") BigDecimal price,
			@Param("currency") String currency
	);

	@Transactional
	@Procedure(procedureName = "delete_package", outputParameterName = "o_package_id")
	Long deleteProcedure(
			@Param("p_package_id") Long id
	);

	@Transactional
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
