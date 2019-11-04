package dx.queen.newcalculationandmaps.ui.fragments;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculatorImpl;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;

@Module
public class FragmentModule {
    @Inject
     TaskSupplier tasksSupplier;
    @Inject
     TimeCalculator calculator;
     @Inject
     TimeCalculatorImpl timeCalculator;

    FragmentModule() {
        ApplicationDI.getInstance().getAppComponent().injectCalculator(timeCalculator);
        ApplicationDI.getInstance().getAppComponent().injectSupplier(tasksSupplier);

    }

    @Provides
     CollectionFragmentContract.Presenter providePresenter() {
        return new CollectionsPresenter(tasksSupplier,calculator);
    }
}

