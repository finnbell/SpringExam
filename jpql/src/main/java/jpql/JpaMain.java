package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);

            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m from Member m join m. team t"; // inner join
//            String query = "select m from Member m left join m.team t" // left join
//            String query = "select m from Member m, Team t where m.username = t.name";   //cross join
//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";  //join on
              String query = "select m from Member m left join Team t on m.username = t.name";

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println("result.size= " + result.size());

            for (Member member1 : result) {

                System.out.println("member =" + member1);

            }




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