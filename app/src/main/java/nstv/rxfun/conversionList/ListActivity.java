package nstv.rxfun.conversionList;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nstv.rxfun.R;
import nstv.rxfun.data.model.Conversion;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    //TODO: add Disposable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        loadData();
    }

    private void init() {
        //Init adapter
        adapter = new ListAdapter();

        //Init recyclerView
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private List<Conversion> getDummyData(String to) {
        List<Conversion> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new Conversion("Coin " + i, "", to, "", i % 2 - 1, i + "", i));
        }
        SystemClock.sleep(1000);
        return items;
    }

    private void loadData() {
        //TODO: load list data
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO: clean up disposable
    }
}
