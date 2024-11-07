package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_2SecondaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("SUM")) {
            String got_string = String.valueOf(intent.getStringExtra("SUM"));
            setResult(calculateSum(got_string), null);
            finish();
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
}