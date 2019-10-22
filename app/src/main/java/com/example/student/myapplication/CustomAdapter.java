package com.example.student.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Activity activity;
    int customList;
    ArrayList<HocSinh> lsHocSinh;
    SqlDB db;

    public CustomAdapter(Activity activity, int customList, SqlDB db) {
        this.activity = activity;
        this.customList = customList;
        this.lsHocSinh = db.getAllHocSinh();
        this.db = db;
    }

    public void themHocSinh(HocSinh hs){
        db.insert(hs);
        capNhatList();
    }

    public void suaHocSinh(HocSinh hs){
        db.editHocSinh(hs);
        capNhatList();
    }

    public HocSinh getHocSinh(int stt){
        return lsHocSinh.get(stt);
    }

    private void capNhatList(){
        this.lsHocSinh = db.getAllHocSinh();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lsHocSinh.size();
    }

    @Override
    public Object getItem(int i) {
        return lsHocSinh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        view = layoutInflater.inflate(customList,null);
        final TextView txtId,txtName;
        Button btnDelete;

        txtId = view.findViewById(R.id.txtId);
        txtName = view.findViewById(R.id.txtName);
        txtId.setText(lsHocSinh.get(i).id + "");
        txtName.setText(lsHocSinh.get(i).name);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteHocSinh(lsHocSinh.get(i).id);
                capNhatList();
            }
        });
//        txtId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((EditText)activity.findViewById(R.id.edtID)).setText(txtId.getText());
//                ((EditText)activity.findViewById(R.id.edtName)).setText(txtName.getText());
//            }
//        });
//        txtName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((EditText)activity.findViewById(R.id.edtID)).setText(txtId.getText());
//                ((EditText)activity.findViewById(R.id.edtName)).setText(txtName.getText());
//
//            }
//        });

        return view;
    }
}
