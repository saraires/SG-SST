package fundacion.view.sg_sst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Register extends AppCompatActivity {

    EditText username, name, lastname, CC, phone, cellphone, address, pass;
    CheckBox terms;
    Button rol, send;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.UsernameRg);
        name = findViewById(R.id.NameRg);
        lastname = findViewById(R.id.LastnameRg);
        CC = findViewById(R.id.CCRg);
        phone = findViewById(R.id.PhoneRg);
        cellphone = findViewById(R.id.CellphoneRg);
        address = findViewById(R.id.AdressRg);
        pass = findViewById(R.id.PasswordRg);
        terms = findViewById(R.id.TermsRg);
        rol = findViewById(R.id.RolRg); // AlertDialog
        send = findViewById(R.id.SendRg);

        send.setOnClickListener( v -> {
            SharedPreferences preferences = getSharedPreferences("Datos", MODE_PRIVATE);
            String newUser = username.getText().toString();
            String newPass = pass.getText().toString();

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("Register", newUser + "\n" + newPass );
            editor.apply();
        });

        rol.setOnClickListener(v -> showAlertDialog());
    }

    int seleccion;

    private void showAlertDialog() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Register.this);
        String[] items = {"Administrador", "Empleado", "Supervisor", "Presidente COCOLA", "Presidente BE", "Presidente COPASST"};

        builder.setTitle("Escoge Tu rol:")
                .setSingleChoiceItems(items, 0, (dialog, i) -> seleccion = i)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    preferences = getSharedPreferences("Usuario", MODE_PRIVATE);
                    editor = preferences.edit();

                    editor.putString("Rol", String.valueOf(seleccion));
                    editor.commit();
                    ShowPrincipal();
                }).show();
    }

    private void ShowPrincipal() {
        Intent principal = new Intent(this, Home.class);
        startActivity(principal);
    }

}