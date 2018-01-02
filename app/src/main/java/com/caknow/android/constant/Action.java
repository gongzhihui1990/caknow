package com.caknow.android.constant;

/**
 * Created by caroline on 2017/12/17.
 */

public enum  Action {
    Register("caknow.intent.action.REGISTER"),
    Main("caknow.intent.action.MAIN");

    public String value;
    Action(String action) {
        value = action;
    }
}
