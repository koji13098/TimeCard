package com.example.timecard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText beginTime,endTime;
    EditText beginTimeHour,beginTimeMinute,endTimeHour,endTimeMinute;
    EditText breakTime;
    TextView sumTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginTime = findViewById(R.id.beginTime);
        endTime = findViewById(R.id.endTime);
        breakTime = findViewById(R.id.breakTime);
        sumTime = findViewById(R.id.sumTime);

    }

    public void onCalculate(View view) {
        String beginTimes[] = (beginTime.getText().toString()).split(":");
        String endTimes[] = (endTime.getText().toString()).split(":");

        if ((Integer.parseInt(endTimes[0]) > Integer.parseInt(beginTimes[0]))) {
            sumTime.setText(Integer.toString(
                    ((Integer.parseInt(endTimes[0]) - Integer.parseInt(beginTimes[0])) * 60)
                            + (Integer.parseInt(endTimes[1]) - Integer.parseInt(beginTimes[1]))
                            - Integer.parseInt(breakTime.getText().toString())));
        } else {
            sumTime.setText(Integer.toString(
                    (((Integer.parseInt(endTimes[0]) + 24) - Integer.parseInt(beginTimes[0])) * 60)
                            + (Integer.parseInt(endTimes[1]) - Integer.parseInt(beginTimes[1]))
                            - Integer.parseInt(breakTime.getText().toString())));
        }
    }

    public void showTimeDialog(View view) {
        //TimePickerDialog
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("test",String.format("%02d:%02d", hourOfDay,minute));
                    }
                },
                hour,minute,true);
        dialog.show();

    }
}