package com.cscorner.leicestercollege;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class register extends AppCompatActivity {

    ImageButton button,button2,button3,button4;
    EditText editText;
    ListView listView;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button=findViewById(R.id.add);
        button2=findViewById(R.id.del);
        button3=findViewById(R.id.view);
        button4=findViewById(R.id.clear);
        editText=findViewById(R.id.edit);
        listView=findViewById(R.id.list);
        databaseHandler=new DatabaseHandler(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sav=editText.getText().toString();
                boolean insertdata=databaseHandler.addData(sav);
                if(insertdata==true){
                    Toast.makeText(register.this, "DATA ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(register.this, "DATA FAILED TO SAVE", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase=databaseHandler.getWritableDatabase();
                String del=editText.getText().toString();
                deleteData(del);
                Toast.makeText(register.this, "DATA DELETE SUCCESFULLY", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                Toast.makeText(register.this, "DATA CLEAR SUCCESFULLY", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData(){
        ArrayList<String> theList=new ArrayList<>();
        Cursor data=databaseHandler.getListContents();
        if(data.getCount()==0){
            Toast.makeText(this, "THERE IS NO CONTENT", Toast.LENGTH_SHORT).show();
        }
        else {
            while (data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                listView.setAdapter();
            }
        }
    }
    private boolean deleteData(String del){
        return sqLiteDatabase.delete(DatabaseHandler.TABLE,DatabaseHandler.COL2 + "?",new String[]{del})>
    }

}