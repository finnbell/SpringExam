package jpabook.jpashop_init;

import jpabook.jpashop_init.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
//            em.persist(child1);  //cascade 로 인해,  child 는 필요 없음. parent 에서 연쇄적으로 처리 해줌.
//            em.persist(child2);

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