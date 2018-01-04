package com.data.www.datasavedemo.interfaces;

/**
 * Created by qwh on 2017/12/4.
 */

public interface DataInterface {
    void success(Object o,String message);

    void error(String message);

    void exception(Exception o,String message);
}
