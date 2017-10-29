package vn.ray.animation.base;

import android.view.View;

/**
 * Created by Ray on 10/28/17.
 */

public interface BaseListener {
    interface OnItemClickedListener<T> {
        void onItemClickedListener(View view, T t, int position);
    }

    interface OnAnimationListener{
        void onAnimationListener();
    }

    interface OnPopupMenuListener{
        void onRemove();
        void onShare();
    }
}
