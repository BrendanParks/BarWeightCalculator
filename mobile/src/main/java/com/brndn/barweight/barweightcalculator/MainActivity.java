package com.brndn.barweight.barweightcalculator;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {


    TextView w45EditText;
    TextView w35EditText;
    TextView w25EditText;
    TextView w10EditText;
    TextView w5EditText;
    TextView w2_5EditText;

    ArrayList<TextView> weightDisplays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        w45EditText = (TextView) findViewById(R.id.w45_edit_label);
        w35EditText = (TextView) findViewById(R.id.w35_edit_label);
        w25EditText =  (TextView) findViewById(R.id.w25_edit_label);
        w10EditText = (TextView) findViewById(R.id.w10_edit_label);
        w5EditText = (TextView) findViewById(R.id.w5_edit_label);
        w2_5EditText = (TextView) findViewById(R.id.w2_5_edit_label);

        weightDisplays = new ArrayList<TextView>();

        weightDisplays.add(w45EditText);
        weightDisplays.add(w35EditText);
        weightDisplays.add(w25EditText);
        weightDisplays.add(w10EditText);
        weightDisplays.add(w5EditText);
        weightDisplays.add(w2_5EditText);

        for (TextView t : weightDisplays) {
            t.setText("...");
        }

        final Activity context = this;
        ((Button)findViewById(R.id.calculate_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (TextView t : weightDisplays) {
                    t.setText("...");
                }
                int weight = 0;
                try {
                    weight = Integer.parseInt(((EditText)findViewById(R.id.weight_edittext)).getText().toString());

                } catch (NumberFormatException e) {
                    for (TextView t : weightDisplays) {
                        t.setText("Bad Number Format");
                    }
                }

                new CalculateWeight(context,weight,true,true,true,true,true,true).execute();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class CalculateWeight extends AsyncTask<Integer, Object, int[]> {

        private CalculateWeight(){super();}

        Activity context;

        int weight;

        boolean has45, has35, has25, has10, has5, has2_5;

        public CalculateWeight(Activity context,int weight, boolean has45, boolean has35, boolean has25, boolean has10, boolean has5, boolean has2_5){
            this.context = context;

            this.weight = weight;
            this.has45 =    has45;
            this.has35 =     has35;
            this.has25 =    has25;
            this.has10 =    has10;
            this.has5 =     has5;
            this.has2_5 =   has2_5;
        }




        @Override
        protected int[] doInBackground(Integer... ints) {
            return WeightCalculator.calculate(weight,has45, has35, has25, has10, has5, has2_5);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            for (TextView t : weightDisplays) {
                t.setText("Loading...");
            }
        }

        @Override
        protected void onPostExecute(int[] result) {

            if (result == null) {

                for (TextView t : weightDisplays) {
                    t.setText("Div by 5 error");
                }
            }


            for (int i = 0; i < 6; i++) {
                weightDisplays.get(i).setText(Integer.toString(result[i]));
            }

        }



    }

}
