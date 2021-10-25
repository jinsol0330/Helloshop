package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // 처음 저장할 때는 아이템의 id가 존재하지 않으므로 이 부분을 고려해야 함
        if (item.getId() == null) {
            // 아이템 그냥 저장
            em.persist(item);
        } else {
            // 아이템 업데이트(?) - 나중에 자세히 배움
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        // 여러 개 찾는 것은 JPQL을 작성해야 함
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
