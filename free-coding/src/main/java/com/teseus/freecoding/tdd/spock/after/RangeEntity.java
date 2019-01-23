package com.teseus.freecoding.tdd.spock.after;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class RangeEntity {
    @Id
    private Long id;
    @NotNull
    private int value;
}
