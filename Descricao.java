package com.example.wizzydj.stockbeta;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wizzydj on 12-03-2016.
 */
public class Descricao extends AppCompatActivity {

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao);

        addItemsSpinnerCategorias();
        addItemsSpinnerMaterial();


        Button botaoNext = (Button) findViewById(R.id.botao_descricao_next);
        botaoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Descricao.this, Medidas.class);

                Bundle bundle = new Bundle();
                bundle.putString("cat", CategoriaSpinnerSelect());
                bundle.putString("mat", MaterialSpinnerSelect());
                bundle.putString("for", FornecedorSelect());
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });
    }


    public String CategoriaSpinnerSelect() {
        Spinner spinnerCategoria = (Spinner) findViewById(R.id.spinner_categoria);
        return spinnerCategoria.getSelectedItem().toString();
    }

    public String MaterialSpinnerSelect() {
        Spinner spinnerMaterial = (Spinner) findViewById(R.id.spinner_material);
        return spinnerMaterial.getSelectedItem().toString();

    }

    public String FornecedorSelect(){
        TextView fornecedorView = (TextView) findViewById(R.id.add_fornecedor);
        return fornecedorView.getText().toString();

    }

    public void addItemsSpinnerCategorias() {
        final Spinner sp = (Spinner) findViewById(R.id.spinner_categoria);
        Resources res = getResources();

        String[] categorias = res.getStringArray(R.array.array_categorias);
        List<String> nomes_cat;
        nomes_cat = Arrays.asList(categorias);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes_cat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

    }

    public void addItemsSpinnerMaterial() {
        final Spinner sp = (Spinner) findViewById(R.id.spinner_material);
        dbManager = new DBManager(this);
        dbManager.open();

        Cursor c = dbManager.consultaLeitura("SELECT MAT_SPINNER FROM TABELA_SPINNER");

        ArrayList<String> nomes_material = new ArrayList<String>();

        if (c.getCount() > 0) {
            c.moveToFirst();
            int matnome = c.getColumnIndex("MAT_SPINNER");

            do {
                nomes_material.add(c.getString(matnome));
            } while (c.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes_material);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        c.close();
        dbManager.close();
    }

    @Override
    public void finish() {
        dbManager.close();
        super.finish();
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}
