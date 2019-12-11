package dx.queen.newcalculationandmaps.ui.fragments;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static android.hardware.camera2.params.BlackLevelPattern.COUNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsPresenterTest extends AbstractPresenter<CollectionFragmentContract.View> {
    private static final int THREADS_INT = 4;
    private static final int ELEMENTS_INT = 6;
    private static final int TASK_COUNT = 2;
    private final Lock lock = new ReentrantLock();


    @Mock
    TaskSupplier tasksSupplier;

    @Mock
    TimeCalculator calculator;

    @Mock
    CollectionFragmentContract.View view;

    private CollectionsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new CollectionsPresenter(tasksSupplier, calculator);
        presenter.subscribe(view);
    }


    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void presenterAndViewNotNull() {
        assertNotNull(presenter);
        assertNotNull(view);
    }

    @Test
    public void testEmptyThreads() {
        final String mockMessage = "Threads can`t be empty";
        Mockito.when(view.getString(R.string.threads_empty)).thenReturn(mockMessage);

        presenter.startCalculation("5", " ");

        verify(view).setElementsError(null);
        verify(view).getString(R.string.threads_empty);
        verify(view).setThreadsError(mockMessage);


        verifyNoMore();
    }

    @Test
    public void testEmptyElements() {
        final String mockMessage = "Elements can`t be empty";
        when(view.getString(R.string.elements_empty)).thenReturn(mockMessage);

        presenter.startCalculation(" ", "5");

        verify(view).setThreadsError(null);
        verify(view).getString(R.string.elements_empty);
        verify(view).setElementsError(mockMessage);

        verifyNoMore();
    }

    @Test
    public void testZeroThreads() {
        final String mockMessage = "Threads can`t be zero";

        when(view.getString(R.string.threads_zero)).thenReturn(mockMessage);

        presenter.startCalculation("5", "0");

        verify(view).setElementsError(null);
        verify(view).getString(R.string.threads_zero);
        verify(view).setThreadsError(mockMessage);

        verifyNoMore();
    }

    @Test
    public void testZeroElements() {
        final String mockMsg = "Threads can`t be zero";
        when(view.getString(R.string.elements_zero)).thenReturn(mockMsg);

        presenter.startCalculation("0", "5");

        verify(view).setThreadsError(null);
        verify(view).getString(R.string.elements_zero);
        verify(view).setElementsError(mockMsg);

        verifyNoMore();
    }

    @Test
    public void testNullThreads() {

        presenter.startCalculation("5", "");

        verify(view).setThreadsError(null);
        verify(view).setElementsError(null);
        verify(view).getString(R.string.threads_empty);


        verifyNoMore();
    }

    @Test
    public void testNullElements() {
        presenter.startCalculation("", "5");

        verify(view).setElementsError(null);
        verify(view).setThreadsError(null);
        verify(view).getString(R.string.elements_empty);

        verifyNoMore();
    }


    @Test
    public void startCalculation() throws InterruptedException {
        lock.lock();
        final List<TaskData> taskDatas = getFakesTasks(ELEMENTS_INT);
        when(tasksSupplier.getTasks()).thenReturn(taskDatas);

        presenter.startCalculation(String.format("%d", COUNT), String.format("%d", THREADS_INT));

        Thread.sleep(5000);

        verify(view, Mockito.times(1)).setElementsError(null); // Закомментировала, потому что пока не очень гребу зачем они здесь, если есть отдельные методы валидации
        verify(view, Mockito.times(1)).setThreadsError(null);

        verify(view, Mockito.times(1)).showProgress(true);
        // verify(view, Mockito.times(1)).showProgress(false);
        // verify(view, Mockito.times(1)).showToast(R.string.calculation_stopped);
        verify(tasksSupplier).getTasks();
        for (TaskData task : taskDatas) {
            verify(task).fill(COUNT);
        }

        verify(calculator, times(taskDatas.size())).execAndSetupTime(any());
        verify(view, times(taskDatas.size())).setupResult(any());
        // verify(view).calculationStopped();
        verify(view).btnToStart();

        verifyNoMore();
        lock.unlock();
    }

    @Test
    public void getCollectionsCount() {
        when(tasksSupplier.getCollectionCount()).thenReturn(TASK_COUNT);

        assertEquals(presenter.getCollectionsCount(), TASK_COUNT);

        Mockito.verify(tasksSupplier).getCollectionCount();
        verifyNoMore();
    }


    @Test
    public void getInitialResults() {
        final List<CalculationResult> data = tasksSupplier.getInitialResults();
        when(tasksSupplier.getInitialResults()).thenReturn(data);

        view.setItems(tasksSupplier.getInitialResults());
        final List<CalculationResult> items = view.getItems();

        assertEquals(items, data);
        verify(view).setItems(data);

        verify(tasksSupplier, times(2)).getInitialResults();
        verify(view).getItems();

        verifyNoMore();

    }

    @Test
    public void stopCalculation_withMessage() {
        lock.lock();
        presenter.startCalculation("1", "1");

        presenter.stopCalculation(true);

        verify(tasksSupplier).getTasks();
        verify(view).showProgress(true);
        verify(view).showProgress(false);
        verify(view).calculationStopped();
        verify(view).setThreadsError(null);
        verify(view).setElementsError(null);
        verify(view).showProgress(true);
        verify(view).btnToStart();
        verify(view).showToast(R.string.calculation_stopped);

        verifyNoMore();
        lock.unlock();
    }

    @Test
    public void stopCalculation_withoutMessage() {
        lock.lock();
        presenter.startCalculation("1", "1");

        presenter.stopCalculation(false);

        verify(tasksSupplier).getTasks();
        verify(view).showProgress(false);
        verify(view).calculationStopped();
        verify(view).setThreadsError(null);
        verify(view).setElementsError(null);
        verify(view).showProgress(true);
        verify(view).btnToStart();

        verifyNoMore();
        lock.unlock();
    }


    private List<TaskData> getFakesTasks(int count) {
        final List<TaskData> tasksData = new ArrayList<>(count);
        for (int i = 0; i <= count; i++) {
            final TaskData mock = Mockito.mock(TaskData.class);
            Mockito.when(mock.getResult()).thenReturn(Mockito.mock(CalculationResult.class));
            tasksData.add(mock);
        }
        return tasksData;
    }


    public void verifyNoMore() {
        Mockito.verifyNoMoreInteractions(calculator);
        Mockito.verifyNoMoreInteractions(tasksSupplier);
        Mockito.verifyNoMoreInteractions(view);
    }
}