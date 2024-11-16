package ex.g1.cau0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private final ArrayList<Contact> contactList;
    private final LayoutInflater inflater;

    public ContactAdapter(Context context, ArrayList<Contact> contactList) {
        this.contactList = contactList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.idTextView = convertView.findViewById(R.id.IDTextView);
            holder.nameTextView = convertView.findViewById(R.id.nameTextView);
            holder.phoneTextView = convertView.findViewById(R.id.phoneTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Đổ dữ liệu vào View
        Contact currentContact = contactList.get(position);
        holder.idTextView.setText(String.valueOf(currentContact.getId()));
        holder.nameTextView.setText(currentContact.getName());
        holder.phoneTextView.setText(currentContact.getPhoneNumber());

        // Xử lý sự kiện LongClick
        holder.idTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "ID: " + currentContact.getId(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return convertView;
    }

    // ViewHolder giúp tăng hiệu suất bằng cách lưu trữ các view con trong convertView
    private static class ViewHolder {
        TextView nameTextView, idTextView, phoneTextView;
        ImageView avatarImageView;
    }
}
