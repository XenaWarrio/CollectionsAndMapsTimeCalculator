package dx.queen.newcalculationandmaps.ui.fragments;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dx.queen.newcalculationandmaps.dto.Modes;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculatorImpl;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;

@Module
public class FragmentModule {
    private String mode;

    @Inject
     TaskSupplier tasksSupplier;
     @Inject
     TimeCalculatorImpl timeCalculator;

    public FragmentModule(String mode) {
        this.mode = mode;
    }

    @Provides
     CollectionFragmentContract.Presenter providePresenter() {
        final AppComponent component = ApplicationDI.getInstance().getAppComponent();
        return new CollectionsPresenter(mode.equals(Modes.MAPS ) ? component.injectMapsTaskSupplier(): component.injectColletionsTaskSupplier() , component.provideCalculator());
    }
}
