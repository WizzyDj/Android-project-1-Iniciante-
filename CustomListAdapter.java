package com.example.wizzydj.stockbeta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by wizzydj on 07-03-2016.
 */
public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Placas> listaData;
    private LayoutInflater layoutInflater;


    public CustomListAdapter(Context context, ArrayList<Placas> listaData){
        this.listaData = listaData;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listaData.size();
    }

    @Override
    public Object getItem(int position) {
        return listaData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.new_item_layout, null);

            holder = new ViewHolder();
            holder.descricao = (TextView) convertView.findViewById(R.id.it_descricao);
            holder.fornecedor = (TextView) convertView.findViewById(R.id.it_fornecedor);
            holder.medidas = (TextView) convertView.findViewById(R.id.it_medidas);
            holder.quantidade = (TextView) convertView.findViewById(R.id.it_quantidade);
            convertView.setTag(holder);

        }else{

            holder = (ViewHolder) convertView.getTag();
        }

        holder.descricao.setText(listaData.get(position).getCategoria()
        + " " + listaData.get(position).getMaterial());

        holder.fornecedor.setText(listaData.get(position).getFornecedor());

        holder.medidas.setText(
                ClassAux.IntToString(listaData.get(position).getComprimento()) + " x "
        + ClassAux.IntToString(listaData.get(position).getLargura())
                + " x " + ClassAux.IntToString(listaData.get(position).getExpessura()));

        holder.quantidade.setText(ClassAux.IntToString(listaData.get(position).getQuantidade()));


        return convertView;
    }

    static class ViewHolder {
        private TextView descricao;
        private TextView fornecedor;
        private TextView medidas;
        private TextView quantidade;
        
    }

}
