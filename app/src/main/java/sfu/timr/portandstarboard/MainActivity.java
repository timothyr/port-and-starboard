package sfu.timr.portandstarboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "NauticalGame";

    private Game terminologyGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupShowNameButton(R.id.showLeftButton, R.string.left_port_explanation);
        setupShowNameButton(R.id.showRightButton, R.string.right_starboard_explanation);

        setupAnswerButton(R.id.answerButtonLeft, Game.Side.PORT);
        setupAnswerButton(R.id.answerButtonRight, Game.Side.STARBOARD);

        newQuizQuestion();
    }

    private void setupShowNameButton(int buttonId, final int nameExplanationId) {
        Button button = (Button) findViewById(buttonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, getString(nameExplanationId));
                Toast.makeText(getApplicationContext(), nameExplanationId, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void setupAnswerButton(int buttonId, final Game.Side side) {
        Button button = (Button) findViewById(buttonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkIfAnswerCorrect(side);

            }
        });
    }

    private void checkIfAnswerCorrect(Game.Side side) {
        if(terminologyGame.checkIfCorrect(side))
            answerCorrect(side);
        else
            answerIncorrect(side);

        newQuizQuestion();
    }

    private void newQuizQuestion() {
        // Set Quiz question
        terminologyGame = new Game();
        TextView quizQuestion = (TextView) findViewById(R.id.quizQuestion);
        quizQuestion.setText(terminologyGame.getChosenSideName());
    }

    private void answerIncorrect(Game.Side side) {
        Log.i(TAG, "User guess of " + side.getName() + " was Incorrect.");
        Toast.makeText(getApplicationContext(), "Incorrect. :(", Toast.LENGTH_SHORT)
                .show();
    }

    private void answerCorrect(Game.Side side) {
        Log.i(TAG, "User guess of " + side.getName() + " was Correct!");
        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT)
                .show();
    }
}
