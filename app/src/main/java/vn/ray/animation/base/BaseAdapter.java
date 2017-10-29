package vn.ray.animation.base;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import vn.ray.animation.R;

/**
 * Created by Ray on 10/28/17.
 */

public abstract class BaseAdapter<MODEL, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public BaseListener.OnItemClickedListener<MODEL> listener;
    public Context context;
    private List<MODEL> list = new ArrayList<>();
    private int animationId = R.anim.slide_to_up;

    public abstract VH getViewHolder(ViewGroup parent, int viewType);

    public void setListener(BaseListener.OnItemClickedListener<MODEL> listener) {
        this.listener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return getViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        setAnimation(holder.itemView, position, animationId);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private void setAnimation(View view, int position, int animRes) {
        Animation animation = AnimationUtils.loadAnimation(context, animRes);
        view.startAnimation(animation);
    }

    public void setAnimationId(int animationId) {
        this.animationId = animationId;
    }

    public List<MODEL> getList() {
        return list;
    }

    public void setList(List<MODEL> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void add(MODEL data) {
        if (data == null)
            return;
        list.add(data);
        notifyItemInserted(list.size());
    }

    public void remove(int position) {
        if (position < 0)
            return;
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}
