package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Service : 컴포넌트 스캔의 대상이 되어 자동으로 스프링 빈에 등록됨
@Service
// @Transactional : JPA의 모든 데이터 변경이나 로직들은 가급적 트랜잭션 안에서 실행되어야 함
@Transactional(readOnly = true)
// @RequiredArgsConstructor : final이 있는 필드에만 생성자를 만들어 줌
@RequiredArgsConstructor
public class MemberService {
    // 멤버 리포지토리 가져오기
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    // reaonly=false 로 우선권을 가짐
    @Transactional
    public Long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {

        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {

        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 수정
     */
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
