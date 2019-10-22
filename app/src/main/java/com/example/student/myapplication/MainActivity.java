package com.example.student.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtId,edtName;
    ListView lsvHocSinh;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        lsvHocSinh = findViewById(R.id.lsvHocSinh);

        SqlDB db = new SqlDB(this);
        try {
            db.insert(new HocSinh(1,"Viet"));
            db.insert(new HocSinh(2,"Nam Anh"));
            db.insert(new HocSinh(3,"Tài"));
            db.insert(new HocSinh(4,"Huy"));
            db.insert(new HocSinh(5,"Kiệt"));
            db.insert(new HocSinh(6,"Hoàng"));
        }catch (Exception t){

        }


        //Toast.makeText(this, (db.getAllHocSinh()).get(2).name + "", Toast.LENGTH_SHORT).show();
        customAdapter = new CustomAdapter(this,R.layout.list_item,db);
        lsvHocSinh.setAdapter(customAdapter);

        lsvHocSinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                HocSinh hs = customAdapter.getHocSinh(i);
                edtId.setText(hs.id + "");
                edtName.setText(hs.name + "");
            }
        });


    }

    public void them(View view) {
        customAdapter.themHocSinh(new HocSinh(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString()));
        edtId.setText("");
        edtName.setText("");
    }

    public void sua(View view) {
        customAdapter.suaHocSinh(new HocSinh(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString()));
    }
}
