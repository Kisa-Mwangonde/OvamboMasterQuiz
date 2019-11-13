package com.example.ovambomasterquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private static final int REQUEST_CODE_QUIZ= 1;
public static final String EXTRA_DIFFICULTY= "extraDifficulty";

public static final String SHARED_PREFS= "sharedPrefs";
public static final String KEY_HIGHSCORE= "keyHighScore";

private TextView textViewHighscore;
private Spinner spinnerDifficulty;
private ImageView infoImage;
private ImageView puzzleImage;


private int highscore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textViewHighscore = findViewById(R.id.text_view_highscore);
        spinnerDifficulty= findViewById(R.id.spinner_difficulty);
        infoImage= (ImageView) findViewById(R.id.image_view_info);
        puzzleImage= (ImageView) findViewById(R.id.image_view_puzzle);


        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });



        puzzleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchGame();
            }
        });

        String[] difficulyLevels= Question.getAllDifficultyLevels();


        ArrayAdapter<String> adapterDifficulty= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficulyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
        loadHighScore();


        Button buttonStartQuiz = findViewById(R.id.button_startquiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startQuiz();
            }
        });




    }


   private void startQuiz(){
        String difficulty= spinnerDifficulty.getSelectedItem().toString();
       Intent intent= new Intent(MainActivity.this, QuizActivity.class);
       intent.putExtra(EXTRA_DIFFICULTY, difficulty);

       startActivityForResult(intent, REQUEST_CODE_QUIZ);

   }

   public  void openMatchGame() {
       Intent intent = new Intent(MainActivity.this, MatchGameActivity.class);
       startActivity(intent);
   }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore){
                    updateHighscore(score);
                }
            }

        }
    }

    private void loadHighScore(){
        SharedPreferences prefs= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore= prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor= prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }

    public void openDialog(){
            InfoDialog infoDialog = new InfoDialog();
            infoDialog.show(getSupportFragmentManager(), "info dialog");

    }

}
