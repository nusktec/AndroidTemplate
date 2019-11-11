package com.rscbyte.homechurch.https;

public interface Listeners {

    //login interface
    interface IAccount {
        void isLogin(String udata, boolean isData);

        void isRegistered(String udata);
    }

}
