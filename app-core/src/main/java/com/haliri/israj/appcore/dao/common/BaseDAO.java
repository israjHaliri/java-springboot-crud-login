package com.haliri.israj.appcore.dao.common;

import java.util.List;

/**
 * Created by israjhaliri on 11/21/17.
 */
public interface BaseDAO<T1,T2> {
    
    T1 getData();

    List<T1> getListData();

    T1 getDataById(T2 id);

    T1 getDataByParameters(T2 parameters);

    List<T1> getListDataByParameters(T2 parameters);

    void saveData(T1 parameters);

    void updateData(T1 parameters);

    void saveOrUpdate(T1 parameters);

    void deleteData(T2 id);
}
