package dx.queen.newcalculationandmaps;

import javax.inject.Named;

import dagger.Component;
import dx.queen.newcalculationandmaps.model.AppComponent;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;

@Component(modules = {TestAppModule.class})
public interface TestAppComponent extends AppComponent {
    TimeCalculator provideCalculator();

    @Named("Maps")
    TaskSupplier injectMapsTaskSupplier();

    @Named("Collections")
    TaskSupplier injectColletionsTaskSupplier();
}
