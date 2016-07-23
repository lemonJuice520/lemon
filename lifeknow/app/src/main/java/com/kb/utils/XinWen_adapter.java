package com.kb.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class XinWen_adapter {
    public final static int TYPE_putong = 0;
    public final static int type_duotu = 1;

    //判断item的类型
    public static int getType(String skipType) {
        if (skipType == null) {
            return TYPE_putong;
        }
        if (skipType.equals("photoset")) {
            return type_duotu;
        }
        return TYPE_putong;
    }
}
