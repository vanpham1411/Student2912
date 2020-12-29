package com.example.student2912;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import io.bloco.faker.Faker;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<StudentItem> items;
    SQLiteDatabase db;
    ItemAdapter adapter;
    SearchView searchView;
    private final int UPDATE = 101;
    private final int DELETE = 102;
    private final int ADD = 006;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String dataPath = getFilesDir() + "/student_data";
        items = new ArrayList<>();
//khoi tao thong tin
        if(dataPath.isEmpty()) {
            createRandomData();
            db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        else{
            db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String sq1 = "select * from sinhvien";
            Cursor c1 = db.rawQuery(sq1,null);
            c1.moveToPosition(-1);
            while ( c1.moveToNext()){
                String mssv = c1.getString(0);
                String hoten = c1.getString(1);
                String ngaysinh = c1.getString(2);
                String email = c1.getString(3);
                String diachi = c1.getString(4);
                items.add(new StudentItem(mssv,hoten,ngaysinh, email,diachi));
                Log.v("tag/",ngaysinh);
            }

        }
        adapter = new ItemAdapter(this, items);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentItem item = (StudentItem) listView.getItemAtPosition(position);
                go2Details( item);
            }
        });


        //context MENU
        registerForContextMenu(listView);
        listView.setLongClickable(true);
    }
//tao thong tin sinh vien
    private void createRandomData() {
        db.beginTransaction();
        try {
            db.execSQL("create table sinhvien(" +
                    "mssv char(8) primary key," +
                    "hoten text," +
                    "ngaysinh date," +
                    "email text," +
                    "diachi text);");
            Faker faker = new Faker();
            for (int i = 0; i < 50; i++) {
                String mssv = "2017" + faker.number.number(4);
                String hoten = faker.name.name();
                String ngaysinh = faker.date.birthday(18, 22).toString();
                String email = faker.internet.email();
                String diachi = faker.address.city() + ", " + faker.address.country();
                String sql = String.format("insert into sinhvien(mssv, hoten, ngaysinh, email, diachi) " +
                        "values('%s', '%s', '%s', '%s', '%s')", mssv, hoten, ngaysinh, email, diachi);

                db.execSQL(sql);
                items.add(new StudentItem(mssv,hoten,ngaysinh,email,diachi));
            }

            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

// thong tin chi tiet ve sinh vien
    private void go2Details(StudentItem item){
        if(item == null) return;
        Intent myintent  = new Intent(MainActivity.this, DetailActivity.class);

        Bundle bundle = new Bundle();

        bundle.putString("mssv", item.getMssv());
        bundle.putString("hoten",item.getHoten());
        bundle.putString("ngaysinh", item.getNgaysinh());
        bundle.putString("email",item.getDiachi());
        bundle.putString("diachi", item.getEmail());

        myintent.putExtras(bundle);
        startActivityForResult(myintent,222);

    }

    private void go2Intent(StudentItem item){
        if(item == null) return;
        Intent myintent  = new Intent(MainActivity.this, UpdateActivity.class);

        Bundle bundle = new Bundle();

        bundle.putString("mssv", item.getMssv());
        bundle.putString("hoten",item.getHoten());
        bundle.putString("ngaysinh", item.getNgaysinh());
        bundle.putString("email",item.getDiachi());
        bundle.putString("diachi", item.getEmail());

        myintent.putExtras(bundle);
        startActivityForResult(myintent,UPDATE);

    }


//menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if(i == R.id.action_add){
            Intent myintent  = new Intent(MainActivity.this, EditStudent.class);
            startActivityForResult(myintent, ADD);
        }
        return super.onOptionsItemSelected(item);
    }

    private class Click1 implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle myAdd = data.getExtras();
                String studentID = myAdd.getString("mssv");
                String add_hoten = myAdd.getString("hoten");
                String add_ngaysinh = myAdd.getString("ngaysinh");
                String add_email = myAdd.getString("email");
                String add_diachi = myAdd.getString("diachi");

                String sq2 = "select * from sinhvien";
                Cursor c1 = db.rawQuery(sq2, null);
                c1.moveToPosition(-1);
                while (c1.moveToNext()) {
                    if (c1.getString(0) == studentID) {
                        Toast.makeText(getBaseContext(), "khong the them", Toast.LENGTH_LONG);
                        return;
                    }
                }
                String sql = String.format("insert into sinhvien(mssv, hoten, ngaysinh, email, diachi) " +
                        "values('%s', '%s', '%s', '%s', '%s')", studentID, add_hoten, add_ngaysinh, add_email, add_diachi);
                db.execSQL(sql);
                items.add(new StudentItem(studentID, add_hoten, add_ngaysinh, add_email, add_diachi));
            }
            else if(requestCode == UPDATE){

            }
        }


    }
//context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Select action");
        menu.add(0,101, 0,"Update");
        menu.add(0,102,0,"Delete");
}
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.v("tag/","thu tu" + info.position);

        if(item.getItemId() == UPDATE){
            go2Intent(items.get(info.position));

        }
        if(item.getItemId() == DELETE){
            showMyAlertDialog(this,items.get(info.position));
        }
        return super.onContextItemSelected(item);
    }
    private void showMyAlertDialog(MainActivity mainActivity,StudentItem studentItem){
        new AlertDialog.Builder(mainActivity)
            .setTitle("Terminator")
                .setMessage("do you want to delete")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(studentItem);
                        db.delete("sinhvien","mssv=? ",new String[]{studentItem.getMssv()});

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }
    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}