package com.example.wizzydj.stockbeta;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;

/*    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.CATEGORIA, DatabaseHelper.MATERIAL, DatabaseHelper.FORNECEDOR,
            DatabaseHelper.COMPRIMENTO, DatabaseHelper.LARGURA, DatabaseHelper.EXPESSURA, DatabaseHelper.QUANTIDADE};
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listManager();

        Button buttonMain = (Button) findViewById(R.id.botao_main);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Descricao.class);
                startActivity(intent);
            }
        });


    }

    public void atualizarLista(){

        dbManager = new DBManager(this);
        dbManager.open();
        ArrayList dbManagerFavList = dbManager.getFavList();

        final ListView listView = (ListView) findViewById(R.id.myList);

        listView.setAdapter(new CustomListAdapter(this, dbManagerFavList));

        dbManager.close();
    }

    public void listManager() {

        dbManager = new DBManager(this);
        dbManager.open();
        final ListView listView = (ListView) findViewById(R.id.myList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listView.getItemAtPosition(position);
                Placas placas = (Placas) o;

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Detalhes.class);
                startActivity(intent);

         //       Toast.makeText(MainActivity.this, "Selecionou :" + " " + placas.getCategoria()
           //             + " " + placas.getMaterial(), Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                Object object = listView.getItemAtPosition(position);
                final Placas placas = (Placas) object;

                AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(MainActivity.this);
                mAlertDialog.setTitle("Apagar");
                mAlertDialog.setMessage("Tem a certeza que deseja apagar?");
                mAlertDialog.setCancelable(true);

                mAlertDialog.setPositiveButton(
                        "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbManager.removeFav(placas.getID());
                                onStart();
                            }
                        });

                mAlertDialog.setNegativeButton(
                        "Não",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert1 = mAlertDialog.create();
                alert1.show();

                return false;
            }
        });

        dbManager.close();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.definicoes){
            Intent i = new Intent();
            i.setClass(MainActivity.this, DefinicoesActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizarLista();
    }

    @Override
    public void finish() {
        dbManager.close();
        super.finish();
    }
}
