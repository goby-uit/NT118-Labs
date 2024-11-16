package ex.g1.cau1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<student> studentList ;
    StudentAdapter adapter;
    RecyclerView recyclerView;
    EditText idEditText, nameEditText, emailEditText;
    CheckBox sexCheckBox;
    Button addButton, delButton, fixButton;
    SQLiteDatabase db;
    DatabaseHandler dbHandler;

    @SuppressLint({"NotifyDataSetChanged", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //Khoi tao cac doi tuong
        idEditText = findViewById(R.id.IDEditText);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        sexCheckBox = findViewById(R.id.sexCheckBox);
        addButton = findViewById(R.id.addButton);
        delButton = findViewById(R.id.delButton);
        fixButton = findViewById(R.id.fixButton);
        recyclerView = findViewById(R.id.recyclerView);
        dbHandler = new DatabaseHandler(this);
        studentList = dbHandler.getAllStudents();
        adapter = new StudentAdapter(studentList, idEditText, nameEditText, emailEditText, sexCheckBox);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Xu ly database
        db = openOrCreateDatabase("StudentManagement", MODE_PRIVATE, null);
        studentList.clear();
        //Tạo table
        try {
            dbHandler.onUpgrade(db, 0, 1);
        }
        catch (Exception e)
        {
            Log.e("Error", "Table đã tồn tại");
        }

        //Thêm sinh viên
        addButton.setOnClickListener(v -> {
            try {
                String id = idEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                int sex = sexCheckBox.isChecked() ? 1 : 0;
                if (id.isEmpty() || name.isEmpty() || email.isEmpty()){
                    hideKeyboard();
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbHandler.getStudent(Integer.parseInt(id)) != null /*|| isIdInList(id)*/) {
                    Toast.makeText(this, "ID đã tồn tại. Vui lòng nhập ID khác", Toast.LENGTH_SHORT).show();
                    hideKeyboard();
                    return;
                }


                student student = new student(id, name, email, sex);
                dbHandler.addStudent(student, db);
                studentList.clear();
                studentList.addAll(dbHandler.getAllStudents());
                //studentList.add(student);
                adapter.notifyDataSetChanged();
                hideKeyboard();
                idEditText.setText("");
                nameEditText.setText("");
                emailEditText.setText("");
                sexCheckBox.setChecked(false);
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                hideKeyboard();
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();

            }

        });

        //Xóa sinh viên
        delButton.setOnClickListener(v -> {
            try {
                String id = idEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                int sex = sexCheckBox.isChecked() ? 1 : 0;
                if (id.isEmpty()){
                    Toast.makeText(this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbHandler.getStudent(Integer.parseInt(id)) == null) {
                    Toast.makeText(this, "ID không tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
                student student = dbHandler.getStudent(Integer.parseInt(id));
                int n = dbHandler.deleteStudent(student, db);
                //studentList.remove(student);
                studentList.clear();
                studentList.addAll(dbHandler.getAllStudents());
                adapter.notifyDataSetChanged();

                hideKeyboard();
                idEditText.setText("");
                nameEditText.setText("");
                emailEditText.setText("");
                sexCheckBox.setChecked(false);
                Toast.makeText(this, "Xóa thành công " + n + " sinh viên" , Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        //cập nhật sinh viên
        fixButton.setOnClickListener(v -> {
            String id = idEditText.getText().toString();
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            int sex = sexCheckBox.isChecked() ? 1 : 0;

            if (id.isEmpty() || name.isEmpty() || email.isEmpty()){
                hideKeyboard();
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (dbHandler.getStudent(Integer.parseInt(id)) == null) {
                hideKeyboard();
                Toast.makeText(this, "ID không tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }
            student student = dbHandler.getStudent(Integer.parseInt(id));
            student.setName(name);
            student.setEmail(email);
            student.setSex(sex);
            int n = dbHandler.updateStudent(student);
            studentList.clear();
            studentList.addAll(dbHandler.getAllStudents());
            adapter.notifyDataSetChanged();
            hideKeyboard();
            idEditText.setText("");
            nameEditText.setText("");
            emailEditText.setText("");
            sexCheckBox.setChecked(false);
            Toast.makeText(this, "Cập nhật thành công " + n + " sinh viên", Toast.LENGTH_SHORT).show();

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean isIdInList(String id) {
        for (student s : studentList) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


}