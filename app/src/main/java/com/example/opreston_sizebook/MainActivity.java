package com.example.opreston_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    private EditText bodyText;
    private ListView personListView;
    public final static String EXTRA_MESSAGE = "com.example.opreston_sizebook";
    static final int PICK_CONTACT_REQUEST = 1;
    private ArrayList<Person> personList;
    private ArrayList<String> stringViewList;
    private ArrayAdapter<String> adapter;
    private int personCount;
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personListView = (ListView) findViewById(R.id.PeopleList);
        personList = new ArrayList<Person>();
        stringViewList = new ArrayList<String>();

        loadFromFile();
        for (Person value: personList){
            personCount = personCount + 1;
            stringViewList.add(value.toStringView());
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, stringViewList);
        personListView.setAdapter(adapter);

        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentUpdate = new Intent(view.getContext(), AddPersonActivity.class);
                Gson gS = new Gson();
                String myPerson = gS.toJson(personList.get(position));
                intentUpdate.putExtra(EXTRA_MESSAGE, myPerson);
                stringViewList.remove(personList.get(position).toStringView());
                personList.remove(personList.get(position));
                personCount = personCount - 1;
                adapter.notifyDataSetChanged();
                startActivityForResult(intentUpdate, PICK_CONTACT_REQUEST);

            }
        });
    }

    /**
     * This function starts the AddPersonActivity when the add person button is selected
     * @param view
     */
    public void addPerson(View view) {
        Intent intent = new Intent(this, AddPersonActivity.class);
        bodyText = (EditText) findViewById(R.id.body);
        String name = bodyText.getText().toString();
        Person personObject = new Person(name);
        Gson gS = new Gson();
        String myPerson = gS.toJson(personObject);
        intent.putExtra(EXTRA_MESSAGE, myPerson);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    /**
     * This method receives data from the AddPersonActivity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Gson gS = new Gson();

                String person = data.getStringExtra("MESSAGE");
                Person personObject = gS.fromJson(person, Person.class);
                if (personObject == null){
                    personListView.setAdapter(adapter);
                    saveInFile();
                } else {
                    personCount = personCount + 1;
                    personList.add(personObject);
                    stringViewList.add(personObject.toStringView());

                    adapter.notifyDataSetChanged();
                    personListView.setAdapter(adapter);
                    saveInFile();
                }
            }
        }
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //Take from
            Type listType = new TypeToken<ArrayList<Person>>() {
            }.getType();
            personList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            personList = new ArrayList<>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(personList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later
            throw new RuntimeException();
        } catch (IOException e) {

            throw new RuntimeException();
        }
    }
}

