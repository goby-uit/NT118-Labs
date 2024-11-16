package ex.g1.cau4;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private final List<Employee> EmployeeList;
    private final Context context;

    public EmployeeAdapter(Context context, List<Employee> EmployeeList) {
        this.context = context;
        this.EmployeeList = EmployeeList;
    }

    @Override
    public int getCount() {
        return EmployeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return EmployeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        }

        Employee e = EmployeeList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.item_employee_tv_fullname);
        TextView roleTextView = convertView.findViewById(R.id.item_employee_tv_position);
        ImageView managerImageView = convertView.findViewById(R.id.item_employee_iv_manager);

        nameTextView.setText(e.getFullName() );

        if (e.isManager()) {
            managerImageView.setVisibility(View.VISIBLE);
            roleTextView.setVisibility(View.GONE);
        } else {
            managerImageView.setVisibility(View.GONE);
            roleTextView.setVisibility(View.VISIBLE);
        }
        if(position%2==0)
            convertView.setBackgroundResource(R.color.light_blue);
        else
            convertView.setBackgroundResource(R.color.white);

        return convertView;
    }
}
