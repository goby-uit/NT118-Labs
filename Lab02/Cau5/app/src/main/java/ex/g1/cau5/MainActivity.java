package ex.g1.cau5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerThumbnail;
    private CheckBox checkBoxPromotion;
    private GridView gridView;
    private List<Dish> dishList;
    private DishAdapter dishAdapter;
    private ThumbnailAdapter thumbnailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        editTextName = findViewById(R.id.editTextName);
        spinnerThumbnail = findViewById(R.id.spinnerThumbnail);
        checkBoxPromotion = findViewById(R.id.checkBoxPromotion);
        gridView = findViewById(R.id.gridView);

        dishList = new ArrayList<>();
        dishAdapter = new DishAdapter(this, dishList);
        gridView.setAdapter(dishAdapter);

        thumbnailAdapter = new ThumbnailAdapter(this, Thumbnail.values());
        spinnerThumbnail.setAdapter(thumbnailAdapter);

        findViewById(R.id.buttonAddDish).setOnClickListener(this::addNewDish);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void addNewDish(View v) {
        String name = editTextName.getText().toString().trim();
        Thumbnail selectedThumbnail = (Thumbnail) spinnerThumbnail.getSelectedItem();
        boolean isPromotion = checkBoxPromotion.isChecked();

        if (!name.isEmpty()) {
            dishList.add(new Dish(name, selectedThumbnail, isPromotion));
            dishAdapter.notifyDataSetChanged();

            editTextName.setText("");
            spinnerThumbnail.setSelection(0);
            checkBoxPromotion.setChecked(false);
            hideKeyboard(v);
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}