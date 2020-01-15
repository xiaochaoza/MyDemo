package com.fzzz.mydemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-14
 * update:
 */
public class UserReturnBean implements Serializable {

    /**
     * resultCode : 0
     * resultMessage : 成功
     * data : [{"id":1,"userName":"rose","passWord":"333"},{"id":10,"userName":"tom","passWord":"11223344"},{"id":11,"userName":"zhangsan","passWord":"123"},{"id":12,"userName":"lisi","passWord":"123"},{"id":15,"userName":"jack1","passWord":"2231"},{"id":16,"userName":"zhuliu","passWord":"3345"}]
     */
    public String resultCode;
    public String resultMessage;
    public List<User> data;

    public class User {
        /**
         * id : 1
         * userName : rose
         * passWord : 333
         */
        public int id;
        public String userName;
        public String passWord;
    }
}
