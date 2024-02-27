package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startSignupActivity(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void startLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public class SignupActivity extends AppCompatActivity {
        private EditText usernameEditText;
        private EditText passwordEditText;

        private UserDao userDao;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);


            usernameEditText = findViewById(R.id.usernameEditText);
            passwordEditText = findViewById(R.id.passwordEditText);

            userDao = AppDatabase.getInstance(this).userDao();
        }

        public void signup(View view) {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userDao.getUserByUsername(username) != null) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);

            userDao.insert(user);

            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public class LoginActivity extends AppCompatActivity {

        private EditText usernameEditText;
        private EditText passwordEditText;
        private UserDao userDao;
        private ContactDao contactDao;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            usernameEditText = findViewById(R.id.usernameEditText);
            passwordEditText = findViewById(R.id.passwordEditText);

            userDao = AppDatabase.getInstance(this).userDao();
            contactDao = AppDatabase.getInstance(this).contactDao();
        }

        public void login(View view) {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            Users user = userDao.login(username, password);

            if (user != null) {
                // Load user's contacts
                List<Contacts> contacts = contactDao.getUserContacts(user.getId());

                // Navigate to contacts activity and pass the contacts list
                Intent intent = new Intent(this, ContactsActivity.class);
                List<Contacts> contact = getIntent().<Contacts>getParcelableArrayListExtra("contacts");                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }

        public void signUp(View view) {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        }
    }
}

