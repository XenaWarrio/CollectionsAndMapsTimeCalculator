package dx.queen.newcalculationandmaps.collectionsandmaps.adapterstuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dx.queen.collectionsandmaps.R;

public class AdapterRecyclerMaps extends RecyclerView.Adapter<AdapterRecyclerMaps.ViewHolderTime> {

    private ArrayList<String> mainList;

    public AdapterRecyclerMaps(ArrayList<String> mainList) {
        this.mainList = mainList;
    }


    @NonNull
    @Override
    public ViewHolderTime onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model, viewGroup, false);
        return new ViewHolderTime(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTime viewHolderTime, int i) {
        viewHolderTime.bind(mainList.get(i));
        //
    }


    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public void setItems(ArrayList<String> namesAndTimes) {
        mainList.addAll(namesAndTimes);
    }


    public static class ViewHolderTime extends RecyclerView.ViewHolder {
        TextView nameAndTime;

        public ViewHolderTime(@NonNull View itemView) {
            super(itemView);
            nameAndTime = itemView.findViewById(R.id.tv_name);
        }

        public void bind(String string) {
            nameAndTime.setText(string);

        }
    }
}

