package ex.g1.cau6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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

    private final List<Employee> employeeList = new ArrayList<>();
    private EmployeeAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmployeeAdapter(this, employeeList);
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addBtn);
        EditText fullNameEditText = findViewById(R.id.nameEditText);
        CheckBox isManagerCheckBox = findViewById(R.id.isManagerCheckBox);
        EditText idEditText = findViewById(R.id.idEditText);

        addButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString();
            boolean isManager = isManagerCheckBox.isChecked();

            if (!fullName.isEmpty() && !idEditText.getText().toString().isEmpty()) {
                employeeList.add(new Employee(fullName, isManager));
                adapter.notifyDataSetChanged();

                idEditText.setText("");
                fullNameEditText.setText("");
                isManagerCheckBox.setChecked(false);
                hideKeyboard(v);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
