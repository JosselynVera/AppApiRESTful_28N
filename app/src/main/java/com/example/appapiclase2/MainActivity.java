package com.example.appapiclase2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view)
    {
        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        EditText txtContraseña = (EditText)findViewById(R.id.txtContraseña);

        String url= "https://revistas.uteq.edu.ec/ws/login.php?usr=" + txtNombre.getText().toString() +
                "&pass=" + txtContraseña.getText().toString();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url, datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");


                // Toast.makeText(getApplicationContext(),
         //       "Hola" + txtNombre.getText().toString() + "Clave" + txtContraseña.getText().toString(),
           //     Toast.LENGTH_LONG).show();
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtRespuesta = (TextView)findViewById(R.id.txtRespuesta);
        txtRespuesta.setText(result);
    }
}