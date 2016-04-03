package com.example.wizzydj.stockbeta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wizzydj on 12-03-2016.
 */
public class Medidas extends AppCompatActivity {

    private DBManager dbManager;
    private String categoria;
    private String material;
    private String fornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medidas_layout);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        categoria = bundle.getString("cat");
        material = bundle.getString("mat");
        fornecedor = bundle.getString("for");

        TextView lay_title = (TextView) findViewById(R.id.descricao_text);
        lay_title.setText(categoria + " " + material);

        dbManager = new DBManager(this);
        Button bInserir = (Button) findViewById(R.id.botao_medidas);
        Button bcancelar = (Button) findViewById(R.id.botao_cancelar);

        bcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        bInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbManager.open();

                TextView tComprimento = (TextView) findViewById(R.id.add_comprimento);
                String comp = tComprimento.getText().toString();
                int comprimento = ClassAux.StringToInt(comp);

                TextView tLargura = (TextView) findViewById(R.id.add_largura);
                int largura = ClassAux.StringToInt(tLargura.getText().toString());

                TextView tExpessura = (TextView) findViewById(R.id.add_expessura);
                String exp = tExpessura.getText().toString();
                int expessura = ClassAux.StringToInt(exp);

                TextView tQuantidade = (TextView) findViewById(R.id.add_quantidade);
                String quant = tQuantidade.getText().toString();
                int quantidade = ClassAux.StringToInt(quant);


                dbManager.insert(categoria, material, fornecedor, comprimento, largura, expessura, quantidade);
                Toast.makeText(Medidas.this, "Adicionado", Toast.LENGTH_SHORT).show();

                dbManager.close();
                finish();

            }
        });
    }

    public void cancelar(){
        Intent i = new Intent();
        i.setClass(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void finish() {
        dbManager.close();
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
