package dx.queen.newcalculationandmaps.ui.fragments;

import dagger.Component;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculatorImpl;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void injectCalculator(TimeCalculatorImpl timeCalculator);

    void injectSupplier(TaskSupplier taskSupplier);
}