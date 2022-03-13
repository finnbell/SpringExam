package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.item;
import com.jpabook.jpashop.repository.itemrepository;
import lombok.requiredargsconstructor;
import org.springframework.stereotype.service;
import org.springframework.transaction.annotation.transactional;

import java.util.list;

@service
@transactional(readonly = true)
@requiredargsconstructor
public class itemservice {

    private final itemrepository itemrepository;

    @transactional
    public void saveitem(item item) {
        itemrepository.save(item) ;
    }

    public list<item> finditems() {
        return itemrepository.findall();
    }

    public item findone(long itemid)  {
        return itemrepository.findone(itemid);
    }

}
