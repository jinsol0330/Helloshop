package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

// @RunWith(SpringRunner.class), @SpringBootTest : 이 두가지가 있어야 스프링과 통합해서 테스트를 진행할 수 있음
@RunWith(SpringRunner.class)
@SpringBootTest
// 각 JPA 같은 트랜잭션 안에서 같은 엔티티라면(id값이 똑같다면), 같은 영속성 컨텍스트에서 하나로 관리가 됨
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        // 현재 member와 리포지토리에서 가져온 member가 같은 지 확인
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        // 똑같은 이름을 넣었으므로 예외가 발생해야 한다!
        memberService.join(member2);

        // then
        fail("예외가 발생해야 한다.");

    }
}