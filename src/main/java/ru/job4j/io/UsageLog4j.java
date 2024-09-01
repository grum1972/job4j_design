package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b1 = 127;
        short sh = 512;
        char ch = 72;
        int i = 1;
        long l = 10_000_000_000L;
        boolean isExist = true;
        float a = 123.00F;
        double b = 20;

        LOG.debug("Primitive data type Part 1: byte {}, short : {}, char {}, int : {}", b1, sh, ch, i);
        LOG.debug("Primitive data type Part 2: long {}, boolean : {}, float {}, double : {}", l, isExist, a, b);
    }
}