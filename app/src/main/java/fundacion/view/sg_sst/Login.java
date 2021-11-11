package fundacion.view.sg_sst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText nombre, contrasena;
    Button entrar, registro;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombre = findViewById(R.id.UserLogin);
        contrasena = findViewById(R.id.PasswordLogin);
        entrar = findViewById(R.id.btninicioLogin);
        registro = findViewById(R.id.btnregistroLogin);

        entrar.setOnClickListener( view ->  {
            String user = nombre.getText().toString();
            String pass = contrasena.getText().toString();

            preferences = getSharedPreferences("Datos", MODE_PRIVATE);
            editor = preferences.edit();

            editor.putString("Login", user + "\n" + pass );
            editor.commit();
        });

        registro.setOnClickListener(view ->  {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });

    }
}