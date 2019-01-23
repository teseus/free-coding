package com.teseus.freecoding.tdd.spock.after;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RangeEntityRepository extends JpaRepository<RangeEntity, Long> {
}
