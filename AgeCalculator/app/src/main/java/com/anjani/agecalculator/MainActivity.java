package com.anjani.agecalculator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getName();
    TextView tvTdyLabel, tvTdyName, tvTdyDay, tvTdyMonth, tvTdyYear, tvDobName, tvDobLabel, tvDobDay, tvDobMonth, tvDobYear, tvAgeCount;
    AppCompatImageView imageView;
    int day, month, year;
    int currentDay, currentMonth, currentYear, dobDay, dobMonth, dobYear;

    String week[] = {"sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static String monthName[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC"};

    String todayDayName;
    ImageButton ibTdyButton, ibDobButton;
    LocalDate ldDobDate, ldTdyate;
    AppCompatButton abtAgeCalculate, abtAgeClear;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        findView();

        textViewChangeBackGroundColor();

        setDefaultCurrentDate();
        setTodayDate(day, month, year);

        getCurrentDate();
        setTodaydayname(0);
        // setDobDate(day,month,year);

        ibDobButton.setOnClickListener(this);
        ibTdyButton.setOnClickListener(this);
        abtAgeCalculate.setOnClickListener(this);
        abtAgeClear.setOnClickListener(this);


    }

    private void setTodaydayname(int d) {
        try {
            Calendar calendar = Calendar.getInstance();
            if (d == 0) {
                calendar.set(currentYear, currentMonth - 1, currentDay);
            } else {
                calendar.set(dobYear, dobMonth - 1, dobDay);
            }
            int i = (calendar.get(Calendar.DAY_OF_WEEK)) - 1;
            todayDayName = week[i];
            if (d == 0) {
                tvTdyName.setText(todayDayName);
            } else {
                tvDobName.setText(todayDayName);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        } catch (ArithmeticException ex) {
            ex.printStackTrace();
        }
    }


    private void getDobDate() {
        try {
            dobDay = Integer.parseInt((tvDobDay.getText().toString()));
            dobMonth = Arrays.asList(monthName).indexOf(tvDobMonth.getText().toString()) + 1;
            dobYear = Integer.parseInt(tvDobYear.getText().toString());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void getCurrentDate() {
        try {
            currentDay = Integer.parseInt((tvTdyDay.getText().toString()));
            currentMonth = Arrays.asList(monthName).indexOf(tvTdyMonth.getText().toString()) + 1;
            currentYear = Integer.parseInt(tvTdyYear.getText().toString());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void setDobDate(int day, int month, int year) {
        try {
            tvDobDay.setText(Integer.toString(day));
            tvDobMonth.setText(monthName[month - 1]);
            tvDobYear.setText(Integer.toString(year));
            tvDobName.setText(todayDayName);
            getDobDate();
            setTodaydayname(1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }

    private void setTodayDate(int day, int month, int year) {
        try {
            tvTdyDay.setText(Integer.toString(day));
            tvTdyMonth.setText(monthName[month - 1]);
            tvTdyYear.setText(Integer.toString(year));
            tvTdyName.setText(todayDayName);
            getCurrentDate();
            setTodaydayname(0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }


    }

    private void setDefaultCurrentDate() {

        try {

            Calendar calendar = Calendar.getInstance();


            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = 1 + calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void textViewChangeBackGroundColor() {
        try {
            tvTdyDay.setBackgroundColor(Color.WHITE);
            tvTdyMonth.setBackgroundColor(Color.WHITE);
            tvTdyYear.setBackgroundColor(Color.WHITE);
            tvDobDay.setBackgroundColor(Color.WHITE);
            tvDobMonth.setBackgroundColor(Color.WHITE);
            tvDobYear.setBackgroundColor(Color.WHITE);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }


    // binding the view


    private void findView() {
        tvTdyLabel = findViewById(R.id.tv_tday_label);
        tvDobLabel = findViewById(R.id.tv_dob_label);
        tvTdyName = findViewById(R.id.tv_today_date_name);
        tvDobName = findViewById(R.id.tv_dob_name);

        tvTdyDay = findViewById(R.id.tv_tdy_month_of_day);
        tvTdyMonth = findViewById(R.id.tv_tdy_year_of_month);
        tvTdyYear = findViewById(R.id.tv_tday_year);

        tvDobDay = findViewById(R.id.tv_dob_month_of_day);
        tvDobMonth = findViewById(R.id.tv_dob_year_of_month);
        tvDobYear = findViewById(R.id.tv_dob_year);


        ibTdyButton = findViewById(R.id.ib_tdy);
        ibDobButton = findViewById(R.id.ib_dob);
        abtAgeCalculate = findViewById(R.id.bt_calculate_age);
        abtAgeClear = findViewById(R.id.bt_clear);

        imageView = findViewById(R.id.ivImage);

        tvAgeCount = findViewById(R.id.tv_age_count);


    }


    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {
                case R.id.bt_calculate_age:
                    calculateAge1();
                    break;
                case R.id.ib_tdy:
                    datePickerDialog(0);
                    break;
                case R.id.ib_dob:
                    datePickerDialog(1);
                    break;
                case R.id.bt_clear:
                    clearDob();
                default:
                    // datePickerDialog();
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }


    }

    private void clearDob() {
        try {
            tvDobYear.setText("");
            tvDobMonth.setText("");
            tvDobDay.setText("");
            abtAgeCalculate.setEnabled(false);
            abtAgeClear.setEnabled(false);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void loadImageFromWeb(int age) {
        String child_url = "https://www.michaelkormos.com/wp-content/uploads/2019/02/12-7126-pp_gallery/boy-crawling-in-grass-1024x741.jpg";
        String adult_url = "https://c.static-nike.com/a/images/w_1920,c_limit,f_auto/rkmkjrgse0dwzmib6j93/boots-of-a-phenom-neymar-jr-meu-jogo-mercurial-vapor-360.jpg";
        String old_url = "https://www.hindustantimes.com/rf/image_size_960x540/HT/p2/2019/04/15/Pictures/_9a283aa6-5f44-11e9-a01d-452d93af50a1.PNG";
        String teenage_url = "https://i.pinimg.com/originals/3f/c9/5a/3fc95a090f6fbd9019da2b2c086e1458.jpg";


        //Picasso.with(getBaseContext()).load(child_url).error()
        if (age < 5) {
            Picasso.with(getBaseContext()).load(child_url).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        } else if (age < 15) {
            Picasso.with(getBaseContext()).load(teenage_url).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        } else if (age < 40) {
            Picasso.with(getBaseContext()).load(adult_url).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        } else {
            Picasso.with(getBaseContext()).load(old_url).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
        }
    }


    private void calculateAge1() {
        try {
            if (!isCheckDob()) {

                Calendar cal = Calendar.getInstance();
                getCurrentDate();
                getDobDate();

                year = currentYear - dobYear;
                month = currentMonth - dobMonth;
                if (month < 0) {
                    month += 12;
                    year--;
                }
                cal.set(dobYear, dobMonth - 1, dobDay);
                int d = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


                day = currentDay - dobDay;
                if (day < 0) {

                    day += d;
                    if (month == 0) {
                        month = 11;
                        year--;
                    } else
                        month--;

                }


                tvAgeCount.setText(year + " -Years   " + month + " -Months   " + day + "  -  Days ");
                //tvAgeCount.setText("DOB is "+ dobDay+"/"+dobMonth+"/"+dobYear +" and "+ "current age is "+currentDay+"/"+currentMonth+"/"+currentYear);

                loadImageFromWeb(year);


            }


        } catch (NullPointerException ex) {
            ex.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private boolean isCheckDob() {
        try {
            if ((!tvDobDay.getText().toString().isEmpty() && !tvDobMonth.getText().toString().isEmpty() && !tvDobYear.getText().toString().isEmpty())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // calculate the age diffrence

    /* @RequiresApi(api = Build.VERSION_CODES.O)
     private void calculateAge()
     {
         if(!(tvDobDay.getText().toString().isEmpty() && !tvDobMonth.getText().toString().isEmpty() &&!tvDobYear.getText().toString().isEmpty()))
         {
             day = Integer.parseInt((tvDobDay.getText().toString()));
             month =11;
             year = Integer.parseInt(tvDobYear.getText().toString());
             ldDobDate = LocalDate.of(year, month, day);
             ldTdyate = LocalDate.now();
             Period diff1 = Period.between(ldDobDate, ldTdyate);
             Toast.makeText(MainActivity.this, "year " + diff1.getYears() + " month " + diff1.getMonths() + " day " +
                     "" + diff1.getDays(), Toast.LENGTH_LONG).show();
         }

     }
 */
    private void datePickerDialog(final int i) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(currentYear, currentMonth - 1, currentDay);
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    if (i == 0) {
                        setTodayDate(dayOfMonth, month + 1, year);
                    } else
                        setDobDate(dayOfMonth, month + 1, year);
                    abtAgeCalculate.setEnabled(true);
                    abtAgeClear.setEnabled(true);


                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.getDatePicker().setEnabled(true);
            datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
            datePickerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("myapp", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
}
