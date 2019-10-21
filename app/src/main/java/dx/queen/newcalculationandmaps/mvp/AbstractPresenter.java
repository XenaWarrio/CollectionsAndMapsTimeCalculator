package dx.queen.newcalculationandmaps.mvp;

public class AbstractPresenter<T extends MvpContract.View> implements MvpContract.Presenter<T> {
    protected T view;

    @Override
    public void subscribe(T view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        view = null;
    }
}
