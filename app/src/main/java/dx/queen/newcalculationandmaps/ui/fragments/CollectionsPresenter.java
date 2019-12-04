package dx.queen.newcalculationandmaps.ui.fragments;


import java.util.concurrent.Executors;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import dx.queen.newcalculationandmaps.util.TextUtils;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class CollectionsPresenter extends AbstractPresenter<CollectionFragmentContract.View> implements CollectionFragmentContract.Presenter {

    private final TaskSupplier tasksSupplier;
    private final TimeCalculator calculator;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CollectionsPresenter(TaskSupplier tasksSupplier, TimeCalculator calculator) {
        this.tasksSupplier = tasksSupplier;
        this.calculator = calculator;
    }

    @Override
    public int getCollectionsCount() {
        return tasksSupplier.getCollectionCount();
    }

    @Override
    public void getInitialResults() {
        if (view != null) {
            view.setItems(tasksSupplier.getInitialResults());
        }
    }

    @Override
    public void startCalculation(String elements, String threads) {
        boolean flag = true;
        if (TextUtils.isEmpty(threads)) {
            view.setThreadsError(view.getString(R.string.threads_empty));
            flag = false;
        } else if ("0".equals(threads)) {
            view.setThreadsError(view.getString(R.string.threads_zero));
            flag = false;
        } else {
            view.setThreadsError(null);

        }

        if (TextUtils.isEmpty(elements)) {
            view.setElementsError(view.getString(R.string.elements_empty));
            flag = false;

        } else if ("0".equals(elements)) {
            view.setElementsError(view.getString(R.string.elements_zero));
            flag = false;

        } else {
            view.setElementsError(null);
        }

        if (!flag) return;

        final int threadsInt = Integer.parseInt(threads);// НУ ПОЧЕМУ??
        final int elementsInt = Integer.parseInt(elements);// может экстрасенсы знают ответ...
        final Scheduler schedulers = Schedulers.from(Executors.newFixedThreadPool(threadsInt));

        stopCalculation(false);

        compositeDisposable.add(Observable.fromIterable(tasksSupplier.getTasks())
                .doOnSubscribe(v -> {
                    if (view != null) view.showProgress(true);
                })
                .doFinally(() -> stopCalculation(true))
                .map(taskData -> {
                    taskData.fill(elementsInt);
                    calculator.execAndSetupTime(taskData);
                    return taskData.getResult();
                })
                .observeOn(schedulers)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(calculationResult -> {
                    if (view != null) view.setupResult(calculationResult);
                }));

        view.btnToStart();
    }


    @Override
    public void stopCalculation(boolean showMsg) {
        if (compositeDisposable.size() == 0) return;
        compositeDisposable.clear();

        if (view == null) return;

        view.showProgress(false);

        view.calculationStopped();

        if (showMsg) {
            view.showToast(R.string.calculation_stopped);
        }
    }
}

