package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HistorialActivity extends Activity implements View.OnClickListener {
    private static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 16777216 ;
    List<String> listoperaciones = new ArrayList<String>();
    ListView miListView;
    ArrayAdapter<String> miArrayAdapter;
    String selectedFromList;
    EditText opTxt;
    Button btnVolver;
    Intent i;
    int pos=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        miListView = (ListView) findViewById(R.id.listView2);
        opTxt = (EditText) findViewById(R.id.editText2);
        btnVolver = (Button) findViewById(R.id.button3);
        i = getIntent();
        if(!(i.getStringArrayListExtra("Lista_Operaciones").isEmpty())) {
            listoperaciones = i.getStringArrayListExtra("Lista_Operaciones");
            int count;
             count = 1;

            for (String str : listoperaciones) {
                String[] parts = str.split(":");

                if(parts.length == 1)
                    listoperaciones.set(count-1, count + " : " + str); //
                    count++;

            }

            miArrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listoperaciones);
        }

        opTxt.setOnClickListener(this);
        opTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    opTxt .getText().clear();
                 //   pos = Integer.valueOf(opTxt.getText().toString());
                }
                if(!hasFocus)
                    pos = Integer.valueOf(opTxt.getText().toString());
            }
        });

        opTxt.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ) {
                          //  if (!event.isShiftPressed()) {
                                // the user is done typing.
                                pos = Integer.parseInt(v.getText().toString()) - 1;
                                return true; // consume.
                           // }
                        }
                        return false; // pass on to other listeners.
                    }
                });

        miListView.setAdapter(miArrayAdapter);
        miListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                selectedFromList = (String) (miListView.getItemAtPosition(myItemInt));
                opTxt .getText().clear();
                pos = myItemInt;
                opTxt.setText(String.valueOf(pos+1));
            }
        });


        btnVolver.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listView2:
                miListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                        selectedFromList = (String) (miListView.getItemAtPosition(myItemInt));
                    }
                });
                break;
            case R.id.button3:
            {

                if(selectedFromList!=null || pos!=500)
                {
                    if(pos==500){
                        String b = opTxt.getText().toString();
                        int po= Integer.parseInt(b);
                        pos = po;
                    }
                   if(selectedFromList==null)
                           if (pos<=listoperaciones.size()){
                               selectedFromList = (String) (miListView.getItemAtPosition(pos));

                           Intent backIntent = new Intent(this, MainActivity.class);
                           backIntent.putExtra("Operaciones_Back", selectedFromList);
                           backIntent.putStringArrayListExtra("Lista_Operaciones", (ArrayList<String>) listoperaciones);
                           startActivity(backIntent);
                           finish();
                       }
                       else
                       {
                           Context context = getApplicationContext();
                           CharSequence text = "El num de operación introducido esta fuera de rango";
                           int duration = Toast.LENGTH_LONG;
                           Toast.makeText(context, text, duration).show();
                           break;
                       }

                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Debe seleccionar un numero de operación o seleccionar una de la lista";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                    break;
                }
            }

        }
    }
}



