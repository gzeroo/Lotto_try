package Lotto.domain;

import Lotto.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

// DTO
    private long id;

    private String memberId;
    // private boolean rememberId;

    private String memberPw;

    //private HashMap<String, Member> memberLikeNumber; // 키: 회원id(memberId), 밸류: 회원 지정 번호 리스트


   // public Member(){}; // @NoArgsConstructor

    public MemberDTO(String memberId, String memberPw){
        this.memberId = memberId;
        this.memberPw = memberPw;
    }


    public static MemberDTO memberDomain(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberDTO.getMemberId());
        memberDTO.setMemberPw(memberDTO.getMemberPw());
        return memberDTO;
    }




}
