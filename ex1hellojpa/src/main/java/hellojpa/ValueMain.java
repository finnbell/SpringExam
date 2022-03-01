package hellojpa;

import javax.persistence.criteria.CriteriaBuilder;

public class ValueMain {
    public static void main(String[] args) {

        Integer a = 10;
        Integer b = 10;

        System.out.println("a == b " + (a == b));

        Address address1 = new Address("city","street","10000");
        Address address2 = new Address("city","street","10000");
        System.out.println("address1 == address2 : " + (address1 == address2));
        System.out.println("address1 equals address2 : " + address1.equals(address2)) ;


    }
}
