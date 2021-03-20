package umn.ac.id.uts_mobile_32827;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import static umn.ac.id.uts_mobile_32827.HomeActivity.songs;

public class MusicActivity extends AppCompatActivity {
    private TextView songName, albumName, durationStart, durationEnd;
    private ImageView playPauseButton, nextButton, prevButton;
    private SeekBar seekBar;
    private Audio currentSong;
    private int position;

    private Uri uri;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    private Thread playThread, nextThread, prevThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initializeComponents();
        currentSong = getIntent().getParcelableExtra("audio");
        position = getIntent().getIntExtra("position", 0);

        // UI
        durationEnd.setText(formattedTime(Integer.parseInt(currentSong.getDuration()) / 1000));
        songName.setText(currentSong.getName());
        albumName.setText(currentSong.getAlbum());

        playPauseButton.setImageResource(R.drawable.pause);
        uri = Uri.parse(currentSong.getPath());

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        } else {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }

        seekBar.setMax(mediaPlayer.getDuration() / 1000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        MusicActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPos = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPos);
                    durationStart.setText(formattedTime(mCurrentPos));
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onResume() {
        playThreadBtn();
        nextThreadBtn();
        prevThreadBtn();

        super.onResume();
    }

    private void prevThreadBtn() {
        prevThread = new Thread() {
            @Override
            public void run() {
                super.run();
                prevButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prevButtonClicked();
                    }
                });
            }
        };

        prevThread.start();
    }

    private void prevButtonClicked() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            position = (position - 1) < 0 ? songs.size() - 1 : position - 1;

            uri = Uri.parse(songs.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

            durationEnd.setText(formattedTime(Integer.parseInt(songs.get(position).getDuration()) / 1000));
            songName.setText(songs.get(position).getName());
            albumName.setText(songs.get(position).getAlbum());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPos);
                    }

                    handler.postDelayed(this, 1000);
                }
            });

            playPauseButton.setImageResource(R.drawable.pause);
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
            mediaPlayer.release();
            position = (position - 1) < 0 ? songs.size() - 1 : position - 1;

            uri = Uri.parse(songs.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

            durationEnd.setText(formattedTime(Integer.parseInt(songs.get(position).getDuration()) / 1000));
            songName.setText(songs.get(position).getName());
            albumName.setText(songs.get(position).getAlbum());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPos);
                    }

                    handler.postDelayed(this, 1000);
                }
            });

            playPauseButton.setImageResource(R.drawable.pause);
        }
    }

    private void nextThreadBtn() {
        nextThread = new Thread() {
            @Override
            public void run() {
                super.run();
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextButtonClicked();
                    }
                });
            }
        };

        nextThread.start();
    }

    private void nextButtonClicked() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            position = (position + 1) % songs.size();

            uri = Uri.parse(songs.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

            durationEnd.setText(formattedTime(Integer.parseInt(songs.get(position).getDuration()) / 1000));
            songName.setText(songs.get(position).getName());
            albumName.setText(songs.get(position).getAlbum());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPos);
                    }

                    handler.postDelayed(this, 1000);
                }
            });

            playPauseButton.setImageResource(R.drawable.pause);
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
            mediaPlayer.release();
            position = (position + 1) % songs.size();

            uri = Uri.parse(songs.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

            durationEnd.setText(formattedTime(Integer.parseInt(songs.get(position).getDuration()) / 1000));
            songName.setText(songs.get(position).getName());
            albumName.setText(songs.get(position).getAlbum());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPos);
                    }

                    handler.postDelayed(this, 1000);
                }
            });

            playPauseButton.setImageResource(R.drawable.pause);
        }
    }

    private void playThreadBtn() {
        playThread = new Thread() {
            @Override
            public void run() {
                super.run();
                playPauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPauseButtonClicked();
                    }
                });
            }
        };

        playThread.start();
    }

    private void playPauseButtonClicked() {
        if (mediaPlayer.isPlaying()) {
            playPauseButton.setImageResource(R.drawable.arrow_play);
            mediaPlayer.pause();
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPos);
                        durationStart.setText(formattedTime(mCurrentPos));
                    }

                    handler.postDelayed(this, 1000);
                }
            });
        } else {
            playPauseButton.setImageResource(R.drawable.pause);
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPos);
                        durationStart.setText(formattedTime(mCurrentPos));
                    }

                    handler.postDelayed(this, 1000);
                }
            });
        }
    }

    private String formattedTime(int pos) {
        String totalout = "";
        String totalnew  = "";
        String seconds = String.valueOf(pos % 60);
        String minutes = String.valueOf(pos / 60);
        totalout = minutes + ":" + seconds;
        totalnew = minutes + ":" + "0" + seconds;

        if (seconds.length() == 1) {
            return totalnew;
        }

        return totalout;
    }

    private void initializeComponents() {
        songName = findViewById(R.id.songName);
        albumName = findViewById(R.id.albumName);
        durationStart = findViewById(R.id.durationStart);
        durationEnd = findViewById(R.id.durationEnd);
        seekBar = findViewById(R.id.durationSeekBar);
        playPauseButton = findViewById(R.id.playPauseButton);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
    }
}