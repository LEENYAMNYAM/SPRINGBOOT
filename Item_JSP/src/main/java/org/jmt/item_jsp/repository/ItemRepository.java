package org.jmt.item_jsp.repository;

import org.jmt.item_jsp.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
