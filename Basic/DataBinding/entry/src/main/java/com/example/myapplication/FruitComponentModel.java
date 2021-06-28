package com.example.myapplication;

import ohos.aafwk.abilityjet.activedata.ActiveData;
public class FruitComponentModel {
    private ActiveData<String> price;
    public ActiveData<String> getPrice() {
        return price;
    }
    public void setPrice(ActiveData<String> price) {
        this.price = price;
    }
}
