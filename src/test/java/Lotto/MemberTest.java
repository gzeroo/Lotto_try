package Lotto;

import Lotto.domain.MemberDTO;
import Lotto.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.*; // juit 아님 // 자바에서 제공해주는 juit과 유사한 assertj

import javax.transaction.Transactional;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    public MemberDTO newMember(){
        MemberDTO member = new MemberDTO("테스트용Id", "테스트용PW");
        return member;
    }


    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("회원가입 테스트")
    public void memberSaveTest(){
        Long saveId = memberService.save(newMember());
        MemberDTO member = memberService.findById(saveId);
        assertThat(newMember().getMemberId()).isEqualTo(member.getMemberId()); // 메소드(newMember) 과 member에 저장된 id가 같은지 확인
    }


    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("로그인 테스트")
    public void loginTest(){
        String memberId = "로그인 아이디";
        String memberPw = "로그인 비번";
        MemberDTO member = new MemberDTO(memberId, memberPw);
        Long saveId = memberService.save(member);

        // 로그인 객체 생성 후 로그인
        MemberDTO loginMember = new MemberDTO();
        loginMember.setMemberId(memberId);
        loginMember.setMemberPw(memberPw);
        MemberDTO loginResult = memberService.login(member);

        // 로그인 결과가 not null 이면 테스트 통과
        assertThat(loginResult).isNotNull();
    }

}
