package com.teseus.exercise;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CurrentMode {
    WAIT_FOR_SIZE("just waiting for inputting the size of the array"),
    WAIT_FOR_CONTENT("just waiting for inputting the main content input");

    private String description;
}
