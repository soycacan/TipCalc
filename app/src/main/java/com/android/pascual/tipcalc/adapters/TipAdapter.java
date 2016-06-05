package com.android.pascual.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.pascual.tipcalc.R;
import com.android.pascual.tipcalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pascual on 6/2/2016.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    // Listado de propinas
    private List<TipRecord> dataset;
    private Context context;
    private OnItemClickListener onItemClickListener;

    //Constructor
    //public TipAdapter(Context context, List<TipRecord> dataset) {
    public TipAdapter(Context context, ArrayList<TipRecord> dataset, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        //this.dataset = dataset;
        this.dataset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //aqui se crea la vista con el inflate del xml y se retorna un ViewHolder con la vista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //instanciar contenido
        TipRecord elemento = dataset.get(position);
        //recuperar y formatear
        String strTip = String.format(context.getString(R.string.global_message_tip), elemento.getTip());
        //anadir al ViewHolder
        holder.setOnItemClickListener(elemento, onItemClickListener);
        holder.txtContent.setText(strTip);
        holder.txtDate.setText(elemento.getDateFormatted());
    }

    @Override
    public int getItemCount() {
        //tamano
        return dataset.size();
    }

    public void add(TipRecord record) {
        dataset.add(0, record); //agregar al principio
        notifyDataSetChanged();//notificar el cambio
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txtContent)
        TextView txtContent;
        @Bind(R.id.txtDate)
        TextView txtDate;
        //el viewholder tendra un text view, lo referencio con butterknife

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setOnItemClickListener(final TipRecord elemento, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(elemento);
                }
            });
        }
    }

}
