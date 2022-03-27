package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;


    @Test
    void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }



    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member( "member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        //단건조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

//        findMember1.setUsername("member!!!!!!!!");

        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);




    }

    @Test
    void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void findHelloBy() {
        List<Member> helloBy = memberRepository.findTop3HelloBy();
    }

    @Test
    void testNamedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");

        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);

    }

    @Test
    void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

//        List<Member> result = memberRepository.findUser("AAA", 10 );
//        assertThat(result.get(0)).isEqualTo(m1);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = "+ s);

        }
    }


    @Test
    void findMemberDto() {

        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10 );
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = "+ dto);

        }

    }

    @Test
    void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA","BBB"));

        for (Member member : result) {
            System.out.println("member = " + member);
        }


    }


    @Test
    void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

//        List<Member> aaa = memberRepository.findListByUsername("AAA");    // 리스트

//        Member findMember = memberRepository.findMemberByUsername("AAA");  //단건
//        System.out.println("findMember= " + findMember);

//        Optional<Member> aaa = memberRepository.findOptionalByUsername("AAA");  // Optional 단건
//        System.out.println("findMember = "+ aaa.get());

//        List<Member> result = memberRepository.findListByUsername("asdfasdf");  // 없는 이름으로 리스트 조회시 null 은 아니고  size 0 임.
//        System.out.println("result = "+ result.size());

//        Member findMember = memberRepository.findMemberByUsername("asdfasf");     // 없는 이름으로 단건 조회시 null 반환
//        System.out.println("findMember = " + findMember);


        Optional<Member> findMember = memberRepository.findOptionalByUsername("asdfasdf");  // 없는 이름으로 조회시 Optional 로 반환
        System.out.println("findMember = " + findMember);



    }



    @Test
    void paging() {

        //given
        memberRepository.save(new Member("member1", 10 ));
        memberRepository.save(new Member("member2", 10 ));
        memberRepository.save(new Member("member3", 10 ));
        memberRepository.save(new Member("member4", 10 ));
        memberRepository.save(new Member("member5", 10 ));


        int age = 10;
        PageRequest pageRequest = PageRequest.of( 0, 3, Sort.by(Sort.Direction.DESC,"username"));

        int offset = 0;
        int limit = 3;

        //when
        Page<Member> page = memberRepository.findByAge(age,pageRequest);  //Slice 타입으로 반환해도 됨. 모바일등에서 주로 사용, 성능향상

        Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername() , null));

        //then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

//        for (Member member : content) {
//            System.out.println("member = " + member);
//        }
//        System.out.println("totalElements = "+ totalElements);

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }


    @Test
    void bulkUpdate() {
        memberRepository.save(new Member("member1", 10 ));
        memberRepository.save(new Member("member2", 19 ));
        memberRepository.save(new Member("member3", 20 ));
        memberRepository.save(new Member("member4", 21 ));
        memberRepository.save(new Member("member5", 40 ));

        //when
        int resultCount = memberRepository.bulkAgePlus(20); // executeUpdate  진행후  반드시 clear 해야 한다. 영속성 컨텍스트 초기화 필요
//        em.clear();


        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0);
        System.out.println("member5 = " + member5);


        //then
        assertThat(resultCount).isEqualTo(3);

    }


    @Test
    void findMemberLazy() {

        //given
        //member1 -> teamA
        //member2 -> teamB

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10 , teamA);
        Member member2 = new Member("member1", 10 , teamA);
        Member member3 = new Member("member2", 10 , teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        em.flush();
        em.clear();

        //when
        //@EntityGraph 없으면  N+1 문제  발생함

//        List<Member> members = memberRepository.findMemberFetchJoin();
//        List<Member> members = memberRepository.findAll();
        List<Member> members = memberRepository.findEntityGraphByUsername("member1");

        for (Member member : members) {
            System.out.println("member = "+ member.getUsername());
            System.out.println("member.teamClass = "+ member.getTeam().getClass());
            System.out.println("member.team = "+ member.getTeam().getName());
        }


    }

    @Test
    void queryHint() {

        //given
        Member member1 = memberRepository.save(new Member("member1", 10));
        memberRepository.save(member1);
        em.flush();
        em.clear();


        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");   //  read only 속성이므로  변경안됨 but 미비한 성능 향상

        em.flush();
    }

    @Test
    void lock() {

        //given
        Member member1 = memberRepository.save(new Member("member1", 10));
        memberRepository.save(member1);
        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");

    }

    @Test
    void callCustom() {
        List<Member> result = memberRepository.findMemberCustom();
    }


    @Test
    void JpaEventBaseEntity() throws Exception {

        Member member = new Member("member1");
        memberRepository.save(member);

        Thread.sleep(100);
        member.setUsername("member2");

        em.flush();
        em.clear();

        //when
        Member findMember = memberRepository.findById(member.getId()).get();

        System.out.println("findMember.createdDate = "+ findMember.getCreatedDate());
        System.out.println("findMember.getLastModifiedDate = "+ findMember.getLastModifiedDate());
        System.out.println("findMember.getCreatedBy = "+ findMember.getCreatedBy());
        System.out.println("findMember.getLastModifiedDate = "+ findMember.getLastModifiedBy());

    }


    @Test
    void queryByExample() {

        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member ("m1", 0, teamA);
        Member m2 = new Member ("m2", 0, teamA);

        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();


        //when
        //Probe

        Member member = new Member("m1");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("age");

        Example<Member> example  = Example.of(member,matcher);

        List<Member> result = memberRepository.findAll(example);

        assertThat(result.get(0).getUsername()).isEqualTo("m1");
    }
}
