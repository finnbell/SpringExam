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
            member.setType(MemberType.ADMIN);
            member.setAge(10);

            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m from Member m join m. team t"; // inner join
//            String query = "select m from Member m left join m.team t" // left join
//            String query = "select m from Member m, Team t where m.username = t.name";   //cross join
//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";  //join on
//              String query = "select m from Member m left join Team t on m.username = t.name";

            String query = "select m.username , 'HELLO', true From Member m "+
                                "where m.type = :userType";    // jpql.MemberType.ADMIN";

            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType" , MemberType.ADMIN)
                    .getResultList();


            System.out.println("result.size= " + result.size());

            for (Object[] objects : result) {
                System.out.println("objects ="+objects[0]);
                System.out.println("objects ="+objects[1]);
                System.out.println("objects ="+objects[2]);

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