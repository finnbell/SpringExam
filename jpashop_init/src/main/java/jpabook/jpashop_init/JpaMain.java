package jpabook.jpashop_init;

import jpabook.jpashop_init.domain.*;

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

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();


            Member ref = em.getReference(Member.class, member1.getId());
            System.out.println("ref = " + ref.getClass());


            Member find = em.find(Member.class, member1.getId());
            System.out.println("find = "  + find.getClass());

            System.out.println("a == a " + (find == ref));
//            Member m2 = em.find(Member.class, member2.getId());
//            Member m2 = em.getReference(Member.class, member2.getId());

//            System.out.println("m1 == m2 : " +   (m1.getClass() == m2.getClass() ) );


            tx.commit();
        }
        catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }


    public static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());

    }

    public static void printMemberAndTeam(Member member) {

        String username = member.getUsername();
        System.out.println("username = "+ username);

        Team team = member.getTeam();
        System.out.println("team = "+ team.getName());

    }

}