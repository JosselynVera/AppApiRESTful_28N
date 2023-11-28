package com.example.appapiclase2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

    public void LoginViewVoley(View view)
    {
        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        EditText txtContraseña = (EditText)findViewById(R.id.txtContraseña);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url= "https://revistas.uteq.edu.ec/ws/login.php?usr=" + txtNombre.getText().toString() +
                "&pass=" + txtContraseña.getText().toString();
        TextView txtRespuesta = (TextView)findViewById(R.id.txtRespuesta);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtRespuesta.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtRespuesta.setText("ERROR" + error.getMessage());
                    }
                });
        queue.add(stringRequest);

    }
}