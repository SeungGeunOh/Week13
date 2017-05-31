package com.example.oh.week13;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inputNum;
    Button bChange;
    ImageView changeView;
    TextView progress;
    LinearLayout pLayout;
    MTask mTask;
    int count = 0;
    int cTime = 0;
    boolean bStop = false;
    int[] foods = { R.drawable.burrito, R.drawable.chicken, R.drawable.coldnoodles,
                    R.drawable.dumpling, R.drawable.hamburger, R.drawable.pizza,
                    R.drawable.ramen, R.drawable.sandwich, R.drawable.spaghetti,
                    R.drawable.stew, R.drawable.kimchijjim, R.drawable.porkbelly,
                    R.drawable.kimchijjim, R.mipmap.ic_launcher
    };
    String[] foodsName = { "부리또", "치킨", "냉면",
                           "만두", "햄버거", "피자",
                           "라면", "샌드위치", "스파게티",
                           "순두부찌개", "김치찜", "삼겹살",
                           "김치찜", "다시 시도해주세요"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("오늘 뭐 먹지?");
        inputNum = (EditText) findViewById(R.id.input);
        changeView = (ImageView) findViewById(R.id.imageView);
        progress = (TextView) findViewById(R.id.progress);
        pLayout = (LinearLayout) findViewById(R.id.progressLayout);
        bChange = (Button) findViewById(R.id.bChange);
    }

    public void onClick(View v) {
        mTask = new MTask();
        if (v.getId() == R.id.bFirst) {
            mTask.cancel(true);
            mTask = null;
            bStop = true;
            count = 0;
            inputNum.setText(null);
            progress.setText(null);
            pLayout.setVisibility(View.INVISIBLE);
            bChange.setVisibility(View.VISIBLE);
        }
        else if (v.getId() == R.id.bChange){
            bStop = false;
            String second = inputNum.getText().toString();
            if (second.length() == 0) {
                Toast.makeText(getApplicationContext(), "다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                inputNum.setText(null);
            }
            else {
                cTime = Integer.parseInt(second);
                mTask.execute(0);
                pLayout.setVisibility(View.VISIBLE);
                bChange.setVisibility(View.INVISIBLE);
            }
        }
    }

    class MTask extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = 0; i < (foods.length * cTime); i++) {
                if (bStop == true)
                    break;
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if (bStop == true)
                progress.setText(foodsName[count] + "을(를) 선택 (" + Integer.toString(values[0] - 1) + "초)");
            else {
                progress.setText("시작부터 " + values[0] + "초");
                if (values[0] % cTime == 0) {
                    changeView.setImageResource(foods[count++]);
                    changeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (count != foods.length) {
                                mTask = new MTask();
                                mTask.cancel(true);
                                bStop = true;
                                count--;
                            }
                        }
                    });
                }
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}



