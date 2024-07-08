package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.MemberAdapter;
import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.dto.member.MemberFilter;
import com.mastercode.fitmaster.dto.member.MemberSearchItem;
import com.mastercode.fitmaster.dto.response.SearchResponse;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.exception.UserException;
import com.mastercode.fitmaster.model.MemberEntity;
import com.mastercode.fitmaster.repository.MemberRepository;
import com.mastercode.fitmaster.util.DescriptionUtils;
import com.mastercode.fitmaster.util.JooqUtils;
import jakarta.transaction.Transactional;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.mastercode.fitmaster.service.jooq.tables.Member.MEMBER;
import static com.mastercode.fitmaster.service.jooq.tables.Membership.MEMBERSHIP;
import static org.jooq.impl.DSL.*;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class MemberService implements AbstractService<MemberEntity, MemberDTO> {
    @Autowired
    JooqService jooqService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberAdapter memberAdapter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<MemberEntity> getAll() {
        return memberRepository.findAll();
    }

    @Override
    public MemberEntity findByID(final Long id) {
        return memberRepository.getByMemberID(id);
    }

    @Override
    public List<MemberDTO> getAllDTOs() {
        return memberAdapter.entitiesToDTOs(memberRepository.findAll());
    }

    public SearchResponse<MemberSearchItem> search(MemberFilter filter) {
        List<Condition> conditions = getSearchConditions(filter);
        LocalDate currentDate = LocalDate.now();

        Field<String> membershipStatus = DSL.case_()
                .when(MEMBER.STATUS.eq("PENDING"), "PENDING")
                .when(MEMBER.STATUS.eq("BANNED"), "BANNED")
                .when(MEMBERSHIP.START_DATE.le(currentDate).and(MEMBERSHIP.END_DATE.ge(currentDate)), "ACTIVE").otherwise("INACTIVE")
                .as("status");

        var query = jooqService.getDslContext()
                .select(
                    MEMBER.ID,
                    MEMBER.FIRST_NAME,
                    MEMBER.LAST_NAME,
                    MEMBER.GENDER,
                    MEMBER.ADDRESS,
                    MEMBER.PHONE_NUMBER,
                    MEMBER.BIRTH_DATE,
                    membershipStatus
                )
                .from(MEMBER).leftJoin(MEMBERSHIP).on(MEMBER.ID.eq(MEMBERSHIP.MEMBER_ID))
                .where(conditions);

        return jooqService.search(query, filter, MemberSearchItem.class);
    }

    @Override
    public MemberDTO create(MemberEntity entity) {
        return memberAdapter.entityToDTO(memberRepository.saveAndFlush(entity));
    }

    @Override
    public MemberDTO update(MemberEntity entity) {
        MemberEntity memberEntity = memberRepository.findById(entity.getMemberID())
                .orElseThrow(() -> new UserException("MemberEntity with id " + entity.getMemberID() + " does not exist",
                        HttpStatus.NOT_FOUND));

        System.out.println("Updating Memberentity: " + memberEntity);

        entity.setPassword(memberEntity.getPassword());
        entity.setUsername(memberEntity.getUsername());

        return memberAdapter.entityToDTO(memberRepository.saveAndFlush(entity));
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public Map<String, Object> getMemberStatistics() {
        return null;
        //        return memberRepository.getMemberStatistics();
    }

    public MemberEntity login(UserDTO userDTO) {
        MemberEntity memberEntity = memberRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(
                        () -> new LoginException(DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"),
                                HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(CharBuffer.wrap(userDTO.getPassword()), memberEntity.getPassword())) {
            throw new LoginException(
                    DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (!memberEntity.isEmailVerified()) {
            throw new LoginException(
                    DescriptionUtils.getErrorDescription("EMAIL_NOT_VERIFIED"),
                    HttpStatus.FORBIDDEN
            );
        }

        return memberEntity;
    }

    public MemberEntity registerMember(MemberEntity memberEntity) {
        // TODO: Edit encoding proccess to make it URL friendly.
        Optional<MemberEntity> optionalMember = memberRepository.findByUsername(memberEntity.getUsername());

        if (optionalMember.isPresent())
            throw new RegisterException(DescriptionUtils.getErrorDescription("USERNAME_TAKEN"), HttpStatus.CONFLICT);

        memberEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(memberEntity.getPassword())));

        MemberEntity savedMemberEntity = memberRepository.saveAndFlush(memberEntity);
        savedMemberEntity.setVerificationToken(
                passwordEncoder.encode(CharBuffer.wrap(savedMemberEntity.getMemberID().toString())));

        return savedMemberEntity;
    }

    @Transactional
    public UserDTO findByUsername(String username) {
        MemberEntity memberEntity = memberRepository.findByUsername(username)
                .orElseThrow(
                        () -> new LoginException(DescriptionUtils.getErrorDescription("Unknown memberEntity"),
                        HttpStatus.NOT_FOUND)
                );

        return memberAdapter.entityToDTO(memberEntity);
    }

    public MemberEntity changePassword(Long memberID, String oldPassword, String newPassword) {
        MemberEntity memberEntity = memberRepository.getByMemberID(memberID);

        if (passwordEncoder.matches(CharBuffer.wrap(oldPassword), memberEntity.getPassword())) {
            memberEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(newPassword)));
            return memberRepository.saveAndFlush(memberEntity);
        }

        throw new LoginException(DescriptionUtils.getErrorDescription("WRONG_PASSWORD"), HttpStatus.UNAUTHORIZED);
    }

    public boolean verifyMemberAccount(String token) {
        // TODO: Edit decoding proccess to make it URL friendly.
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedMemberID = new String(decodedBytes, StandardCharsets.UTF_8);

        MemberEntity memberEntity = memberRepository.getByMemberID(Long.parseLong(decodedMemberID));

        if (memberEntity != null) {
            memberEntity.setEmailVerified(true);
            memberRepository.saveAndFlush(memberEntity);
            return true;
        }

        return false;
    }

    private List<Condition> getSearchConditions(MemberFilter filter) {
        List<Condition> conditions = new ArrayList<>();

        JooqUtils.addStringCondition(filter.getFullName(), MEMBER.FIRST_NAME.concat(" ").concat(MEMBER.LAST_NAME), conditions);
        JooqUtils.addStringCondition(filter.getGender(), MEMBER.GENDER, conditions);

        if (filter.getStatus() != null && filter.getStatus().isPresent()) {
            Field<String> membershipStatus = DSL.field(
                    DSL.case_()
                            .when(MEMBER.STATUS.eq("PENDING"), "PENDING")
                            .when(MEMBER.STATUS.eq("BANNED"), "BANNED")
                            .when(MEMBERSHIP.START_DATE.le(LocalDate.now()).and(MEMBERSHIP.END_DATE.ge(LocalDate.now())), "ACTIVE").otherwise("INACTIVE")
            );

            conditions.add(membershipStatus.eq(filter.getStatus().get().toString()));
        }

        return conditions;
    }
}
