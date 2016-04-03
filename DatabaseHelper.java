package com.example.wizzydj.stockbeta;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wizzydj on 15-03-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABELA_PLACAS = "PLACAS";
    // Table columns
    public static final String _ID = "_ID";

    public static final String CATEGORIA = "categoria";
    public static final String MATERIAL = "material";
    public static final String FORNECEDOR = "fornecedor";
    public static final String COMPRIMENTO = "comprimento";
    public static final String LARGURA = "largura";
    public static final String EXPESSURA = "expessura";
    public static final String QUANTIDADE = "quantidade";

    public static final String TABELA_SPINNER = "TABELA_SPINNER";
    public static final String CAT_SPINNER = "CAT_SPINNER";
    public static final String MAT_SPINNER = "MAT_SPINNER";

    // Database Information
    static final String NOME_BASEDADOS = "databaseWZ.db";

    // database version
    static final int DB_VERSION = 1;

    private String[] materiais;

    // Creating table query
    //private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
           // + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT);";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABELA_PLACAS + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CATEGORIA + " varchar(50), " + MATERIAL + " varchar(50), " + FORNECEDOR + " varchar(50), " + COMPRIMENTO + " INTEGER, " + LARGURA + " INTEGER, " + EXPESSURA + " INTEGER, " + QUANTIDADE + " INTEGER);";

    private static final String CREATE_TABLE_SPINNER = "CREATE TABLE " + TABELA_SPINNER + "(_ID INTEGER PRIMARY KEY, " + CAT_SPINNER
            + " varchar(50), " + MAT_SPINNER + " varchar(50));";


    public DatabaseHelper(Context context) {
        super(context, NOME_BASEDADOS, null, DB_VERSION);

        Resources res = context.getResources();
        materiais = res.getStringArray(R.array.array_material);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_SPINNER);

        for(int i = 0; i < materiais.length; i ++){
            db.execSQL("insert into " + TABELA_SPINNER + " (" + MAT_SPINNER + ") values ('" + materiais[i] + "')");
        }

        //db.execSQL("insert into " + TABELA_PLACAS + " (" + CATEGORIA + ") values (' FUCK ')");
        //db.execSQL("insert into " + TABELA_PLACAS + " (" + FORNECEDOR + ") values (' SJGOMES ')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PLACAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_SPINNER);
        onCreate(db);
    }
}
