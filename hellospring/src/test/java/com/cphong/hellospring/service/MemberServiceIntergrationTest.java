package com.cphong.hellospring.service;

import com.cphong.hellospring.domain.Member;
import com.cphong.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@Transactional
public class MemberServiceIntergrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

//    @BeforeEach
//    public void beforeEach() throws Exception {
//        memberService = new MemberService(memoryRepository);
//
//    }

    @Test
    public void 회원가입() throws Exception {
    //Given
        Member member = new Member();
        member.setName("spring3");
    //When
        Long saveId = memberService.join(member);
    //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_예약() {

        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");



        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//            memberService.join(member2);
//            fail();
//        }
//        catch(IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
//        }


        //then
    }


    @Test
    public void 중복_회원_예외() throws Exception {
//Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
//When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findOne() {
    }
}
