package com.lfm.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
@Slf4j
public class OptionalTest {
    public static void main(String[] args) {
        String s = Optional.ofNullable("sd").orElseGet(OptionalTest::getString);
        log.info(s);
        String s1 = Optional.ofNullable("sd").orElse(getString());
        log.info(s1);
    }

    public static String getString(){
        log.info("invoke getString method");
        return "the default value";
    }
}
