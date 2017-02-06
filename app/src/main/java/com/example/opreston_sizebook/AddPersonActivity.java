package com.example.opreston_sizebook;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This is my activity class for editing the person and their sizes
 */
public class AddPersonActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private EditText neckText;
    private EditText chestText;
    private EditText waistText;
    private EditText bustText;
    private EditText hipText;
    private EditText inseamText;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        Button addButton = (Button) findViewById(R.id.addInfoButton);
        Button deleteButton = (Button) findViewById(R.id.deleteInfoButton);

        Intent intent = getIntent();
        final String mainData = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        /**
         * This is my delete button method and switches back to the other activity
         * after having deleted the person selected
         */
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Gson gS = new Gson();
                Person personObject = gS.fromJson(mainData, Person.class);
                personObject = null;
                String myPerson = gS.toJson(personObject);

                Intent intent2 = new Intent();
                intent2.putExtra("MESSAGE", myPerson);
                setResult(RESULT_OK, intent2);
                finish();

            }
        });

        /**
         * this is my add button method for adding a person to the list
         * and switches back to the other activity
         */
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePicker = (DatePicker) findViewById(R.id.pickedDate);
                neckText = (EditText) findViewById(R.id.neckBody);
                chestText = (EditText) findViewById(R.id.chestBody);
                waistText = (EditText) findViewById(R.id.waistBody);
                bustText = (EditText) findViewById(R.id.bustBody);
                hipText = (EditText) findViewById(R.id.hipBody);
                inseamText = (EditText) findViewById(R.id.inseamBody);
                commentText = (EditText) findViewById(R.id.commentBody);

                int day = datePicker.getDayOfMonth();
                day = day -1;
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                Date dateRepresentation = calendar.getTime();
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MMM-dd");
                fmt.setCalendar(calendar);
                String date = fmt.format(calendar.getTime());

                Gson gS = new Gson();
                Person personObject = gS.fromJson(mainData, Person.class);

                personObject.setDate(date);
                if (!(neckText.getText().toString().matches("")) ) {
                    String neck = neckText.getText().toString();
                    double neckVal = Double.parseDouble(neck);
                    personObject.setNeck(neckVal);
                }

                if (!waistText.getText().toString().matches("")) {
                    String waist = waistText.getText().toString();
                    double waistVal = Double.parseDouble(waist);
                    personObject.setWaist(waistVal);
                }
                if (!chestText.getText().toString().matches("")){
                    String chest = chestText.getText().toString();
                    double chestVal = Double.parseDouble(chest);
                    personObject.setChest(chestVal);
                }
                if (!bustText.getText().toString().matches("")){
                    String bust = bustText.getText().toString();
                    double bustVal = Double.parseDouble(bust);
                    personObject.setBust(bustVal);
                }
                if (!hipText.getText().toString().matches("")){
                    String hip = hipText.getText().toString();
                    double hipVal = Double.parseDouble(hip);
                    personObject.setHip(hipVal);
                }
                if (!inseamText.getText().toString().matches("")){
                    String inseam = inseamText.getText().toString();
                    double inseamVal = Double.parseDouble(inseam);
                    personObject.setInseam(inseamVal);
                }
                if (!commentText.getText().toString().matches("")){
                    String comment = commentText.getText().toString();
                    personObject.setComment(comment);
                }

                String myPerson = gS.toJson(personObject);

                Intent intent2 = new Intent();
                intent2.putExtra("MESSAGE", myPerson);
                setResult(RESULT_OK, intent2);
                finish();
            }
        });

    }


}