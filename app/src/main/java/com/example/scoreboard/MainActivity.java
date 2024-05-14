package com.example.scoreboard;

import static com.example.scoreboard.R.*;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.scoreboard.R;

public class MainActivity extends AppCompatActivity {

    private Button btnHsp, btnHsm, btnHfp, btnHfm, btnGsp, btnGsm, btnStart, btnPause, btnReset, btngfp, btngfm;
    private TextView tvHs, tvGs, tvHf, tvGf, timerTv;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private boolean timerRunning;

    // score + foul
    private int homeScore, guestScore, homeFoul, guestFoul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your views
        btnHsp = findViewById(R.id.btnhsp);
        btnHsm = findViewById(R.id.btnhsm);
        btnHfp = findViewById(R.id.btnhfp);
        btnHfm = findViewById(R.id.btnhfm);
        btnGsp = findViewById(R.id.btngsp);
        btnGsm = findViewById(R.id.btngsm);
        btnStart = findViewById(R.id.btnst);
        btnPause = findViewById(R.id.btnpt);
        btnReset = findViewById(R.id.btnrt);
        tvHs = findViewById(R.id.tvhs);
        tvGs = findViewById(R.id.tvgs);
        tvHf = findViewById(R.id.tvhf);
        tvGf = findViewById(R.id.tvgf);
        timerTv = findViewById(R.id.timertv);
        btngfp = findViewById(id.btngfp);
        btngfm = findViewById(id.btngfm);


        // Set initial values for the timer
        timeLeftInMillis = 720000; // 12 minutes in milliseconds
        timerRunning = false;
        updateTimer();

        // Button click listeners
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });


        btnHfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFoul++;
                tvHf.setText(String.valueOf(homeFoul));
            }
        });

        btnHfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFoul--;
                tvHf.setText(String.valueOf(homeFoul));
            }
        });

        btnHsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScore++;
                tvHs.setText(String.valueOf(homeScore));
            }
        });

        btnHsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScore--;
                tvHs.setText(String.valueOf(homeScore));
            }
        });

        btnGsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestScore++;
                tvGs.setText(String.valueOf(guestScore));
            }
        });

        btnGsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestScore--;
                tvGs.setText(String.valueOf(guestScore));
            }
        });

        btngfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestFoul++;
                tvGf.setText(String.valueOf(guestFoul));
            }
        });

        btngfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestFoul--;
                tvGf.setText(String.valueOf(guestFoul));
            }
        });






    }

    private void startStop() {
        if (timerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateTimer();
            }
        }.start();

        timerRunning = true;
        updateTimer();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        updateTimer();
    }

    private void resetTimer() {
        timeLeftInMillis = 720000;
        updateTimer();
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTv.setText(timeLeftFormatted);

    }

    // Other methods for handling score and foul buttons...
}
