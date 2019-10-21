package dx.queen.newcalculationandmaps.collectionsandmaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dx.queen.collectionsandmaps.adapterstuff.CalculationResult;
import dx.queen.collectionsandmaps.presenter.FragmentInjector;
import dx.queen.collectionsandmaps.presenter.Presenter;


public class CollectionsFragment extends Fragment  implements MainInterface.View{

    private static String MAIN_MODE = "mode";

    @NonNull
    @BindView(R.id.et_operations)
    EditText countOfElement;
    @NonNull
    @BindView(R.id.et_threads)
    EditText countOfThreads;
    @NonNull
    @BindView(R.id.bt_start)
    Button start;
    @NonNull
    @BindView(R.id.recycler)
    RecyclerView rv;

    String mode;
    Presenter presenter;
    Unbinder unbinder;
    String elements;
    String threads;

    public static CollectionsFragment newInstance(String mode) {
        Bundle args = new Bundle();
        args.putString(MAIN_MODE, mode);

        CollectionsFragment fragment = new CollectionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = FragmentInjector.createPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collections, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elements = countOfElement.getText().toString();
                threads = countOfThreads.getText().toString();
                mode = getArguments().getString(MAIN_MODE);
                presenter.buttonWasClicked(elements, threads, mode);

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        RecyclerView.Adapter currentAdapter = rv.getAdapter();
        if (currentAdapter != null)
            currentAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void setItems(List<CalculationResult> results) {

    }
}
