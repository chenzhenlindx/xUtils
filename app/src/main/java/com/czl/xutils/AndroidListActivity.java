package com.czl.xutils;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.czl.xutils.model.Result;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AndroidListActivity extends AppCompatActivity {
    LiveData<PagedList<Result>> results;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_list);
        recyclerView = findViewById(R.id.recycler);
        results = new LivePagedListBuilder<>(new DataSource.Factory<String, Result>() {
            @NonNull
            @Override
            public DataSource<String, Result> create() {
                ResultDataSource dataSource=new ResultDataSource();
                return dataSource;
            }
        }, 10).build();
        ResultAdapter adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);
    }

    static class ResultAdapter extends PagedListAdapter<Result, ResultViewHolder> {
        static final DiffUtil.ItemCallback<Result> diffCallback = new DiffUtil.ItemCallback<Result>() {
            @Override
            public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                return oldItem.get_id().equals(newItem.get_id());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                return oldItem.equals(newItem);
            }
        };

        protected ResultAdapter() {
            super(diffCallback);
        }

        @NonNull
        @Override
        public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        }
    }

    private class ResultViewHolder extends RecyclerView.ViewHolder {
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
