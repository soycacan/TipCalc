package com.android.pascual.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.pascual.tipcalc.R;
import com.android.pascual.tipcalc.TipCalcApp;
import com.android.pascual.tipcalc.fragments.TipHistoryListFragment;
import com.android.pascual.tipcalc.fragments.TipHistoryListFragmentListener;
import com.android.pascual.tipcalc.models.TipRecord;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANCE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    //manera tradicional
    //EditText inputbill = (EditText)findViewById(R.id.inputBill);

    //nueva manera con butterknife
 /*  @BindView(R.id.inputBill)
    EditText inputbill;*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this); //casa los xml con los bind notaciones

        //instanciar clase fragmento atraves de su layout
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        //configurar para que elfragmento no se reinicie sus valores
        fragment.setRetainInstance(true);

        //asignar al listener de tipo fragmento, el fragmento. (se debe castear)
        fragmentListener = fragment;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //original de aqui, NO FUNCIONA
        //crear menu, usando recurso e inflandolo (convertirlo a algo para mostrar)
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //si el item seleccionado es menu about
        if (item.getItemId() == R.id.action_about) {
            about();
        }

        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.btnSubmit) // no hace falta el listener
    public void handleClickSubmit(){
        hideKeyBoard();
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            //ya no calculo aqui
            //double tip = total*(tipPercentage/100d);
            //double tip = total*(tipPercentage/100);
            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_tip),tipRecord.getTip());

            //pasar al fragmetn
            fragmentListener.addToList(tipRecord);
            //fragmentListener.action(strTip);
            //pasar al Textview
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPorcentage = inputPercentage.getText().toString().trim();
        if (!strInputTipPorcentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPorcentage);
        } else {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }


    private void hideKeyBoard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
                try {
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);}
                catch (NullPointerException npe){
                    Log.e(getLocalClassName(),Log.getStackTraceString(npe));
                }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyBoard();
        handleTipChance(TIP_STEP_CHANCE);
        }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyBoard();
        handleTipChance(-TIP_STEP_CHANCE);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        hideKeyBoard();
        fragmentListener.clearList();
    }

    private void handleTipChance(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if (tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }


    //metodo para llamar al intent de about
    private void about() {
        //llamar aplicacion
        TipCalcApp app = (TipCalcApp) getApplication();
        //obtener el url definido en esa aplicaicon
        String strUrl = app.getAboutUrl();
        //crear el intent del tipo para navegar
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //asignar el url al intent, parseandolo de texto a Url
        intent.setData(Uri.parse(strUrl));
        //arrancar la actividad, con ese intent
        startActivity(intent);
    }
}
