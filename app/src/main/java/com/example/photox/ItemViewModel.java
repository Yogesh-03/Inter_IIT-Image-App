package com.example.photox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {

    // @MutableLiveData to get value to send the scale float value from Blur Fragment
    private final MutableLiveData<Float>  selectedItem = new MutableLiveData<Float>();
    public void setScale(float value){
        selectedItem.setValue(value);
    }

    public LiveData<Float> getSelectedItem(){
        return selectedItem;
    }

    // @MutableLiveData to get value to send the Radius float value from Blur Fragment
    private final MutableLiveData<Float> selectedItemRadius = new MutableLiveData<Float>();
    public void setRadius(float radius){
        selectedItemRadius.setValue(radius);
    }
    public  LiveData<Float> getSelectedRadius(){
        return selectedItemRadius;
    }

    // @MutableLiveData to get value to send the Saturation float value from Color Fragment
    private final MutableLiveData<Float> selectedItemSaturation = new MutableLiveData<Float>();

    public void setSaturation(Float sat){
        selectedItemSaturation.setValue(sat);
    }
    public LiveData<Float> getSelectedSaturation(){
        return selectedItemSaturation;
    }

    // @MutableLiveData to get value to send the Contrast float value from Color Fragment
    private final MutableLiveData<Float> selectedItemContrast = new MutableLiveData<Float>();
    public void setContrast(Float con){
        selectedItemContrast.setValue(con);
    }
    public  LiveData<Float> getSelectedContrast(){
        return selectedItemContrast;
    }

    // @MutableLiveData to get value to send the Brightness float value from Color Fragment
    private final MutableLiveData<Float> selectedItemBrightness = new MutableLiveData<Float>();
    public void setBrightness(Float bright){
        selectedItemBrightness.setValue(bright);
    }
    public  LiveData<Float> getSelectedBrightness(){
        return selectedItemBrightness;
    }

    // @MutableLiveData to get value to send the Opacity int value from Color Fragment
    private final MutableLiveData<Integer> selectedItemOpacity = new MutableLiveData<Integer>();

    public void setOpacity(int op){
        selectedItemOpacity.setValue(op);
    }
    public LiveData<Integer> getSelectedItemOpacity(){
        return selectedItemOpacity;
    }

    // @MutableLiveData to get value to send the Tempereature flaat value from Effects Fragment
    private final MutableLiveData<Float> selectedItemTempereature = new MutableLiveData<Float>();

    public void setTempereature(Float tem){
        selectedItemTempereature.setValue(tem);
    }

    public  LiveData<Float> getSelectedItemTempereature(){
        return selectedItemTempereature;
    }


    // @MutableLiveData to get value to send the Sharpness flaat value from Effects Fragment
    private final MutableLiveData<Float> selectedItemSharpness = new MutableLiveData<Float>();

    public void setSharpness(Float shp){
        selectedItemSharpness.setValue(shp);
    }

    public  LiveData<Float> getSelectedItemSharpness(){
        return selectedItemSaturation;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemPinkTint = new MutableLiveData<Integer>();

    public void setPinkTint(Integer in){
        selectedItemPinkTint.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemPinkTint(){
        return selectedItemPinkTint;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintTwo = new MutableLiveData<Integer>();

    public void setTintTwo(Integer in){
        selectedItemTintTwo.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintTwo(){
        return selectedItemTintTwo;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintThree = new MutableLiveData<Integer>();

    public void setTintThree(Integer in){
        selectedItemTintThree.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintThree(){
        return selectedItemTintThree;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintFour = new MutableLiveData<Integer>();

    public void setTintFour(Integer in){
        selectedItemTintFour.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintFour(){
        return selectedItemTintFour;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintFive = new MutableLiveData<Integer>();

    public void setTintFive(Integer in){
        selectedItemTintFive.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintFive(){
        return selectedItemTintFive;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintSix = new MutableLiveData<Integer>();

    public void setTintSix(Integer in){
        selectedItemTintSix.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintSix(){
        return selectedItemTintSix;
    }


    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintSeven = new MutableLiveData<Integer>();

    public void setTintSeven(Integer in){
        selectedItemTintSeven.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintSeven(){
        return selectedItemTintSeven;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintEight = new MutableLiveData<Integer>();

    public void setTintEight(Integer in){
        selectedItemTintEight.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintEight(){
        return selectedItemTintEight;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintNine = new MutableLiveData<Integer>();

    public void setTintNine(Integer in){
        selectedItemTintNine.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintNine(){
        return selectedItemTintNine;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemTintTen = new MutableLiveData<Integer>();

    public void setTintTen(Integer in){
        selectedItemTintTen.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemTintTen(){
        return selectedItemTintTen;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemRotation = new MutableLiveData<Integer>();

    public void setRotation(Integer in){
        selectedItemRotation.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemRotation(){
        return selectedItemRotation;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemRotationLeft = new MutableLiveData<Integer>();

    public void setRotationLeft(Integer in){
        selectedItemRotationLeft.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemRotationLeft(){
        return selectedItemRotationLeft;
    }

    // @MutableLiveData to get value to send the Sharpness int value from Effects Fragment
    private final MutableLiveData<Integer> selectedItemOriginal = new MutableLiveData<Integer>();

    public void setOriginal(Integer in){
        selectedItemOriginal.setValue(in);
    }

    public  LiveData<Integer> getSelectedItemOriginal(){
        return selectedItemOriginal;
    }


}
