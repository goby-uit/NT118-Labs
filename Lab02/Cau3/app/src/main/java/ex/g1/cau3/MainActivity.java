package ex.g1.cau3;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arr = new ArrayList<String>();
    View selectedItemView = null;
    int selectedItemPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RadioButton fullRadio = findViewById(R.id.fullRadio);
        RadioButton partRadio = findViewById(R.id.partRadio);
        TextView selectTextView = findViewById(R.id.selectTextView);
        ListView lv = findViewById(R.id.listView);
        Button buttonInput = findViewById(R.id.buttonInput);
        EditText IDeditText = findViewById(R.id.IDeditText);
        EditText nameeditText = findViewById(R.id.nameeditText);


        ArrayAdapter<String> adapter = new
                ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, arr);
        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(IDeditText.getText().toString().isEmpty()
                        || nameeditText.getText().toString().isEmpty()
                || (!fullRadio.isChecked() && !partRadio.isChecked()))
                    return;
                Employee e = null ;
                if(fullRadio.isChecked()){
                    e = new EmployeeFullTime(IDeditText.getText().toString(), nameeditText.getText().toString());
                }
                else if(partRadio.isChecked()){
                    e = new EmployeeParttime(IDeditText.getText().toString(), nameeditText.getText().toString());
                }
                if(e != null)
                    arr.add(e.ToString());
                IDeditText.setText("");
                nameeditText.setText("");
                fullRadio.setChecked(false);
                partRadio.setChecked(false);
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        lv.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    class Employee{
        String id, name;
        public double TinhLuong(){return 0.0;}
        public String ToString(){return "";}
        public Employee(String id, String name){
            this.id = id;
            this.name = name;
        }
        public String getId(){return id;}
        public String getName(){return name;}
        public void setId(String id){this.id = id;}
        public void setName(String name){this.name = name;}
    };

    class EmployeeFullTime extends Employee{
        public EmployeeFullTime(String id, String name) {
            super(id, name);
        }
        @Override
        public double TinhLuong() {return 500.0;}
        @Override
        public String ToString() {
            return id + " - " + name + "-->FullTime=" + TinhLuong();}
    };

    class EmployeeParttime extends Employee{
        public EmployeeParttime(String id, String name) {
            super(id, name);
        }
        @Override
        public double TinhLuong() {return 150.0;}
        @Override
        public String ToString() {
            return id + " - " + name + "-->PartTime=" + TinhLuong();}
    };
}