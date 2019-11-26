package dx.queen.newcalculationandmaps.ui.fragments;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static android.hardware.camera2.params.BlackLevelPattern.COUNT;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsPresenterTest extends AbstractPresenter<CollectionFragmentContract.View> {
    public static final int THREADS_INT = 4;
    public static final int ELEMENTS_INT = 6;
    public static final int TASK_COUNT = 2;

    @Mock
    TaskSupplier tasksSupplier;

    @Mock
    TimeCalculator calculator;

    @Mock
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Mock
    CollectionFragmentContract.View view;

    @Mock
    TaskData taskData;

    CollectionsPresenter presenter;


    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                (Callable<Scheduler> schedulerCallable) -> Schedulers.trampoline());
    }


    @Test
    public void getCollectionsCount() {
        Mockito.when(tasksSupplier.getCollectionCount()).thenReturn(ELEMENTS_INT);
        assertTrue(presenter.getCollectionsCount() == ELEMENTS_INT);

    }

    @Test
    public void getInitialResults() {
        assertNotNull(view);
        Mockito.when(tasksSupplier.getInitialResults()).thenReturn(view.getItems());
    }

    @Test
    public void startCalculation() {
        List<TaskData> taskDatas = getFakesTasks(ELEMENTS_INT);

        verifyMethods(taskDatas);

        Mockito.when(tasksSupplier.getTasks()).thenReturn(taskDatas);

        presenter.startCalculation(String.format("%d", ELEMENTS_INT), String.format("%d", THREADS_INT));


    }

    public void verifyMethods(List<TaskData> taskDatas) {
        verify(view, Mockito.times(0)).setError(anyInt());
        verify(view).showProgress(true);
        verify(tasksSupplier).getTasks();
// here is problem because multithreading!!
        for (TaskData task : taskDatas) {
            verify(task).fill(COUNT);
        }


        verify(calculator, times(getFakesTasks(TASK_COUNT).size())).execAndSetupTime(any());
        verify(view, times(getFakesTasks(TASK_COUNT).size())).setupResult(any());
        verify(view).calculationStopped();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void stopCalculation() {
    }

    List<TaskData> getFakesTasks(int count) {
        List<TaskData> tasksData = new ArrayList<>(count);

        for (int i = 0; i <= count; i++) {

            tasksData.set(i, taskData);

        }
        return tasksData;
    }
}