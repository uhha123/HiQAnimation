package vn.ray.animation.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.ray.animation.R;
import vn.ray.animation.base.BaseAdapter;
import vn.ray.animation.model.FileModel;

/**
 * Created by Ray on 10/28/17.
 */

public class FileAdapter extends BaseAdapter<FileModel, FileAdapter.FileVH> {

    @Override
    public FileVH getViewHolder(ViewGroup parent, int viewType) {
        return new FileVH(LayoutInflater.from(context).inflate(R.layout.layout_item_file, parent, false));
    }

    @Override
    public void onBindViewHolder(FileVH holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(position);
    }

    class FileVH extends RecyclerView.ViewHolder {


        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.image)
        ImageView image;

        public FileVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(final int position) {
            final FileModel file = getList().get(position);
            if (file == null)
                return;
            tvTitle.setText(file.getTitle());
            Glide.with(context).load(file.getIcon()).into(image);
            image.setBackground(ContextCompat.getDrawable(context, file.getBgColor()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onItemClickedListener(view, file, position);
                }
            });
        }
    }
}
