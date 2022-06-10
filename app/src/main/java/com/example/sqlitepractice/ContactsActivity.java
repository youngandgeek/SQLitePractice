package com.example.sqlitepractice;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.sqlitepractice.contacts.ContactsModelClass;
import com.example.sqlitepractice.contacts.DatabaseAdapter;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    // creating variables for our array list,
    // dbadapter, recyclerviewadapter and recycler view.
    private ArrayList<ContactsModelClass> contactsModalArrayList;
    private DatabaseAdapter dbAdapter;
    private ContactsRvAdapter contactsRVAdapter;
    private RecyclerView contactsRv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // initializing our all variables.
        contactsModalArrayList = new ArrayList<>();
        dbAdapter = new DatabaseAdapter(ContactsActivity.this);

        // getting our course array
        // list from db handler class.
        contactsModalArrayList = dbAdapter.readContacts();

        // on below line passing our array lost to our adapter class.
        contactsRVAdapter = new ContactsRvAdapter(contactsModalArrayList, ContactsActivity.this);
        contactsRv = findViewById(R.id.contacts_rv);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ContactsActivity.this, RecyclerView.VERTICAL, false);
        contactsRv.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        contactsRv.setAdapter(contactsRVAdapter);


    }
        /**

         public class ViewCourses extends AppCompatActivity {

         // creating variables for our array list,
         // dbhandler, adapter and recycler view.
         private ArrayList<CourseModal> courseModalArrayList;
         private DBHandler dbHandler;
         private CourseRVAdapter courseRVAdapter;
         private RecyclerView coursesRV;

         @Override
         protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_view_courses);

         // initializing our all variables.
         courseModalArrayList = new ArrayList<>();
         dbHandler = new DBHandler(ViewCourses.this);

         // getting our course array
         // list from db handler class.
         courseModalArrayList = dbHandler.readCourses();

         // on below line passing our array lost to our adapter class.
         courseRVAdapter = new CourseRVAdapter(courseModalArrayList, ViewCourses.this);
         coursesRV = findViewById(R.id.idRVCourses);

         // setting layout manager for our recycler view.
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewCourses.this, RecyclerView.VERTICAL, false);
         coursesRV.setLayoutManager(linearLayoutManager);

         // setting our adapter to recycler view.
         coursesRV.setAdapter(courseRVAdapter);
         }
         }

         */
    }
