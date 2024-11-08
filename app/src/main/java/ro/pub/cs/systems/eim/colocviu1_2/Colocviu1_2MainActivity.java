package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_2MainActivity extends AppCompatActivity {

    Button addButton, computeButton;
    EditText nextTerm, allTerms;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    String statusService = "STOPPED";


    MessageBroadcastReceiver messageBroadcastReceiver;
    IntentFilter intentFilter;
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.add) {
                if (!allTerms.getText().toString().isEmpty()) {
                    allTerms.setText(String.valueOf(allTerms.getText().toString() + " + " + nextTerm.getText().toString()));

                } else {
                    allTerms.setText(nextTerm.getText().toString());
                }
            } else if (view.getId() == R.id.compute) {
                Intent intent = new Intent(Colocviu1_2MainActivity.this, Colocviu1_2SecondaryActivity.class);
                intent.putExtra("SUM", allTerms.getText().toString());
                startActivityForResult(intent, 2024);
            }

            if (calculateSum(allTerms.getText().toString()) >= 10 && statusService.equals("STOPPED")) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("SUM", calculateSum(allTerms.getText().toString()));
                getApplicationContext().startService(intent);
                statusService = "STARTED";
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

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("next_term")) {
                nextTerm.setText(savedInstanceState.getString("next_term"));
            } else {
                nextTerm.setText("");
            }
            if (savedInstanceState.containsKey("all_terms")) {
                allTerms.setText(savedInstanceState.getString("all_terms"));
            } else {
                allTerms.setText("");
            }
        } else {
            nextTerm.setText("");
            allTerms.setText("");
        }

        messageBroadcastReceiver = new MessageBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("actiune1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("TestPractic1MainActivity","onSaveInstanceState() method was invoked.");

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("next_term", nextTerm.getText().toString());
        savedInstanceState.putString("all_terms", allTerms.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("TestPractic1MainActivity","onRestoreInstanceState() method was invoked.");

        if (savedInstanceState.containsKey("next_term")) {
            nextTerm.setText(savedInstanceState.getString("next_term"));
        } else {
            nextTerm.setText("");
        }
        if (savedInstanceState.containsKey("all_terms")) {
            allTerms.setText(savedInstanceState.getString("all_terms"));
        } else {
            allTerms.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2024) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    public static int calculateSum(String expression) {
        // Split the expression by " + " delimiter
        String[] numbers = expression.split(" \\+ ");

        int sum = 0;
        for (String number : numbers) {
            // Convert each substring to an integer and add to the sum
            sum += Integer.parseInt(number.trim());
        }

        return sum;
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        super.onDestroy();
    }
}