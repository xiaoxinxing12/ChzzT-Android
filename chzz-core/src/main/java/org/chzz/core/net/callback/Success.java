package org.chzz.core.net.callback;

/**
 * Created by copy on 2017/8/8.
 */

public abstract class Success<T> implements ISuccess<T> {
   public T t;
    public Success() {

    }
    public Success(T t) {
        this.t = t;
    }

    @Override
    public T getEntity() {
        return t;
    }
}
