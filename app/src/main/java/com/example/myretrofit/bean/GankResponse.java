package com.example.myretrofit.bean;

import java.io.Serializable;
public class GankResponse<T> implements Serializable {
    private static final long serialVersionUID = -686453405647539973L;

    public boolean error;
    public T results;
}
