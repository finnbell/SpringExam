package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city", "street", "10000번지"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street1", ""));
            member.getAddressHistory().add(new AddressEntity("old2", "street1", ""));
            member.getAddressHistory().add(new AddressEntity("old3", "street1", ""));

            em.persist(member);

            em.flush();
            em.clear();

//            System.out.println("================== START   =================");
//            Member findMember = em.find(Member.class, member.getId());
//
//            //homeCity -> newCity
//            // findMember.getHomeAddress().setCity("newCity");   사용법 에러,  사이드 발생.  값타입 에러..
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            //치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");  //String 은 삭제후   추가해야 함
//            findMember.getFavoriteFoods().add("한식");
//
//
//            findMember.getAddressHistory().remove(new AddressEntity("old1", "street","10000"));
//            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street","10000"));


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
