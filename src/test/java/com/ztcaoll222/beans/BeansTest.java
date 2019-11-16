package com.ztcaoll222.beans;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log
class BeansTest {

    @Test
    void map2Bean() throws Exception {
        Map<String, Object> map = new HashMap<>(20);
        map.put("_a", 0);
        map.put("a", 0);
        map.put("b", Boolean.TRUE);
        map.put("c", false);
        map.put("d", Byte.valueOf("1"));
        map.put("e", (byte) 2);
        map.put("f", Short.valueOf("1"));
        map.put("g", (short) 2);
        map.put("h", Integer.valueOf("1"));
        map.put("i", 2);
        map.put("j", Long.valueOf("1000000000000000000"));
        map.put("k", 2000000000000000000L);
        map.put("l", 'c');
        map.put("n", 'b');
        map.put("m", Float.valueOf("0.01"));
        map.put("o", 0.02F);
        map.put("p", Double.valueOf("0.001"));
        map.put("q", 0.002D);
        map.put("r", new TestBean.Inner());
        assertEquals("TestBean[a=0, b=true, c=false, d=1, e=2, f=1, g=2, h=1, i=2, j=1000000000000000000, k=2000000000000000000, l=c, n=b, m=0.01, o=0.02, p=0.001, q=0.002, r=Inner[innerA=1551]]",
                Beans.map2Bean(map, TestBean.class).toString());
    }

    @Test
    void bean2Map() throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>(20);
        TestBean testBean = new TestBean();
        testBean.setC(false);
        testBean.setN('a');
        assertEquals("{a=0, b=null, c=false, d=null, e=0, f=null, g=0, h=null, i=0, j=null, k=0, l=null, m=null, n=a, o=0.0, p=null, q=0.0, r=null}",
                Beans.bean2Map(testBean, map).toString());
    }
}
