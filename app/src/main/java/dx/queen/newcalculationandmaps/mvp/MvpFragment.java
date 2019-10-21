package dx.queen.newcalculationandmaps.mvp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class MvpFragment<T extends MvpContract.Presenter> extends Fragment implements MvpContract.View {
    protected T presenter;

//    @Override
//    public void onStart() {
//        super.onStart();
//        presenter.subscribe(this);
//    }
//
//    @Override
//    public void onStop() {
//        presenter.unsubscribe();
//        super.onStop();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.subscribe(this);
    }

    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void showToast(int msgResId) {
        Toast.makeText(getActivity(), msgResId, Toast.LENGTH_LONG).show();
    }
}
