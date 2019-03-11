package com.haliri.israj.appcore.strategy.content;

/**
 * Created by israjhaliri on 12/6/17.
 */
public interface DeleteDataStrategy<T> {

    void process(T id);
}
