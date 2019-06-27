package com.rnview;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MyViewManager extends SimpleViewManager<View> {
    private static final String EVENT_NAME_ONCLICK = "onClick";

    @Nonnull
    @Override
    public String getName() {
        return "MyView";
    }

    @Nonnull
    @Override
    protected View createViewInstance(@Nonnull ThemedReactContext reactContext) {
        MyView myView = new MyView(reactContext);
//        TextView tv=new TextView(reactContext);
//        tv.setText("1212321423542352346432634");
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建数据传递信使，类似于Android中的Bundle
                WritableMap data = Arguments.createMap();
                data.putString("msg", "点击了控件");// key用于在RN中获取传递的数据
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        myView.getId(), // RN层原生层根据id绑定在一起
                        EVENT_NAME_ONCLICK, // 事件名称
                        data // 传递的数据
                );
            }
        });
        return myView;
    }

    @ReactProp(name = "text")
    public void setText(MyView my, String text) {
        my.setText(text);
        Log.e("LOG", text);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(EVENT_NAME_ONCLICK, MapBuilder.of("registrationName", EVENT_NAME_ONCLICK));
    }
}
