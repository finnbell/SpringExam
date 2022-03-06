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
            member.setUsername("관리자");
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

//            String query = "select m.username , 'HELLO', true From Member m "+
//                                "where m.type = :userType";    // jpql.MemberType.ADMIN";

//            String query = "select "+
//                                " case when m.age <= 10  then '학생요금'" +
//                                "      when m.age <= 60 then '경로요금' " +
//                                " else '일반요금' "+
//                                " end " +
//                            " from Member m";

//            String query = "select coalesce(m.username, '이름 없는 회원') as username " +
//                            " from Member m ";

            String query = "select nullif(m.username, '관리자')" +
                           " from Member m ";

            List<String> result = em.createQuery(query, String.class)
                            .getResultList();


            System.out.println("result.size= " + result.size());

            for (String s : result) {
                System.out.println("s = " + s);
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