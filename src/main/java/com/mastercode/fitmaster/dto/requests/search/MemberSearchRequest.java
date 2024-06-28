package com.mastercode.fitmaster.dto.requests.search;

import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.model.enums.MemberStatus;
import lombok.Data;

@Data
public class MemberSearchRequest extends SearchRequest {
    private String fullName;
    private Gender gender;
    private MemberStatus status;
}
