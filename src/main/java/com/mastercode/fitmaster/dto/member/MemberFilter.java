package com.mastercode.fitmaster.dto.member;

import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.model.enums.MemberStatus;
import com.mastercode.fitmaster.model.util.PaginationAndSort;
import lombok.Data;
import java.util.Optional;

@Data
public class MemberFilter extends PaginationAndSort {
    private Optional<String> fullName = Optional.empty();
    private Optional<Gender> gender = Optional.empty();
    private Optional<MemberStatus> status = Optional.empty();
}
