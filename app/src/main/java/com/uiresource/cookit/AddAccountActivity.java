package com.uiresource.cookit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class AddAccountActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.uiresource.cookit.EXTRA_USERNAME";
    public static final String EXTRA_EMAIL = "com.uiresource.cookit.EXTRA_EMAIL";
    public static final String EXTRA_ID = "com.uiresource.cookit.EXTRA_ID";

    private EditText editTextUsername;
    private EditText editTextEmail;
    //private EditText editTextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        Button button = (Button) findViewById(R.id.button_add_account);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                saveAccount();
            }
        });

        editTextUsername = findViewById(R.id.edit_text_accUsername);
        editTextEmail = findViewById(R.id.edit_text_accEmail);
        //editTextID = findViewById(R.id.edit_text_accID);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            editTextUsername.setText(intent.getStringExtra(EXTRA_USERNAME));
            editTextEmail.setText(intent.getStringExtra(EXTRA_EMAIL));
        }

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        //setTitle("Add Account");
    }

    private void saveAccount(){
        String Username = editTextUsername.getText().toString();
        String Email = editTextEmail.getText().toString();
        //String ID = editTextID.getText().toString();

        if (Username.trim().isEmpty() || Email.trim().isEmpty()){
            Toast.makeText(this,"Please insert data", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_USERNAME, Username);
        data.putExtra(EXTRA_EMAIL, Email);

        String id = "-1";
        id = getIntent().getStringExtra(EXTRA_ID);

        if (id != "-1"){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    /*public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_account_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_account:
                saveAccount();
                return true;
            default:
                    return super.onOptionsItemSelected(item);
        }
    }*/
}
