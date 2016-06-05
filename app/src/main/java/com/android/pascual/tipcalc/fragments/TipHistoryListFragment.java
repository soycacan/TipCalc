package com.android.pascual.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.pascual.tipcalc.R;
import com.android.pascual.tipcalc.activities.TipDetailActivity;
import com.android.pascual.tipcalc.adapters.OnItemClickListener;
import com.android.pascual.tipcalc.adapters.TipAdapter;
import com.android.pascual.tipcalc.models.TipRecord;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {
    TipAdapter adapter;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initAdapter() {
        if (adapter == null) {
            //adapter = new TipAdapter(getActivity().getApplicationContext(), new ArrayList<TipRecord>());
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }
    }

    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        //Toast.makeText(getActivity(),tipRecord.getDateFormatted(), Toast.LENGTH_SHORT).show();
        //llamar a la clase que oncreate infla la actividad
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);

        //le agregamos los valores key-value necesarios
        intent.putExtra(TipDetailActivity.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY,tipRecord.getBill());
        intent.putExtra(TipDetailActivity.DATE_KEY, tipRecord.getDateFormatted());
        startActivity(intent);
    }

    /*@Override
    public void action(String str) {
        //prueba del llamado y reaccion del fragmente
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }*/
}
