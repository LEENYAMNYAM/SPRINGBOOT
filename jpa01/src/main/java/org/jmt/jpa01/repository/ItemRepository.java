package org.jmt.jpa01.repository;

import org.jmt.jpa01.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
