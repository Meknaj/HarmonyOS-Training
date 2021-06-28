package com.example.myapplication;

import com.example.myapplication.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.abilityjet.activedata.ActiveData;
import ohos.aafwk.abilityjet.activedata.DataObserver;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;

public class MainAbility extends Ability {
    private ActiveData fruitPrice;
    Text text;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
    }
