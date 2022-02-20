package simple.searchrecycleview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import simple.searchrecycleview.Modal.modal;
import simple.searchrecycleview.R;

public class adapter extends RecyclerView.Adapter<adapter.Holder> implements Filterable {
    ArrayList<modal> data;
    ArrayList<modal> backup;

    public adapter(ArrayList<modal> data) {
        this.data = data;
        backup = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.mylayout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv1.setText(data.get(position).getTitle());
        holder.tv2.setText(data.get(position).getDes());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        //background thread
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<modal> filterdata = new ArrayList<>();
            if (keyword.toString().isEmpty()) {
                filterdata.addAll(backup);
            } else {
                //matching
                for (modal obj : backup) {
                    if (obj.getTitle().toString().toLowerCase().contains(keyword.toString().toLowerCase())) {
                        filterdata.add(obj);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdata;
            return results;
        }

        @Override
        // main ui thread
        protected void publishResults(CharSequence keyword, FilterResults filterResults) {
            data.clear();
            data.addAll((ArrayList<modal>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.title);
            tv2 = (TextView) itemView.findViewById(R.id.des);
        }
    }
}
