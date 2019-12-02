package dx.queen.newcalculationandmaps.ui.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolderTime> {

    private final List<CalculationResult> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolderTime onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model, viewGroup, false);
        return new ViewHolderTime(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTime viewHolderTime, int i) {
        viewHolderTime.bind(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(List<CalculationResult> namesAndTimes) {
        items.clear();
        items.addAll(namesAndTimes);
        notifyDataSetChanged();
    }

    List<CalculationResult> getItems() {
        return items;
    }

    void showProgress(boolean mode) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setShowProgress(mode);
            Log.d("Erroro", "in collection adapter " + items.get(i).isShowProgress());

        }
        notifyDataSetChanged();
    }

    void updateItem(CalculationResult result) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).labelResId == result.labelResId) {
                items.set(i, result);
                notifyItemChanged(i);
                break;
            }
        }
    }

    class ViewHolderTime extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;
        private final TextView nameAndTime;
        View v;

        ViewHolderTime(@NonNull View itemView) {
            super(itemView);
            nameAndTime = itemView.findViewById(R.id.tv_name);
            progressBar = itemView.findViewById(R.id.progressBar);
            this.v = itemView;
        }

        void bind(CalculationResult item) {
            Log.d("Erroro", "bind" + item.isShowProgress());

            final String s = nameAndTime.getContext().getString(item.getRes()) + (item.isTimeDefault() ? "-:-" : (item.time + " ms"));
            nameAndTime.setText(s);
            if (item.isShowProgress()) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
            }
            //progressBar.setVisibility(item.isShowProgress() ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
//сли этот код работает , то его писала ксюша, если нет , то не знаю , кто писал