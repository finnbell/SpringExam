package study.datajpa.repository;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();

    }
}
