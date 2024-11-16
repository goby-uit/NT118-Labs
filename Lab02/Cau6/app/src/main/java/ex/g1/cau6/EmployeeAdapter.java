package ex.g1.cau6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private final List<Employee> employeeList;
    private final Context context;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);

        holder.nameTextView.setText(employee.getFullName());

        if (employee.isManager()) {
            holder.managerImageView.setVisibility(View.VISIBLE);
            holder.roleTextView.setVisibility(View.GONE);
        } else {
            holder.managerImageView.setVisibility(View.GONE);
            holder.roleTextView.setVisibility(View.VISIBLE);
        }

        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.light_blue);
        } else {
            holder.itemView.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView roleTextView;
        ImageView managerImageView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_employee_tv_fullname);
            roleTextView = itemView.findViewById(R.id.item_employee_tv_position);
            managerImageView = itemView.findViewById(R.id.item_employee_iv_manager);
        }
    }
}
