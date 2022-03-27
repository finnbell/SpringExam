package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

//    @GetMapping("/members")   //yml 은 전역,  여기서 page size 는 local
//    public Page<MemberDto> list(@PageableDefault(size=5) Pageable pageable) {
//        Page<Member> page = memberRepository.findAll(pageable)
//
//        //Page 를 그대로 반환 해도 되고,  Dto를 map 을 이용해서 변환후 반환도 가능
//        Page<MemberDto> map = page.map( member -> new MemberDto(member.getId(), member.getUsername(), null));
//
//        return map;
//    }
    // 축약형  Entity 는 내부용이므로, 반드시 Dto 로 변환해서 반환 하도록 함..
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size=5) Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberDto::new);
    }


    @PostConstruct
    public void init() {
        for (int i = 0; i < 100 ; i++) {
            memberRepository.save(new Member("user"+i, i) );
        }


    }

}
