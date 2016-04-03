package com.example.wizzydj.stockbeta;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by WizzyDj on 10/02/2016.
 */
public class Placas extends AppCompatActivity {

    private int ID;
    private int Comprimento;
    private int Largura;
    private int Expessura;
    private int Quantidade;
    private String Fornecedor;
    private String Categoria;
    private String Material;
    private ImageView img;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public int getLargura() {
        return Largura;
    }

    public void setLargura(int largura) {
        Largura = largura;
    }

    public int getComprimento() {
        return Comprimento;
    }

    public void setComprimento(int comprimento) {
        Comprimento = comprimento;
    }

    public int getExpessura() {
        return Expessura;
    }

    public void setExpessura(int expessura) {
        Expessura = expessura;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        Fornecedor = fornecedor;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
