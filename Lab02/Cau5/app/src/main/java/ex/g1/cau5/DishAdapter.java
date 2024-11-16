package ex.g1.cau5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private final List<Dish> dishes;

    public DishAdapter(Context context, List<Dish> dishes) {
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int position) {
        return dishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dish, parent, false);
        }

        Dish dish = dishes.get(position);
        ImageView imageViewDish = convertView.findViewById(R.id.imageViewDish);
        TextView textViewDishName = convertView.findViewById(R.id.textViewDishName);
        ImageView imageViewPromotion = convertView.findViewById(R.id.imageViewPromotion);

        imageViewDish.setImageResource(dish.getThumbnail().getImageResId());
        textViewDishName.setText(dish.getName());

        if (dish.isPromotion()) {
            imageViewPromotion.setVisibility(View.VISIBLE);
        } else {
            imageViewPromotion.setVisibility(View.GONE);
        }

        return convertView;
    }
}

