package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team team1 = new Team();
            team1.setName("팀A");
            em.persist(team1);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(team1);

            member1.setType(MemberType.USER);
            member1.setAge(10);

            em.persist(member1);


            Team team2 = new Team();
            team2.setName("팀B");
            em.persist(team2);


            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setType(MemberType.USER);
            member2.setAge(10);
            member2.setTeam(team2);
            em.persist(member2);


            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setType(MemberType.USER);
            member3.setAge(10);
            member3.setTeam(team2);
            em.persist(member3);



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

//            String query = "select nullif(m.username, '관리자')" +
//                           " from Member m ";

//            String query = "select concat('a','b') From Member m";
//            String query = "select substring(m.username,2,3) From Member m";
//            String query = "select locate('de','abcdefgf') From Member m";
//            String query = "select length('abcd') From Member m";
//            String query = "select size(t.members) From Team t";

//            String query = "select function('group_concat', m.username) From Member m"; //사용자 정의 함수
//            String query = "select t.members From Team t";
            //language=JPAQL
            String query = "select distinct  t From Team t  join fetch t.members ";  //join fetch m.team

            List<Team> result = em.createQuery(query, Team.class)
                            .getResultList();


            System.out.println("result.size= " + result.size());

            for (Team mem : result) {
                System.out.println("result = " + mem.getName() + ", " + mem.getMembers() );
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