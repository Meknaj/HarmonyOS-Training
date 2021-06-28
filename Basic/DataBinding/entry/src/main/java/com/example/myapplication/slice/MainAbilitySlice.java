package com.example.myapplication.slice;

import com.example.myapplication.FruitComponentModel;
import com.example.myapplication.ResourceTable;
import com.example.myapplication.AbilityMainBinding;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.abilityjet.activedata.ActiveData;
import ohos.aafwk.abilityjet.databinding.DataBindingUtil;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.IOException;

public class MainAbilitySlice extends AbilitySlice {
    private ComponentContainer componentContainer;
    private AbilityMainBinding binding;
    private FruitComponentModel apple;

    private static final int DOMAIN = 0x00101;
    private static final String TAG = "Databinding";
    protected static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, DOMAIN, TAG);

    @Override
    public void onStart(Intent intent) {
        ComponentContainer componentContainer = (ComponentContainer)LayoutScatter.getInstance(this).parse(ResourceTable.Layout_ability_main, null, false);
        super.onStart(intent);
        if(!(componentContainer instanceof ComponentContainer)) {
            HiLog.error(LABEL, "Error");
        }
        //super.setUIContent(ResourceTable.Layout_ability_main);
        super.setUIContent(componentContainer);
        try {
            binding = DataBindingUtil.createBinding(ResourceTable.Layout_ability_main,
                    getContext(), "com.example.myapplication");
        } catch (IOException e) {
            HiLog.error(LABEL, "Error");
        }
        if (binding != null) {
            binding.initComponent(componentContainer);
            binding.setLifecycle(getLifecycle());
            apple = new FruitComponentModel();
            ActiveData price = new ActiveData<>();
            apple.setPrice(price);
            price.setData("7.23");
            binding.setApple(apple);
        }
    }
}
