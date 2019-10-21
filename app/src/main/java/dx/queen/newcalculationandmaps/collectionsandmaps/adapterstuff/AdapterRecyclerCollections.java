package dx.queen.newcalculationandmaps.collectionsandmaps.adapterstuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dx.queen.collectionsandmaps.R;

public class AdapterRecyclerCollections extends RecyclerView.Adapter<AdapterRecyclerCollections.ViewHolderTime> {

    //  private ArrayList<String> mainList;
    private List<CalculationResult> items;

    public AdapterRecyclerCollections(ArrayList<CalculationResult> items) {
        this.items = items == null ? new ArrayList<CalculationResult>() : items;

    }

    @NonNull
    @Override
    public ViewHolderTime onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model, viewGroup, false);

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

    public void setItems(List<CalculationResult> namesAndTimes) {
        items.clear();
        items.addAll(namesAndTimes);
    }

    public void showProgress() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setShowProgress(false);
            notifyDataSetChanged();
        }
    }

    public class ViewHolderTime extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;
        private final TextView nameAndTime;

        public ViewHolderTime(@NonNull View itemView) {
            super(itemView);
            nameAndTime = itemView.findViewById(R.id.tv_name);
            progressBar = itemView.findViewById(R.id.progressBar);

        }

        void bind(CalculationResult item) {
            nameAndTime.setText(item.getres());
            progressBar.setVisibility(item.isShowProgress() ? View.VISIBLE : View.VISIBLE);
        }

    }
}