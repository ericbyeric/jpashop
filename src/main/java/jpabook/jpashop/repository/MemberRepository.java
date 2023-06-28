package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
//    private EntityManager em;   // spring이 엔티티 매니져를 만들어서 주입(injection)해준다

    private final EntityManager em;

    public void save(Member member){
        em.persist(member); // 저장
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    // 리스트 조회
    public List<Member> findAll(){
        // JPQL필요
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();// (jpql, 반환타입)
        return result;

    }

    // 이름으로 검색
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
