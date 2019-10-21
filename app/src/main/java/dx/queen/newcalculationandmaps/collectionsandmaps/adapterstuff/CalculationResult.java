package dx.queen.newcalculationandmaps.collectionsandmaps.adapterstuff;

public class CalculationResult {
    private int res;
    private long time = -1;
    private boolean showProgress = false;

    public boolean isTimeDefault() {
        return -1 == time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public int getres() {
        return res;
    }

    public void setres(int res) {
        this.res = res;
    }
}
