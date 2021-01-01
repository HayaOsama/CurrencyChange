package com.example.currencychange.ViewModel.Adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import com.example.currencychange.ViewModel.entity.SpinnerItem;
import java.util.ArrayList;

public class SpinnerAdapter implements android.widget.SpinnerAdapter {
    private   ArrayList<SpinnerItem> items ;
    private static final   int ITEM_TYPE =1 ;

    public SpinnerAdapter(ArrayList<SpinnerItem> items) {
        this.items = items;
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return ITEM_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public boolean isEmpty() {
        return getCount()==0;
    }
}
