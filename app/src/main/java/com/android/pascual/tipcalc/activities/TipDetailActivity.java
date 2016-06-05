package com.android.pascual.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.pascual.tipcalc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipDetailActivity extends AppCompatActivity {

    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimesStamp)
    TextView txtTimesStamp;

    //Crear estructura key values para el Intent de esta actividad
    public final static String TIP_KEY = "tip";
    public final static String DATE_KEY = "txtTimesStamp";
    public final static String BILL_TOTAL_KEY = "total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        //Formatear los valores de estos atributos
        String strTotal = String.format(getString(R.string.tipdetail_message_bill),intent.getDoubleExtra(BILL_TOTAL_KEY, 0d));
        String strTip = String.format(getString(R.string.global_message_tip),intent.getDoubleExtra(TIP_KEY, 0));

        //devolver a la vista
        //txtBillTotal.setText(intent.getStringExtra(DATE_KEY));
        txtBillTotal.setText(strTotal);
        //txtTip.setText(intent.getStringExtra(TIP_KEY));
        txtTip.setText(strTip);
        txtTimesStamp.setText(intent.getStringExtra(DATE_KEY));



    }
}
