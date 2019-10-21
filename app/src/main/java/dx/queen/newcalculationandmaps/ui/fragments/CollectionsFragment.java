package dx.queen.newcalculationandmaps.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.mvp.MvpFragment;

public class CollectionsFragment extends MvpFragment<CollectionFragmentContract.Presenter>
        implements CollectionFragmentContract.View, CompoundButton.OnCheckedChangeListener {

    private static String MAIN_MODE = "mode";

    @NonNull
    @BindView(R.id.et_operations)
    EditText countOfElement;
    @NonNull
    @BindView(R.id.et_threads)
    EditText countOfThreads;
    @NonNull
    @BindView(R.id.bt_start)
    ToggleButton start;
    @NonNull
    @BindView(R.id.recycler)
    RecyclerView rv;

    private final Handler handler = new Handler();
    private final CollectionAdapter adapter = new CollectionAdapter();
    private Unbinder unbinder;

    public static CollectionsFragment newInstance(String mode) {
        final Bundle args = new Bundle();
        args.putString(MAIN_MODE, mode);

        final CollectionsFragment fragment = new CollectionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = FragmentInjector.createPresenter(getArguments().getString(MAIN_MODE));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collections, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        rv.setLayoutManager(new GridLayoutManager(getActivity(), presenter.getCollectionsCount()));
        rv.setAdapter(adapter);
        start.setOnCheckedChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getInitialResults();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            presenter.startCalculation(getText(countOfElement), getText(countOfThreads));
        } else {
            presenter.stopCalculation(true);
        }
    }

    private String getText(EditText editText) {
        return editText.getText().toString();
    }

    @Override
    public void setItems(List<CalculationResult> results) {
        adapter.setItems(results);
    }

    @Override
    public void setupResult(CalculationResult result) {
        handler.post(() -> adapter.updateItem(result)); // multithreading!!
    }

    @Override
    public void showProgress() {
        adapter.showProgress();
    }
}
