package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional  // 이렇게 overriding 해주어야 저장이 된다
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity, String author, String isbn){
        Book findItem = (Book) itemRepository.findOne(itemId);     // 변경 감지 기능 -  Id를 기반으로 실제 영속상태의 엔티티를 찾아옴
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        findItem.setAuthor(author);
        findItem.setIsbn(isbn);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto itemDto){
        Item findItem = itemRepository.findOne(itemId);     // 변경 감지 기능 -  Id를 기반으로 실제 영속상태의 엔티티를 찾아옴
        findItem.setName(itemDto.getName());
        findItem.setPrice(itemDto.getPrice());
        findItem.setStockQuantity(itemDto.getStockQuantity());
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }
}
