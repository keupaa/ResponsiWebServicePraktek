package com.example.responsiwebservicepraktek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.responsiwebservicepraktek.Adapter.Adapter;
import com.example.responsiwebservicepraktek.Data.Data;
import com.example.responsiwebservicepraktek.Helper.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, EditorActivity.class);
                startActivity(intent);
            }
        }));
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity2.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String name = lists.get(i).getName();
                final String email = lists.get(i).getEmail();
                final CharSequence[] dialogitem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity2.this);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity2.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
                                lists.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }

    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String name = rows.get(i).get("name");
            String email = rows.get(i).get("email");

            Data data = new Data();
            data.setId(id);
            data.setName(name);
            data.setEmail(email);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}