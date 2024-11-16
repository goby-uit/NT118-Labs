package ex.g1.cau1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private final List<student> studentList;
    EditText idEditText, nameEditText, emailEditText;
    CheckBox sexCheckBox;
    DatabaseHandler dbHandler;

    // Constructor để nhận danh sách sinh viên
    public StudentAdapter(List<student> studentList, EditText idEditText, EditText nameEditText, EditText emailEditText, CheckBox sexCheckBox) {
        this.studentList = studentList;
        this.idEditText = idEditText;
        this.nameEditText = nameEditText;
        this.emailEditText = emailEditText;
        this.sexCheckBox = sexCheckBox;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho mỗi item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        AtomicReference<student> currentStudent = new AtomicReference<>(studentList.get(position));

        // Đổ dữ liệu lên View
        holder.nameTextView.setText( " - " + currentStudent.get().getName() + " - ");
        holder.IDTextView.setText(currentStudent.get().getId());
        holder.emailTextView.setText(currentStudent.get().getEmail());
        holder.sexTextView.setText(currentStudent.get().getSex() == 1 ? "Nam" : "Nữ");

        holder.itemView.setOnClickListener(v -> {
            try
            {
                idEditText.setText(currentStudent.get().getId());
                nameEditText.setText(currentStudent.get().getName());
                emailEditText.setText(currentStudent.get().getEmail());
                sexCheckBox.setChecked(currentStudent.get().getSex() == 1 ? true : false);
            }
            catch (Exception e)
            {
                Toast.makeText(holder.itemView.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        holder.IDTextView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), info.class);
            intent.putExtra("id", currentStudent.get().getId());
            intent.putExtra("name", currentStudent.get().getName());
            intent.putExtra("email", currentStudent.get().getEmail());
            intent.putExtra("sex", currentStudent.get().getSex() == 1 ? "Nam" : "Nữ");
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    // ViewHolder giữ tham chiếu đến các view trong item_student.xml
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, sexTextView;
        Button IDTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            // Khởi tạo các view
            nameTextView = itemView.findViewById(R.id.nameTextView);
            IDTextView = itemView.findViewById(R.id.IDButton);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            sexTextView = itemView.findViewById(R.id.sexTextView);
        }
    }
}
