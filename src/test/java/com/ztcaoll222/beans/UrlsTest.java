package com.ztcaoll222.beans;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log
class UrlsTest {

    @Test
    void bean2Query() throws IllegalAccessException {
        assertEquals("a=0&b=&c=false&d=&e=0&f=&g=0&h=&i=0&j=&k=0&l=&m=&n=a&o=1.0&p=&q=0.0&r=",
                Urls.bean2Query(new TestBean().setO(1).setN('a')));
    }

    @Test
    void bean2Url() throws IllegalAccessException {
        assertEquals("http://baidu.com?a=0&b=&c=false&d=&e=0&f=&g=0&h=&i=0&j=&k=0&l=&m=&n=a&o=1.0&p=&q=0.0&r=",
                Urls.bean2Url(new TestBean().setO(1).setN('a'), "http://baidu.com"));
    }
}
