package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_2MainActivity extends AppCompatActivity {

    Button addButton, computeButton;
    EditText nextTerm, allTerms;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();


    //    String serviceStatus = Constants.SERVICE_STOPPPED;
//
//    MessageBroadcastReceiver messageBroadcastReceiver;
//    IntentFilter intentFilter;
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.add) {
                if (allTerms.getText() != null) {
                    allTerms.setText(String.valueOf(allTerms.getText().toString() + " + " + nextTerm.getText().toString()));

                } else {
                    allTerms.setText(String.valueOf(allTerms.getText().toString()));
                }
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        addButton = findViewById(R.id.add);
        computeButton = findViewById(R.id.compute);

        nextTerm = findViewById(R.id.next_term);
        allTerms = findViewById(R.id.all_terms);

        addButton.setOnClickListener(buttonClickListener);
        computeButton.setOnClickListener(buttonClickListener);
    }
}