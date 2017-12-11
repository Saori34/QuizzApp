package fr.emilie.quizzapp;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {

    private TextView mIntro;
    private Button mIntro_button;
    private TextView mQuestionTextView;
    private ImageView mImage;
    private Button mButton_true;
    private Button mButton_false;
    private ProgressBar mProgressBar;

    private int mScore = 0;
    private int mIndex = 0;
    private int mQuestion;



    private VraiFaux mQuestions[] = new VraiFaux[]{
            new VraiFaux(R.string.question_1, false),
            new VraiFaux(R.string.question_2, true),
            new VraiFaux(R.string.question_3, true),
            new VraiFaux(R.string.question_4, false),
            new VraiFaux(R.string.question_5, true),
            new VraiFaux(R.string.question_6, true),
            new VraiFaux(R.string.question_7, false),
            new VraiFaux(R.string.question_8, true),
            new VraiFaux(R.string.question_9, false),
            new VraiFaux(R.string.question_10, false),
            new VraiFaux(R.string.question_11, true),
            new VraiFaux(R.string.question_12, false),
            new VraiFaux(R.string.question_13, true),
            new VraiFaux(R.string.question_14, true),
            new VraiFaux(R.string.question_15, false),
            new VraiFaux(R.string.question_16, true)
    };

    private int mTabImage[] = new int[]{
            R.drawable.avatar, R.drawable.poule, R.drawable.chi, R.drawable.des, R.drawable.rougit,
            R.drawable.feldman, R.drawable.guignolet, R.drawable.herault, R.drawable.iguane, R.drawable.jane,
            R.drawable.kalium, R.drawable.lamartine, R.drawable.mandela, R.drawable.nenufar, R.drawable.oedipe,
            R.drawable.popeye
    };

    final int PROGRESS_BAR_INCREMENT = (int)Math.ceil(100.0/mQuestions.length);
    private int mProgressBarProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if (savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mProgressBarProgress = savedInstanceState.getInt("ProgressBarKey");
           // mProgressBar.setProgress(mProgressBarProgress);
        }else{
            mScore = 0;
            mIndex = 0;
        }


        mIntro = (TextView) findViewById(R.id.intro);
        mIntro_button = (Button) findViewById(R.id.button_demarrer);

        mIntro_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_main);
                mImage = (ImageView) findViewById(R.id.image);
                mQuestionTextView = (TextView) findViewById(R.id.question);
                mButton_false = (Button) findViewById(R.id.button_false);
                mButton_true = (Button) findViewById(R.id.button_true);
                mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

                mQuestion = mQuestions[mIndex].getQuestionID();
                mQuestionTextView.setText(mQuestion);
                mImage.setImageResource(mTabImage[mIndex]);

                mButton_true.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verifReponse(true);
                        questionSuivante();
                    }
                });

                mButton_false.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verifReponse(false);
                        questionSuivante();
                    }
                });

            }
        });


    }

    public void verifReponse(boolean userChoice){
        boolean vraiReponse = mQuestions[mIndex].getReponse();
        if(userChoice == vraiReponse){
            Toast.makeText(this, R.string.bonneReponse, Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            Toast.makeText(this, R.string.mauvaiseReponse, Toast.LENGTH_SHORT).show();
        }
    }

    public void questionSuivante(){
        mIndex = (mIndex+1) % mQuestions.length;
        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setCancelable(false);
            alert.setMessage("Vous avez un score de " + mScore + "/16");
            alert.setTitle("Fin du quizz !");
            alert.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        mQuestion = mQuestions[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mImage.setImageResource(mTabImage[mIndex]);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mProgressBarProgress = mProgressBar.getProgress();


    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("IndexKey", mIndex);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("ProgressBarKey", mProgressBarProgress);

    }

}
