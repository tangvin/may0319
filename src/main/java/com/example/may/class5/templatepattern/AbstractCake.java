package com.example.may.class5.templatepattern;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/27   9:35
 * @version: 1.0
 * @modified:
 */
public abstract class AbstractCake {

    protected abstract void shape();

    protected abstract void apply();

    protected abstract void bake();

    /*模板方法*/
    public final void run() {
        this.shape();
        if (shouldApply()) {
            this.apply();
        }
        this.bake();
    }

    protected boolean shouldApply() {
        return true;
    }


}
