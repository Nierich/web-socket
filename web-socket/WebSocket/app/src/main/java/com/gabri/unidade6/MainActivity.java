package com.gabri.unidade6;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; // Add this import for TextView
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario = null;
    private EditText etSenha = null;
    private TextView tvResposta = null;
    private Button btEnviar = null; // Add declaration for Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etSenha = (EditText) findViewById(R.id.et_senha);

        tvResposta = findViewById(R.id.tv_resposta); // No need to cast
        btEnviar = findViewById(R.id.bt_enviar); // Initialize the Button
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsuario.getText().toString().isEmpty() && etSenha.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
                } else if (etUsuario.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha o campo Usuário", Toast.LENGTH_LONG).show();
                } else if (etSenha.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha o campo Senha", Toast.LENGTH_LONG).show();
                } else {
                    execute();
                }
            }
        });
    }
    public void execute() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String urlConexao = "http://192.168.3.29:8080/Teste/";   // link da API ou webpage
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,                                        // Método
                urlConexao,                                                // link (acima)
                new Response.Listener<String>() { // Corrected syntax here
                    @Override
                    public void onResponse(String response) {             // o que fazer com a resposta
                        tvResposta.setText(response);
                    }
                },
                new Response.ErrorListener() {                            // se der erro
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvResposta.setText("Erro" + error);
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() {                   // o que enviar
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("usuario", etUsuario.getText().toString());
                params.put("senha", etSenha.getText().toString());

                return params;
            }
        };

        queue.add(stringRequest);

    }
}