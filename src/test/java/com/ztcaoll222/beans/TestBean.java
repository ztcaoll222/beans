package com.ztcaoll222.beans;

import java.util.StringJoiner;

/**
 * @author ztcaoll222
 * @date 2019/11/13 22:38
 */
public class TestBean {
    public int a;

    private Boolean b;

    private boolean c;

    private Byte d;

    private byte e;

    private Short f;

    private short g;

    private Integer h;

    private int i;

    private Long j;

    private long k;

    private Character l;

    private char n;

    private Float m;

    private float o;

    private Double p;

    private double q;

    private Inner r;

    private TestBean setB(Boolean b) {
        this.b = b;
        return this;
    }

    public TestBean setC(boolean c) {
        this.c = c;
        return this;
    }

    private TestBean setD(Byte d) {
        this.d = d;
        return this;
    }

    public TestBean setE(byte e) {
        this.e = e;
        return this;
    }

    private TestBean setF(Short f) {
        this.f = f;
        return this;
    }

    public TestBean setG(short g) {
        this.g = g;
        return this;
    }

    private TestBean setH(Integer h) {
        this.h = h;
        return this;
    }

    public TestBean setI(int i) {
        this.i = i;
        return this;
    }

    private TestBean setJ(Long j) {
        this.j = j;
        return this;
    }

    public TestBean setK(long k) {
        this.k = k;
        return this;
    }

    private TestBean setL(Character l) {
        this.l = l;
        return this;
    }

    public TestBean setN(char n) {
        this.n = n;
        return this;
    }

    private TestBean setM(Float m) {
        this.m = m;
        return this;
    }

    public TestBean setO(float o) {
        this.o = o;
        return this;
    }

    private TestBean setP(Double p) {
        this.p = p;
        return this;
    }

    public TestBean setQ(double q) {
        this.q = q;
        return this;
    }

    public static class Inner {
        private int innerA = 1551;

        @Override
        public String toString() {
            return new StringJoiner(", ", Inner.class.getSimpleName() + "[", "]")
                    .add("innerA=" + innerA)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TestBean.class.getSimpleName() + "[", "]")
                .add("a=" + a)
                .add("b=" + b)
                .add("c=" + c)
                .add("d=" + d)
                .add("e=" + e)
                .add("f=" + f)
                .add("g=" + g)
                .add("h=" + h)
                .add("i=" + i)
                .add("j=" + j)
                .add("k=" + k)
                .add("l=" + l)
                .add("n=" + n)
                .add("m=" + m)
                .add("o=" + o)
                .add("p=" + p)
                .add("q=" + q)
                .add("r=" + r)
                .toString();
    }
}
