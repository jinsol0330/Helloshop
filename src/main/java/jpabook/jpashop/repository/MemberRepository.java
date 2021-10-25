package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

// @Repository : 컴포넌트 스캔의 대상이 되어 자동으로 스프링 빈에 등록됨
@Repository
// @RequiredArgsConstructor : final이 있는 필드에만 생성자를 만들어 줌
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }
    // 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    // 리스트 조회
    public List<Member> findAll() {
        // JPQL 작성
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        // 파라미터 바인딩
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
