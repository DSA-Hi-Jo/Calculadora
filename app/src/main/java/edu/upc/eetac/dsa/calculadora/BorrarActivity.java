package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BorrarActivity extends Activity implements View.OnClickListener {
    Intent i;
    List<String> listoperaciones = new ArrayList<String>();
    Button btnSI,btnNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        btnSI = (Button) findViewById(R.id.buttonSI);
        btnNo = (Button) findViewById(R.id.buttonNo);

        i = getIntent();
        if (!(i.getStringArrayListExtra("Lista_Operaciones").isEmpty())) {
            listoperaciones = i.getStringArrayListExtra("Lista_Operaciones");
        } else {
            Context context = getApplicationContext();
            CharSequence text = "La lista esta vacia.";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();
        }
        btnSI.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSI:
                if(!listoperaciones.isEmpty()) {
                    listoperaciones.clear();
                    Context context = getApplicationContext();
                    CharSequence text = "El historia ha sido borrado correctamente.";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                    Intent historialIntent = new Intent(this, MainActivity.class);
//                historialIntent.putStringArrayListExtra("Lista_Operaciones", (ArrayList<String>) listoperaciones);
                    startActivity(historialIntent);
                    finish();
                }
                else
                { Context context = getApplicationContext();
                    CharSequence text = "No existe historial para borrar, pulse el boton No para volver atras.";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();}
                break;
            case R.id.buttonNo:
                Intent historialIntent2 = new Intent(this, HistorialActivity.class);
                historialIntent2.putStringArrayListExtra("Lista_Operaciones", (ArrayList<String>) listoperaciones);
                startActivity(historialIntent2);
                finish();
                break;
        }
    }
}