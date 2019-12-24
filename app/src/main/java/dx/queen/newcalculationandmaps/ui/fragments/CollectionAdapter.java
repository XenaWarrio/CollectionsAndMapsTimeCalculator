package dx.queen.newcalculationandmaps.ui.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        ViewHolderTime(@NonNull View itemView) {
            super(itemView);
            nameAndTime = itemView.findViewById(R.id.tv_name);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        void bind(CalculationResult item) {
            final String s = nameAndTime.getContext().getString(item.getRes()) + (item.isTimeDefault() ? "-:-" : (item.time + " ms"));
            nameAndTime.setText(s);
            progressBar.setVisibility(item.isShowProgress() ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
