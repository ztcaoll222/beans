package com.ztcaoll222.beans;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ztcaoll222
 * @date 2019/11/13 20:39
 */
public abstract class AbstractSetMethod<B> {
    private final AccessibleObject methodOrField;
    private final boolean permissionChanged;
    private boolean isInvoke = false;

    public AbstractSetMethod(AccessibleObject methodOrField, Object o) {
        this.methodOrField = methodOrField;
        if (!this.methodOrField.canAccess(o)) {
            this.methodOrField.setAccessible(true);
            this.permissionChanged = true;
        } else {
            this.permissionChanged = false;
        }
    }

    protected abstract void invoke0(B value) throws IllegalAccessException, InvocationTargetException;

    public synchronized void invoke(B value) throws InvocationTargetException, IllegalAccessException {
        if (!isInvoke) {
            invoke0(value);
            if (permissionChanged) {
                this.methodOrField.setAccessible(false);
            }
            isInvoke = true;
        }
    }
}
