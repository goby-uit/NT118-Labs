package ex.g1.cau5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThumbnailAdapter extends BaseAdapter {
    private Context context;
    private final Thumbnail[] thumbnails;

    public ThumbnailAdapter(Context context, Thumbnail[] thumbnails) {
        this.context = context;
        this.thumbnails = thumbnails;
    }

    @Override
    public int getCount() {
        return thumbnails.length;
    }

    @Override
    public Object getItem(int position) {
        return thumbnails[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        }

        ImageView imageViewThumbnail = convertView.findViewById(R.id.imageViewThumbnail);
        TextView textViewThumbnailName = convertView.findViewById(R.id.textViewThumbnailName);

        imageViewThumbnail.setImageResource(thumbnails[position].getImageResId());
        textViewThumbnailName.setText(thumbnails[position].getName());

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, parent, false);
        }

        TextView textViewSelectedThumbnail = convertView.findViewById(R.id.textViewSelectedThumbnail);
        textViewSelectedThumbnail.setText(thumbnails[position].getName());

        return convertView;
    }
}

