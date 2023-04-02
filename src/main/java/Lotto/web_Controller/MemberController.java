package Lotto.web_Controller;

import Lotto.domain.Member;
import Lotto.repository.MemberRepository;
import Lotto.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/basic/LottoSignUp")
    public String saveMember(@ModelAttribute Member member){
        memberService.save(member);
        return "/basic/LottoLogin";
    }


    /*
    @GetMapping()
    public String member(Model model){
        return "/basic/LottoLogin";
    }

    @PostMapping() // 계정&비밀번호 일치할 경우
    public String loginMember(@PathVariable long id,
                              Model model){
        Member member = memberRepository.findById(id);
        model.addAttribute("member", member);
        return "/basic/LottoMyPage";
    }

    @GetMapping("/basic/LottoForgotPassword")
    public String Singup(Model model){
        return "/basic/LottoForgotPassword";
    }

    @PostMapping("/basic/LottoForgotPassword")
    public String singUpMember(){
        return null;
    }

     */

}
