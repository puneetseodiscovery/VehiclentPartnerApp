package vehiclentpartner.com.vehiclent.spinner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import vehiclentpartner.com.vehiclent.R;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spin;
    String[] bankNames = {"Select", "BOI", "SBI", "HDFC", "PNB", "OBC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {

            Validation();
        }


    }

    private void Validation() {
        TextView errorText = (TextView) spin.getSelectedView();
        errorText.setError("");
        errorText.setTextColor(Color.GRAY);//just to highlight that this is an error
        errorText.setText("Non Selected");//changes the selected item text to this
        errorText.setTextSize(18);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
