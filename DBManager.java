package com.example.wizzydj.stockbeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wizzydj on 15-03-2016.
 */
public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String categoria, String material, String fornecedor, int comprimento, int largura, int expessura, int quantidade) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CATEGORIA, categoria);
        contentValue.put(DatabaseHelper.MATERIAL, material);
        contentValue.put(DatabaseHelper.FORNECEDOR, fornecedor);
        contentValue.put(DatabaseHelper.COMPRIMENTO, comprimento);
        contentValue.put(DatabaseHelper.LARGURA, largura);
        contentValue.put(DatabaseHelper.EXPESSURA, expessura);
        contentValue.put(DatabaseHelper.QUANTIDADE, quantidade);

        database.insert(DatabaseHelper.TABELA_PLACAS, null, contentValue);
    }

    public void insertSpinner(String CATspinner, String MATspinner){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CAT_SPINNER, CATspinner);
        contentValues.put(DatabaseHelper.MAT_SPINNER, MATspinner);

        database.insert(DatabaseHelper.TABELA_SPINNER, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.CATEGORIA, DatabaseHelper.MATERIAL, DatabaseHelper.FORNECEDOR,
        DatabaseHelper.COMPRIMENTO, DatabaseHelper.LARGURA, DatabaseHelper.EXPESSURA, DatabaseHelper.QUANTIDADE};

        Cursor cursor = database.query(DatabaseHelper.TABELA_PLACAS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String categoria, String material, String fornecedor, int comprimento, int largura, int expessura, int quantidade) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CATEGORIA, categoria);
        contentValues.put(DatabaseHelper.MATERIAL, material);
        contentValues.put(DatabaseHelper.FORNECEDOR, fornecedor);
        contentValues.put(DatabaseHelper.COMPRIMENTO, comprimento);
        contentValues.put(DatabaseHelper.LARGURA, largura);
        contentValues.put(DatabaseHelper.EXPESSURA, expessura);
        contentValues.put(DatabaseHelper.QUANTIDADE, quantidade);

        int i = database.update(DatabaseHelper.TABELA_PLACAS, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABELA_PLACAS, DatabaseHelper._ID + "=" + _id, null);
    }

    public Cursor consultaLeitura(String consulta) {
        return database.rawQuery(consulta,null);
    }


    //Get Row Count
    public int getCount() {
        String countQuery = "SELECT * FROM " + DatabaseHelper.TABELA_PLACAS;
        int count = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    //Delete Query
    public void removeFav(int id) {
        String countQuery = "DELETE FROM " + DatabaseHelper.TABELA_PLACAS + " where " + DatabaseHelper._ID + "= " + id;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(countQuery);

    }

    //Get FavList
    public ArrayList getFavList(){
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABELA_PLACAS;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Placas> FavList = new ArrayList<Placas>();
        if (cursor.moveToFirst()) {
            do {
                Placas list = new Placas();
                list.setID(Integer.parseInt(cursor.getString(0)));
                list.setCategoria(cursor.getString(1));
                list.setMaterial(cursor.getString(2));
                list.setFornecedor(cursor.getString(3));
                list.setComprimento(cursor.getInt(4));
                list.setLargura(cursor.getInt(5));
                list.setExpessura(cursor.getInt(6));
                list.setQuantidade(cursor.getInt(7));

                FavList.add(list);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return FavList;
    }

}