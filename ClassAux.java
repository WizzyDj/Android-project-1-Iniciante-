package com.example.wizzydj.stockbeta;


import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by wizzydj on 05-03-2016.
 */
public class ClassAux {

    private DBManager dbManager;
    private Context context;

    public static String IntToString(int numeros){
        return String.valueOf(numeros);
    }

    public static int StringToInt(String string){
        return Integer.parseInt(string);
    }

//Adicionar dados á lista (Nao funciona corretamente)
    private ArrayList getListData(){

        ArrayList<Placas> results = new ArrayList<Placas>();
        Placas placas = new Placas();

        dbManager = new DBManager(context);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        if(cursor.moveToFirst()){

            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String cat = cursor.getString(cursor.getColumnIndex("categoria"));
            String mat = cursor.getString(cursor.getColumnIndex("material"));
            String fornecedor = cursor.getString(cursor.getColumnIndex("fornecedor"));
            int quant = cursor.getInt(cursor.getColumnIndex("quantidade"));
            int comp = cursor.getInt(cursor.getColumnIndex("comprimento"));
            int larg = cursor.getInt(cursor.getColumnIndex("largura"));
            int exp = cursor.getInt(cursor.getColumnIndex("expessura"));

            do{
                placas.setID(id);
                placas.setCategoria(cat);
                placas.setMaterial(mat);
                placas.setFornecedor(fornecedor);
                placas.setQuantidade(quant);
                placas.setComprimento(comp);
                placas.setLargura(larg);
                placas.setExpessura(exp);

                results.add(placas);


            }while (cursor.moveToNext());

        }

        cursor.close();
        dbManager.close();

        return results;

    }

    public void setAlertDialog(){
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(context);
        mAlertDialog.setMessage("Write your message here.");
        mAlertDialog.setCancelable(true);

        mAlertDialog.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        mAlertDialog.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = mAlertDialog.create();
        alert11.show();
    }


    public void setAlertDialog2(){

        //alterar a variavel context se necessario

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();

    }

}
