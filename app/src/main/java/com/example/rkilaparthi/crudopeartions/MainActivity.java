package com.example.rkilaparthi.crudopeartions;

import android.content.DialogInterface;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstname;
    EditText lastname;
    TextView text_result;
    DatabaseController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname=(EditText)findViewById(R.id.edit_text_fname);
        lastname=(EditText)findViewById(R.id.edit_text_lname);
        text_result=(TextView)findViewById(R.id.text_view_result);
        controller=new DatabaseController(this,"",null,1);
    }
    public void btn_click(View view){
        switch (view.getId()){
            case R.id.button_add:
                try {
                    controller.insert_student(firstname.getText().toString(), lastname.getText().toString());
                }catch(SQLException e){
                    Toast.makeText(MainActivity.this,"Already exists",Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.button_delete:
                controller.delete_student(firstname.getText().toString());
                break;
            case R.id.button_update:
                AlertDialog.Builder dailog=new AlertDialog.Builder(MainActivity.this);
                dailog.setTitle("Enter new First Name");
                final EditText new_firstname = new EditText(this);
                dailog.setView(new_firstname);
                dailog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student(firstname.getText().toString(),new_firstname.getText().toString());
                    }
                });
                dailog.show();
                break;
            case R.id.button_list:
                controller.list_all_students(text_result);
                break;
        }
    }
}
