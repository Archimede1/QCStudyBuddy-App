package com.cornely.qcstudybuddy.appactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


import com.cornely.qcstudybuddy.R;
import com.cornely.qcstudybuddy.classes.Course;
import com.cornely.qcstudybuddy.classes.CourseCatalog;

public class CourseActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        String[] identifiers = getResources().getStringArray(R.array.courseIdentifiers);
        String[] names = getResources().getStringArray(R.array.courseName);
        CourseCatalog courseCatalog = new CourseCatalog();
        for(int i = 0; i < identifiers.length; i++) {
            Course newCourse = new Course(names[i], identifiers[i], "0");
            courseCatalog.addToCatalog(newCourse);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, identifiers);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.classSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search for Class");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}