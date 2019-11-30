package dx.queen.newcalculationandmaps.ui.fragments;

import dagger.Module;
import dagger.Provides;
import dx.queen.newcalculationandmaps.dto.Modes;
import dx.queen.newcalculationandmaps.model.AppComponent;

@Module
public class FragmentModule {
    private String mode;

    public FragmentModule(String mode) {
        this.mode = mode;
    }

    @Provides
    CollectionFragmentContract.Presenter providePresenter() {
        final AppComponent component = AppInstance.getInstance().getAppComponent();
        return new CollectionsPresenter(mode.equals(Modes.MAPS) ? component.injectMapsTaskSupplier() : component.injectColletionsTaskSupplier(), component.provideCalculator());
    }
}
