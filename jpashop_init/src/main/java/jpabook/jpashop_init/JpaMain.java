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

           Book book = new Book();



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