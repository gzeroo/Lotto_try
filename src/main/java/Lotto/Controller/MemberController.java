package Lotto.Controller;

import Lotto.domain.MemberDTO;
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

//    @PostConstruct // 생성 이후 실행
//    public void iniMember(){ // 이니셜라이징 : 기본 테스트 세팅값
//        memberService.save(new Member(1,"test123", "123456"));
//        memberService.save(new Member(2,"test123", "123456"));
//    }

    @GetMapping("/LottoSignUp")
    public String saveForm(){
        return "/basic/LottoSignUp";
    }

    // 회원 가입
    @PostMapping("/LottoSignUp")
    public String saveMember(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "redirect:/basic/LottoLogin";
    }


    @GetMapping("/LottoLogin")
    public String loginGet(Model model){
        return "/basic/LottoLogin";
    }


    // 로그인 정보 일치 여부 확인
    @PostMapping("/LottoLogin")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            // login 성공
            session.setAttribute("memberId",loginResult.getMemberId());
            session.setAttribute("memberPw",loginResult.getMemberPw());
            return "redirect:/basic/LottoMyPage";
        }else{
            return "redirect:/basic/LottoLogin";
        }
    }

    @GetMapping("/LottoForgotPassword")
    public String updateForm(HttpSession session, Model model){ // 세션에 저장한 값(내 정보)을 가져와서 로그인 id를 db로 부터 가져와서 model에 담아서 비번재설정.html로 이동
        String loginId = (String) session.getAttribute("memberId");
        MemberDTO memberDTO = memberService.updateForm(loginId);
        model.addAttribute("updateMember", memberDTO);
        return "/basic/LottoForgotPassword";
    }

    @PostMapping("/basic/LottoForgotPassword")
    public String update(@ModelAttribute MemberDTO memberDTO){ // 비번 값 받아오기
        memberService.update(memberDTO);
        return "redirect:/basic/LottoLogin";
    }

    @GetMapping("/LottoMyPage")
    public String firstMyPage(){
        return "/basic/LottoMyPage";
    }

}
