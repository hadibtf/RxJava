package com.example.rxjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestVH> {
    private List<Book> data = new ArrayList<>();

    public void setData(List<Book> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestVH holder, int position) {
        holder.update(data.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TestVH extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView autherNameTv;

        public TestVH(View view) {
            super(view);
            nameTv = view.findViewById(R.id.name_tv);
            autherNameTv = view.findViewById(R.id.auther_name_tv);
        }

        public void update(Book book) {
            nameTv.setText(book.getName());
            autherNameTv.setText(book.getAutherName());
        }
    }
}
