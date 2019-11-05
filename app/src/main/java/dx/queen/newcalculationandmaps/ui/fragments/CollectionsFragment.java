package dx.queen.newcalculationandmaps.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;

public class CollectionsFragment extends Fragment implements CollectionFragmentContract.View, CompoundButton.OnCheckedChangeListener {

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

    @Inject
    CollectionFragmentContract.Presenter presenter;




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
        String mode = getArguments().getString(MAIN_MODE);
        DaggerFragmentComponent.builder().fragmentModule(new FragmentModule(mode))
                .build().injectPresenter(this);
        presenter.subscribe(this);
        showProgress(false);

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
        presenter.stopCalculation(false);
        unbinder.unbind();
        super.onDestroy();
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
    public void showProgress(boolean mode) {
        adapter.showProgress(mode);
    }

    @Override
    public void calculationStopped() {
        start.setChecked(false);
    }

    @Override
    public void setThreadsError(String error) {
        countOfThreads.setError(error);
    }

    @Override
    public void setElemntsError(String error) {
        countOfElement.setError(error);
    }

    @Override
    public String getString(Integer strResId) {
        return Objects.requireNonNull(getContext()).getString(strResId);
    }

//
//       @Override
//   public void onStart() {
//        super.onStart();
//        presenter.subscribe(this);
//    }
//
    @Override
    public void onStop() {
        presenter.unsubscribe();
        super.onStop();
    }
//
//    @Override
//    public void onDestroy() {
//        presenter.unsubscribe();
//        super.onDestroy();
//    }

    @Override
    public void showToast(int msgResId) {
        Toast.makeText(getActivity(), msgResId, Toast.LENGTH_LONG).show();
    }



}
