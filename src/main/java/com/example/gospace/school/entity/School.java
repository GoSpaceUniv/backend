package com.example.gospace.school.entity;

import com.example.gospace.common.entity.BaseEntity;
import com.example.gospace.school.dto.SchoolResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "school")
public class School extends BaseEntity {

    @Id
    @Column(name = "SD_SCHUL_CODE", length = 20)
    private String schoolCode; // 행정표준코드 (PK로 사용)

    @Column(name = "ATPT_OFCDC_SC_CODE", length = 20, nullable = false)
    private String atptOfcdcScCode; // 시도교육청코드

    @Column(name = "ATPT_OFCDC_SC_NM", length = 100)
    private String atptOfcdcScNm; // 시도교육청명

    @Column(name = "SCHUL_NM", length = 200)
    private String schoolName; // 학교명

    @Column(name = "ENG_SCHUL_NM", length = 200)
    private String engSchoolName; // 영문학교명

    @Column(name = "SCHUL_KND_SC_NM", length = 50)
    private String schoolKindName; // 학교종류명

    @Column(name = "LCTN_SC_NM", length = 50)
    private String locationName; // 시도명

    @Column(name = "JU_ORG_NM", length = 100)
    private String juOrgNm; // 관할조직명

    @Column(name = "FOND_SC_NM", length = 50)
    private String fondScNm; // 설립명

    @Column(name = "ORG_RDNZC", length = 10)
    private String orgRdnzc; // 도로명우편번호

    @Column(name = "ORG_RDNMA", length = 200)
    private String orgRdnma; // 도로명주소

    @Column(name = "ORG_RDNDA", length = 200)
    private String orgRdnda; // 도로명상세주소

    @Column(name = "ORG_TELNO", length = 50)
    private String orgTelno; // 전화번호

    @Column(name = "HMPG_ADRES", length = 300)
    private String homepage; // 홈페이지주소

    @Column(name = "COEDU_SC_NM", length = 20)
    private String coeduScNm; // 남녀공학구분명

    @Column(name = "ORG_FAXNO", length = 50)
    private String orgFaxno; // 팩스번호

    @Column(name = "HS_SC_NM", length = 50)
    private String hsScNm; // 고등학교구분명

    @Column(name = "INDST_SPECL_CCCCL_EXST_YN", length = 1)
    private String indstSpeclClassExistYn; // 산업체특별학급존재여부 (Y/N)

    @Column(name = "HS_GNRL_BUSNS_SC_NM", length = 50)
    private String hsGnrlBusnsScNm; // 고등학교일반전문구분명

    @Column(name = "SPCLY_PURPS_HS_ORD_NM", length = 100)
    private String spclyPurpsHsOrdNm; // 특수목적고등학교계열명

    @Column(name = "ENE_BFE_SEHF_SC_NM", length = 50)
    private String eneBfeSehfScNm; // 입시전후기구분명

    @Column(name = "DGHT_SC_NM", length = 20)
    private String dghtScNm; // 주야구분명

    @Column(name = "FOND_YMD", length = 20)
    private String fondYmd; // 설립일자

    @Column(name = "FOAS_MEMRD", length = 20)
    private String foasMemrd; // 개교기념일

    @Column(name = "LOAD_DTM", length = 20)
    private String loadDtm; // 수정일자

    public static School mapToEntity(String[] data) {
        return School.builder()
            .atptOfcdcScCode(data[0])
            .atptOfcdcScNm(data[1])
            .schoolCode(data[2])
            .schoolName(data[3])
            .engSchoolName(data[4])
            .schoolKindName(data[5])
            .locationName(data[6])
            .juOrgNm(data[7])
            .fondScNm(data[8])
            .orgRdnzc(data[9])
            .orgRdnma(data[10])
            .orgRdnda(data[11])
            .orgTelno(data[12])
            .homepage(data[13])
            .coeduScNm(data[14])
            .orgFaxno(data[15])
            .hsScNm(data[16])
            .indstSpeclClassExistYn(data[17])
            .hsGnrlBusnsScNm(data[18])
            .spclyPurpsHsOrdNm(data[19])
            .eneBfeSehfScNm(data[20])
            .dghtScNm(data[21])
            .fondYmd(data[22])
            .foasMemrd(data[23])
            .loadDtm(data[24])
            .build();
    }

    public SchoolResponseDto toResponse() {
        return new SchoolResponseDto(this.schoolName, this.orgRdnda);
    }

}
