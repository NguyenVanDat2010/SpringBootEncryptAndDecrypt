package com.example.demo.pojo;

public interface Convertable<SOURCE, TARGET> {
    TARGET convertToTarget(SOURCE source);

    default SOURCE convertToSource(TARGET target){
        return null;
    }
}
