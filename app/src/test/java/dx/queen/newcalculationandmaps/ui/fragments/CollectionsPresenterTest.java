package dx.queen.newcalculationandmaps.ui.fragments;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;
import dx.queen.newcalculationandmaps.mvp.AbstractPresenter;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static android.hardware.camera2.params.BlackLevelPattern.COUNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsPresenterTest extends AbstractPresenter<CollectionFragmentContract.View> {
    private static final int THREADS_INT = 4;
    private static final int ELEMENTS_INT = 6;
    private static final int TASK_COUNT = 2;

    @Mock
    TaskSupplier tasksSupplier;

    @Mock
    TimeCalculator calculator;

    @Mock
    CollectionFragmentContract.View view;



    private CollectionsPresenter presenter;

    @Before
    public void setUp(){
        presenter = new CollectionsPresenter(tasksSupplier,calculator);
        presenter.subscribe(view);
    }


    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void presenterAndViewNotNull(){
        assertNotNull(presenter);
        assertNotNull(view);
    }

    @Test
    public void testEmptyThreads(){
        presenter.startCalculation("5", " ");

        Mockito.verify(view).setThreadsError(view.getString(R.string.threads_empty));
        Mockito.when(view.getString(R.string.threads_empty)).thenReturn("Treads can`t be empty");
    }

    @Test
    public void testEmptyElements(){
        presenter.startCalculation(" ", "5");
        when(view.getString(R.string.elements_empty)).thenReturn("Elements can`t be empty");
        verify(view).setElementsError(view.getString(R.string.elements_empty));

    }

    @Test
    public void testZeroThreads(){
        presenter.startCalculation("5", "0");
        when(view.getString(R.string.threads_zero)).thenReturn("Threads can`t be zero");
        verify(view).setThreadsError(view.getString(R.string.threads_zero));

    }

    @Test
    public void testZeroElements(){
        presenter.startCalculation("0", "5");
        when(view.getString(R.string.elements_zero)).thenReturn("Threads can`t be zero");
        verify(view).setElementsError(view.getString(R.string.elements_zero));

    }

    @Test
    public void testNullThreads(){
        presenter.startCalculation("5", "");
        verify(view).setThreadsError(null);

    }

    @Test
    public void testNullElements(){
        presenter.startCalculation("", "5");
       verify(view).setElementsError(null);
    }

    @Test
    public void startCalculation(){

        final List<TaskData> taskDatas = getFakesTasks(ELEMENTS_INT);
        when(tasksSupplier.getTasks()).thenReturn(taskDatas);


         final ArgumentCaptor<List<CalculationResult>> ac = forClass(List.class);
        verify(view).setItems(ac.capture());

        presenter.startCalculation(String.format("%d", COUNT), String.format("%d", THREADS_INT));


        Mockito.when(view.getString(R.string.threads_zero)).thenReturn("Threads can`t be zero");
        verify(view, Mockito.times(2)).setElementsError(null);
        verify(view, never()).setElementsError(any());

        verify(view).showProgress(true);
        verify(view).setItems(ac.capture());
        verify(tasksSupplier).getTasks();
        for (TaskData task : taskDatas) {
            verify(task).fill(COUNT);
        }

        verify(calculator, times(getFakesTasks(COUNT).size())).execAndSetupTime(any());
        verify(view, times(getFakesTasks(COUNT).size())).setupResult(any());
        verify(view).calculationStopped();
        verify(view).btnToStart();



    verifyNoMore();

    }



    @Test
    public void getCollectionsCount() {
        when(tasksSupplier.getCollectionCount()).thenReturn(TASK_COUNT);
        assertTrue(presenter.getCollectionsCount() == TASK_COUNT);

        Mockito.verify(tasksSupplier).getCollectionCount();

    }


    @Test
    public void getInitialResults() {
        assertNotNull(view);
        assertEquals(view.getItems() , tasksSupplier.getInitialResults());
    }



    @Test
    public void stopCalculation() {
    }

    private List<TaskData> getFakesTasks(int count) {
        final List<TaskData> tasksData = new ArrayList<>(count);
        for (int i = 0; i <= count; i++) {
            tasksData.add(Mockito.mock(TaskData.class));
        }
        return tasksData;
    }


    public void verifyNoMore(){
        Mockito.verifyNoMoreInteractions(calculator);
        Mockito.verifyNoMoreInteractions(tasksSupplier);
        Mockito.verifyNoMoreInteractions(view);
    }
}