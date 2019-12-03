package dx.queen.newcalculationandmaps;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dx.queen.newcalculationandmaps.dto.task.ListTaskDataTest;
import dx.queen.newcalculationandmaps.dto.task.MapTaskDataTest;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculatorImplTest;
import dx.queen.newcalculationandmaps.model.supplier.CollectionsTasksSupplier;
import dx.queen.newcalculationandmaps.model.supplier.MapsTasksSupplier;
import dx.queen.newcalculationandmaps.ui.fragments.CollectionsPresenterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CollectionsPresenterTest.class,
        TimeCalculatorImplTest.class,
        ListTaskDataTest.class,
        MapTaskDataTest.class,
        CollectionsTasksSupplier.class,
        MapsTasksSupplier.class
})
public class TestSuit {
}
