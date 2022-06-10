package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitepractice.contacts.DatabaseAdapter;
import com.example.sqlitepractice.contacts.ContactsModelClass;

public class MainActivity extends AppCompatActivity {

    EditText etName,etNumber;
    Button writeSQLBtn,readSQLBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=(EditText)findViewById(R.id.et_name);
        etNumber=(EditText) findViewById(R.id.et_number);
        writeSQLBtn=(Button) findViewById(R.id.write_btn);
        readSQLBtn=(Button) findViewById(R.id.read_btn);
        //save data to database
        writeSQLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validating if the text fields are empty or not.
                if (etName.getText().toString().isEmpty()&&etNumber.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                    DatabaseAdapter adapter=new DatabaseAdapter(MainActivity.this);
                    adapter.addEntry(new ContactsModelClass(etName.getText().toString(),etNumber.getText().toString()));

                    // after adding the data we are displaying a toast message.
                    Toast.makeText(MainActivity.this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etNumber.setText("");


            }
        });
readSQLBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent contactIntent= new Intent(MainActivity.this, ContactsActivity.class);
        startActivity(contactIntent);
    }
});
    }
}