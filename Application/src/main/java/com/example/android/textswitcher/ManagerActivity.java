package com.example.android.textswitcher;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class ManagerActivity extends Activity {
    private Button delFileBtn;
    private Button addBtn;
    private EditText addText;
    private BrundominatiArgumentManager manager = new BrundominatiArgumentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        manager.load(getApplicationContext());

        delFileBtn = findViewById(R.id.del_file_button);
        delFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.deleteFile(getApplicationContext());
            }
        });

        addBtn = findViewById(R.id.add_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ManagerActivity", addText.getText().toString());
                String arg = addText.getText().toString();
                manager.add(new Argument(arg + "\n"));
                manager.save(getApplicationContext());
                addText.setText("");
            }
        });

        addText = findViewById(R.id.add_text);
    }
}
