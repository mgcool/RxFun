package nstv.rxfun.conversionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import nstv.rxfun.R;
import nstv.rxfun.data.model.Conversion;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private List<Conversion> items;

    public ListAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Conversion> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<Conversion> getItems() {
        return items;
    }

    public Conversion getItem(int position) {
        return items.get(position);
    }
}
