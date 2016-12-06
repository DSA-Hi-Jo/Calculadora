package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    String op = "",op_back;
    float resultado = 0;
    EditText input1, input2, output;
    Button btnResult,btnReset,btnHistorial;
    float num1,num2;
    List<String> listoperaciones = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        output = (EditText) findViewById(R.id.output);
        btnResult = (Button) findViewById(R.id.buttonRes);
        btnReset = (Button) findViewById(R.id.buttonReset);
        btnHistorial = (Button) findViewById(R.id.buttonHistorial);

        btnReset.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        btnHistorial.setOnClickListener(this);
        input1.setOnClickListener(this);
        input2.setOnClickListener(this);


        Intent i = getIntent();
        boolean a = i.hasExtra("Operaciones_Back");
        if(i.hasExtra("Operaciones_Back"))
        {
            try {
                // String b = i.getExtras().getString("Operaciones_Back");
                if ((i.getExtras().getString("Operaciones_Back")) != "") {
                    op_back = i.getExtras().getString("Operaciones_Back");
                    makeOp(op_back);
                }
            } catch (NullPointerException e) {
                String err = e.toString();
            }
            if(!i.getStringArrayListExtra("Lista_Operaciones").isEmpty())
            {
                listoperaciones = i.getStringArrayListExtra("Lista_Operaciones");
            }
        }
        input1.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == 5 /*EditorInfo.IME_ACTION_DONE*/ ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            //  if (!event.isShiftPressed()) {
                            // the user is done typing.

                            return true; // consume.
                            // }
                        }
                        else {
                            return false; // pass on to other listeners.
                        }
                    }
                });
    }



    public void makeOp(String operacion){

        String[] parts = operacion.split(":");
        String[] oper = parts[1].split(" ");
        String numero1 = oper[1];
        String operation = oper[2];
        String numero2 = oper[3];
        String result = oper[5];
        input1.setText(numero1);
        input2.setText(numero2);
        output.setText("");

        //if(operation =="+")


    }

    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonSuma:
                if (checked)
                    op = "+";
                    break;
            case R.id.radioButtonResta:
                if (checked)
                    op = "-";
                    break;
            case R.id.radioButtonMultipli:
                if (checked)
                    op = "*";
                    break;
            case R.id.radioButtonDivision:
                if (checked)
                    op = "/";
                    break;
        }
    }

    public void btnResClicked(View view) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRes:
            {
                if (op != "") {

                    String text1 = input1.getText().toString();
                    String text2 = input2.getText().toString();

                    try {
                        int num = Integer.parseInt(text1);
                        int num2 = Integer.parseInt(text2);
                    } catch (NumberFormatException e) {
                        Context context = getApplicationContext();
                        CharSequence text = "Los numeros introducidos no son enteros";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                        break;
                    }


                    num1 = Float.parseFloat(input1.getText().toString());
                    num2 = Float.parseFloat(input2.getText().toString());

                    if(op =="+") {
                        resultado = (num1) + (num2);
                        listoperaciones.add(num1 + " + " + num2 + " = " + resultado);
                    }
                    if(op =="-") {
                        resultado = (num1) - (num2);
                        listoperaciones.add(num1 + " + " + num2 + " = " + resultado);
                    }
                    if(op =="/") {
                        resultado = (num1) / (num2);
                        listoperaciones.add(num1 + " + " + num2 + " = " + resultado);
                        }
                    if(op =="*") {
                        resultado = (num1) * (num2);
                        listoperaciones.add(num1 + " + " + num2 + " = " + resultado);
                            }
                    output.setText("" + (resultado));

                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Debe seleccionar una operación";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }
            }
            break;
            case R.id.buttonReset:
            {
                input1.setText("0");
                output.setText("0");
                input2.setText("0");
            }
            break;
            case R.id.input2:
            {
                input2.getText().clear();
            }
            break;
            case R.id.input1:
            {input1.getText().clear();
            }
            break;
            case R.id.buttonHistorial:
            {
                Intent historialIntent = new Intent(this, HistorialActivity.class);
                historialIntent.putStringArrayListExtra("Lista_Operaciones", (ArrayList<String>) listoperaciones);
                startActivity(historialIntent);
               // finish();

            }
            break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

            }
        }
    }
}
