package com.android.pascual.tipcalc.fragments;

import com.android.pascual.tipcalc.models.TipRecord;

/**
 * Created by pascual on 6/2/2016.
 */
public interface TipHistoryListFragmentListener {
    //void action(String str);
    void addToList(TipRecord record);
    void clearList();
}
