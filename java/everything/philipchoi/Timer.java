package everything.philipchoi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Timer extends Fragment {
    TextView timer;
    SeekBar timeSeek;
    FloatingActionButton goB;
    FloatingActionButton goB2;
    CountDownTimer time;
    ProgressBar progress;
    boolean counter = false;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        (getActivity()).setTitle("Timer");
        view = inflater.inflate(R.layout.timer, container, false);
        timeSeek = view.findViewById(R.id.timeBar);
        timer = view.findViewById(R.id.timer);
        goB = view.findViewById(R.id.pause);
        goB2 = view.findViewById(R.id.pause2);
        progress = view.findViewById(R.id.progressBar);
        progress.setProgress(0);
        timeSeek.setMax(3600);
        timeSeek.setProgress(30);
        timeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        start();
        return view;
    }

    @SuppressLint("RestrictedApi")
    public void reset() {
        timer.setText(("0:30"));
        timeSeek.setProgress(30);
        timeSeek.setEnabled(counter);
        time.cancel();
        System.out.println("RESER");
    //    goB.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
       // goB.setText("START");
        counter = false;
        progress.setProgress(0);
    }
    int i =0;
    public void start(){
        progress.setProgress(i);
        goB2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                System.out.println("AA");
                goB.setVisibility(View.VISIBLE);
                goB2.setVisibility(View.INVISIBLE);
                System.out.println("RESET");
                // goB.setVisibility(View.VISIBLE);
                reset();
            }
        });
        goB.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                System.out.println("CLICKEC");
                try {
                        goB.setVisibility(View.INVISIBLE);
                        goB2.setVisibility(View.VISIBLE);
                        System.out.println("NO RE");
                        counter = true;
                        timeSeek.setEnabled(false);
                       // goB.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                   //     goB.setText("STOP");
                        i = 0;
                        time = new CountDownTimer(timeSeek.getProgress() * 1000, 1000) {
                            @Override
                            public void onTick(long l) {
                                updateTimer((int) l / 1000);
                                i++;
                                progress.setProgress(i);
                            }

                            @Override
                            public void onFinish() {
                                goB.setVisibility(View.VISIBLE);
                        /*MediaPlayer audio = MediaPlayer.create(getContext(), R.raw.alarm);
                        if(i>1)
                            audio.start();*/
                                reset();

                            }
                        }.start();
                }catch(Exception e){
                    Toast.makeText(getContext(), "GO HIGHER", Toast.LENGTH_SHORT).show();
                    timer.setText(("0:30"));
                    timeSeek.setProgress(30);
                    timeSeek.setEnabled(counter);
                    //goB.setText("START");
                    System.out.println("EXCEPTION");
                    counter = false;
                    progress.setProgress(0);
                }
            }
        });

    }

    private void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);
        String secondString = Integer.toString(seconds);
        progress.setMax(timeSeek.getProgress());
        if (seconds <= 9)
            secondString = String.format("0%d", seconds);
        timer.setText(Integer.toString(minutes) + ":" + secondString);

    }
}
