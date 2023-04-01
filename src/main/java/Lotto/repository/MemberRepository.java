package Lotto.repository;

import Lotto.domain.Member;

public interface MemberRepository {

    Member save(Member member); // 회원 정보 저장

    Member findById(long id); // 회원 고유 번호 찾기

    Member findByName(String memberId); // 회원 이름(계정) 찾기, 마이페이지 로그인 이름 확인용



}
