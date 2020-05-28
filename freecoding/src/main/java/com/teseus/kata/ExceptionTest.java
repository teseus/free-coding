package com.teseus.kata;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionTest {
    public static void main(String[] args) {
        try {
            log.info("Request Bulk parameter : {}", 132);
            Preconditions.checkArgument(1 == 2 );

        } catch (Exception e){
            log.error(e.getMessage(), e);
            log.info("============");
            log.error("{}", e);
        }
    }
}


