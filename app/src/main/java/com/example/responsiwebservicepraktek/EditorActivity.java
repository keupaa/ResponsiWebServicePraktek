package com.example.responsiwebservicepraktek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.responsiwebservicepraktek.Helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText editName, editEmail;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String id, name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        btnSave = findViewById(R.id.btn_save);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        if (id == null || id.equals("")){
            setTitle("Tambah User");
        }else {
            setTitle("Edit User");
            editName.setText(name);
            editEmail.setText(email);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (id == null || id.equals("")){

                    }else {

                    }

                }catch(Exception e){
                    Log.e("saving", e.getMessage());
                }
            }
        });


    }

    private void save(){
        if (String.valueOf(editName.getText()).equals("") || String.valueOf(editEmail.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.insert(editName.getText().toString(), editEmail.getText().toString());
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(editName.getText()).equals("") || String.valueOf(editEmail.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.update(Integer.parseInt(id),editName.getText().toString(), editEmail.getText().toString());
            finish();
        }
    }
}