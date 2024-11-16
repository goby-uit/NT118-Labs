package ex.g1.cau0;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
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
    ArrayList<Contact> contactList ;
    ContactAdapter adapter;
    ListView listView;
    DatabaseHandler dbhandler;
    SQLiteDatabase db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbhandler = new DatabaseHandler(this);
        db = openOrCreateDatabase("contactsManager", MODE_PRIVATE, null);
        try {
            dbhandler.onUpgrade(db, 0, 1);
        }
        catch (Exception e)
        {
            Log.e("Error", "Table đã tồn tại");
        }

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        dbhandler.addContact(new Contact(1,"Tô Công Quân", "22521190"), db);
        dbhandler.addContact(new Contact(2,"Srinivas", "9199999999"), db);
        dbhandler.addContact(new Contact(3,"Tommy", "9522222222"), db);
        dbhandler.addContact(new Contact(4,"Karthik", "9533333333"), db);
        dbhandler.addContact(new Contact(5,"Sandeep", "9544444444"), db);
        dbhandler.addContact(new Contact(6,"Raju", "9555555555"), db);
        dbhandler.addContact(new Contact(7,"Ravi", "9566666666"), db);
        dbhandler.addContact(new Contact(8,"Satish", "9577777777"), db);
        dbhandler.addContact(new Contact(9,"Ravi", "9588888888"), db);
        dbhandler.addContact(new Contact(10,"Ravi", "9599999999"), db);

        // Reading all contacts
        Log.e("Reading: ", "Reading all contacts..");
        contactList = dbhandler.getAllContacts();

        for (Contact cn : contactList) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + ",Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }

        listView = findViewById(R.id.listView);
        contactList = dbhandler.getAllContacts();
        adapter = new ContactAdapter(this, contactList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Contact contact = contactList.get(position);

                dbhandler.deleteContact(contact, db);

                contactList.remove(position);
                adapter.notifyDataSetChanged();

                // Hiển thị thông báo xác nhận
                Toast.makeText(MainActivity.this, "Đã xóa: " + contact.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}