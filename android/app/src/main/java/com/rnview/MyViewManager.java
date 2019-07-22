package com.rnview;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.HashMap;
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
        LinearLayout ll = new LinearLayout(reactContext);
        MyView myView = new MyView(reactContext);
//        TextView tv=new TextView(reactContext);
//        tv.setText("1212321423542352346432634");
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MyViewManager", "点击了控件" + ll.getId());
                // 创建数据传递信使，类似于Android中的Bundle
                WritableMap data = Arguments.createMap();
                data.putString("msg", "点击了控件");// key用于在RN中获取传递的数据
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        ll.getId(), // RN层原生层根据id绑定在一起
                        EVENT_NAME_ONCLICK, // 事件名称
                        data // 传递的数据
                );
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myView, params);
        return ll;
    }

    @ReactProp(name = "text")
    public void setText(LinearLayout my, String text) {
        ((MyView) my.getChildAt(0)).setText(text);
        Log.e("LOG", text);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(EVENT_NAME_ONCLICK, MapBuilder.of("registrationName", EVENT_NAME_ONCLICK));
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> map = new HashMap<>();
        map.put("abc", "这是原生数据!");
        return map;
    }

    @ReactMethod
    public void show() {
        Log.e("MyViewManager", "show方法被调用。" );
    }

}
