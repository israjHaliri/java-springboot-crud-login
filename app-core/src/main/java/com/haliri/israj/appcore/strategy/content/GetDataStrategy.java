package com.haliri.israj.appcore.strategy.content;

/**
 * Created by israjhaliri on 12/6/17.
 */
public interface GetDataStrategy<T1,T2> {

    T1 process(T2 parameters);
}
