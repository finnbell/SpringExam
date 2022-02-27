package jpabook.jpashop_init;

import jpabook.jpashop_init.domain.Item;
import jpabook.jpashop_init.domain.Member;
import jpabook.jpashop_init.domain.Movie;
import jpabook.jpashop_init.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setCreatedBy("hong");
            member.setCreatedDateTime(LocalDateTime.now());

            em.persist(member);


            em.flush();
            em.clear();

            tx.commit();
        }
        catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }

}