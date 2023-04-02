package Lotto.web_Controller;

import Lotto.domain.Member;
import Lotto.repository.MemberRepository;
import Lotto.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j// 생성자 주입
@Controller // 뷰네임 불러오기 // 컨트롤 기능 수행 선언
@RequestMapping("/basic")
@RequiredArgsConstructor // 생성자 주입
public class MemberController {

    private final MemberService memberService; // 저장소

    @GetMapping("/LottoSignUp")
    public String saveForm(){
        return "/basic/LottoSignUp";
    }

    // 회원 가입
    @PostMapping("/basic/LottoSignUp")
    public String saveMember(@ModelAttribute Member member){
        memberService.save(member);
        return "/basic/LottoLogin";
    }


    @GetMapping("/LottoLogin")
    public String loginGet(){
        return "/basic/LottoLogin";
    }


    // 로그인 정보 일치 여부 확인
    @PostMapping("/LottoLogin")
    public String login(@ModelAttribute Member member, HttpSession session){
        Member loginResult = memberService.login(member);
        if(loginResult != null){
            session.setAttribute("loginId",loginResult.getMemberId());
            session.setAttribute("loginPw",loginResult.getMemberPw());
            return "/basic/LottoMyPage";
        }else{
            return "/basic/LottoLogin";
        }
    }

//    @PostMapping("/LottoLogin")
//    public String login(@ModelAttribute Member member){
//        boolean loginResult = memberService.login(member);
//        if(loginResult){
//            return "/basic/LottoMyPage";
//        }else{
//            return "/basic/LottoLogin";
//        }
//    }


}
