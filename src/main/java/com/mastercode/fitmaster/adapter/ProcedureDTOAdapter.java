package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.member.MemberProcedureSearchItem;
import com.mastercode.fitmaster.dto.membership_package.PackageProcedureSearchItem;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;

public class ProcedureDTOAdapter {

	public static MemberProcedureSearchItem mapToMemberProcedureSearchItem(Object[] result) {
		return new MemberProcedureSearchItem(
				(Long) result[0],
				(String) result[1],
				(String) result[2],
				(String) result[3],
				(String) result[4],
				(String) result[5],
				(Date) result[6],
				(String) result[7]
		);
	}

	public static PackageProcedureSearchItem mapToPackageProcedureSearchItem(Object[] result) {
		return new PackageProcedureSearchItem(
				(Long) result[0],
				(String) result[1],
				(Integer) result[2],
				(BigDecimal) result[3],
				Currency.getInstance(result[4].toString())
		);
	}
}
