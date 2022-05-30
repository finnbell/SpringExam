package springcore.hello.member;

import java.awt.*;

public class Member {

    private Long id;
    private String name;
    private Grade grade;


    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }
}
