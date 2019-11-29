package dx.queen.newcalculationandmaps;

import androidx.annotation.Nullable;

public class TextUtils {
    public static boolean isEmpty(@Nullable CharSequence text){
        return text == null || text.length() == 0;
    }
}
