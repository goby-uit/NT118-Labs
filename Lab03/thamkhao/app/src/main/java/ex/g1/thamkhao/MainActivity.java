// MainActivity.java
package ex.g1.thamkhao;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DbAdapter dbAdapter;
    private List<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        for (int i = 0; i < 10; i++) {
            dbAdapter.createUser("Tô Công Quân " + i);
        }

        users = getData();
        showData();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("Range")
    private List<String> getData() {
        List<String> users = new ArrayList<>();
        try (Cursor cursor = dbAdapter.getAllUsers()) { // Ensure cursor is closed
            while (cursor.moveToNext()) {
                users.add(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NAME)));
            }
        }
        return users;
    }

    private void showData() {
        ListView lvUser = findViewById(R.id.listView);
        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(this, R.layout.item_user, users);
        lvUser.setAdapter(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}
