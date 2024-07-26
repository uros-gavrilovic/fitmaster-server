package com.mastercode.fitmaster.dto.member;

import java.util.Date;

public record MemberProcedureSearchItem (
		Long memberID,
		String firstName,
		String lastName,
		String gender,
		String address,
		String phoneNumber,
		Date birthDate,
		String status
) {}
