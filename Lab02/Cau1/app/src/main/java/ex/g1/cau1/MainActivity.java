package ex.g1.cau1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    final String arr[] = {"Teo", "Ty", "Bin", "Bo"};
    int selectedItemPosition = -1;
    View selectedItemView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView selectTextView = findViewById(R.id.selectTextView);
        ListView lv = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new
                ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (selectedItemView != null) {
                            selectedItemView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        }
                        view.setBackgroundColor(getResources().getColor(R.color.purple_200));
                        selectedItemView = view;
                        selectedItemPosition = position;
                        selectTextView.setText("Position: "+ position+ "; value = " + arr[position]);
                    }
                }
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
