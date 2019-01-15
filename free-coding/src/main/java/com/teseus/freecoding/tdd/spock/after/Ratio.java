package com.teseus.freecoding.tdd.spock.after;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Ratio {

    @Id
    private Long id;

    @NotNull
    private double value;

}
