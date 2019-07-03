package com.example.rxjava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Button addBtn;
    private EditText nameEt;
    private EditText authorNameEt;

    private BooksDao booksDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        addBtn = findViewById(R.id.add_btn);
        nameEt = findViewById(R.id.name_et);
        authorNameEt = findViewById(R.id.author_name_et);

        booksDao = TestDb.getAppDatabase(this).booksDao();


        addBtn.setOnClickListener(view -> {
            Book book = new Book();
            book.setName(nameEt.getText().toString());
            book.setAutherName(authorNameEt.getText().toString());

            booksDao.addBook(book);
        });
    }
}
