package com.example.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button addBtn;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private BooksDao booksDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        addBtn = findViewById(R.id.add_btn);

        addBtn.setOnClickListener(view -> startActivity(new Intent(this, SecondActivity.class)));

        booksDao = TestDb.getAppDatabase(this).booksDao();

        TestAdapter testAdapter = new TestAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(testAdapter);

        compositeDisposable.add(booksDao.getBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (data == null || data.size() == 0) {
                        Toast.makeText(this, "Empty data", Toast.LENGTH_SHORT).show();
                    } else {
                        testAdapter.setData(data);
                    }
                }));


        Pres pres = new Pres();
        Pres pres_2 = new Pres();

        pres.getData().subscribe(o -> Log.d("Test", "Item : " + o));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}