package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    String op = "";
    float resultado = 0;
    EditText input1, input2, output;
    Button btnResult,btnReset;
    float num1,num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         input1 = (EditText) findViewById(R.id.input1);
         input2 = (EditText) findViewById(R.id.input2);
         output = (EditText) findViewById(R.id.output);
         btnResult = (Button) findViewById(R.id.buttonRes);
         btnReset = (Button) findViewById(R.id.buttonReset);

        btnReset.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }


    public void operacion(String input1, String input2, String operacion)
    {
        if(operacion == "+")
        {

        }
        if(operacion == "-")
        {

        }
        if(operacion == "/")
        {

        }
        if (operacion =="*")
        {

        }

    }


    public void reset(View view)
    {

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

                    num1 = Float.parseFloat(input1.getText().toString());
                    num2 = Float.parseFloat(input2.getText().toString());

                    if(op =="+")
                        resultado = (num1)+ (num2);
                    if(op =="-")
                        resultado = (num1)- (num2);
                    if(op =="/")
                        resultado = (num1)/ (num2);
                    if(op =="*")
                        resultado = (num1)* (num2);

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
}

    }
}
