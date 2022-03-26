package com.example.todotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
       implements View.OnClickListener {

    private EditText etTodoInput;
    private Button btnSaveTodo;
    private ListView lvDisplayTodo;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnSaveTodo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String inputTodo = etTodoInput.getText().toString();

                        if (inputTodo.isEmpty())
                            Toast.makeText(MainActivity.this,
                                    "NO empty todos",
                                    Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(MainActivity.this,
                                    inputTodo,
                                    Toast.LENGTH_SHORT).show();
                            //
                            updateAdapter(inputTodo);
                            cleanInput();
                        }
                    }
                }
        );

        /*
        UI Events configurations
        1.- Anonymous Implementation (new View.OnClickListener{})
        2.- Lambdas ( (view)->{} )
        3.- XML attribute ( android:onclick="methodName" )
        4.- Interface Impl ( class MyClass implements View.OnClickListener )
        5.- Method Reference ( setOn...(this::methodName) )
         */

        btnSaveTodo.setOnClickListener(this::anotherClickEvent);

        lvDisplayTodo.setOnItemLongClickListener(
                (adapterView, view, index, l) -> {
                    adapter.remove(
                            adapter.getItem(
                                    index
                     )
                    );
                    return true;
                }
        );
    }

    private void cleanInput() {
        etTodoInput.getText().clear();
    }

    private void updateAdapter(String inputTodo) {
       // setup LV with Adapter
       lvDisplayTodo.setAdapter(adapter);
       // update adapter with data
       adapter.add(inputTodo);
    }

    private void initViews() {
        etTodoInput = findViewById(R.id.et_todo_input);
        btnSaveTodo = findViewById(R.id.btn_save_todo);
        lvDisplayTodo = findViewById(R.id.lv_display_todo);
        adapter = new ArrayAdapter<>( this,
                android.R.layout.simple_list_item_1);
    }

    public void anotherClickEvent(View view) {
        Toast.makeText(this, "XML Implementation", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Interface Implementation", Toast.LENGTH_SHORT).show();

    }
}




