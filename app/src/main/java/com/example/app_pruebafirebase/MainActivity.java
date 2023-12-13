package com.example.app_pruebafirebase;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity extends AppCompatActivity {
    EditText nom,apell,edd,telef;
    Button insertar,mostrar,cambiar,borrar;
    DatabaseReference dbRef;
    DatosUsuario datosUsu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom=findViewById(R.id.nombre);
        apell=findViewById(R.id.apell);
        edd=findViewById(R.id.edad);
        telef=findViewById(R.id.tlfno);
        insertar=findViewById(R.id.btnInsertar);
        mostrar=findViewById(R.id.btnMostrar);
        cambiar=findViewById(R.id.btnCambiar);
        borrar=findViewById(R.id.btnBorrar);

        dbRef= FirebaseDatabase.getInstance().getReference().child("datosUsu");
        datosUsu=new DatosUsuario();
        //String claveUsu=dbRef.push().getKey();

        insertar.setOnClickListener(new View.OnClickListener() { //FUNCIONA
            @Override
            public void onClick(View view) {
                //inserta los datos poniendo como clave el nombre del usuario
                String nombreInsert=nom.getText().toString().trim();
                datosUsu.setNombre(nombreInsert);
                datosUsu.setApellidos(apell.getText().toString().trim());
                int edad=Integer.parseInt(edd.getText().toString().trim());
                int numTelef=Integer.parseInt(telef.getText().toString().trim());
                datosUsu.setEdad(edad);
                datosUsu.setTelefono(numTelef);
                dbRef.child(nombreInsert).setValue(datosUsu);

                nom.setText("");
                apell.setText("");
                edd.setText("");
                telef.setText("");
            }
        });
        mostrar.setOnClickListener(new View.OnClickListener() { //FUNCIONA
            @Override
            public void onClick(View v) {
                //muestra todos los datos de la bbdd cuyo nombre sea el que se ha puesto en el campo
                Intent muestra=new Intent(MainActivity.this,muestraDatos.class);
                muestra.putExtra("nombreM",nom.getText().toString()); //pasar datos a la otra actividad
                muestra.putExtra("edadM",edd.getText().toString());
                startActivity(muestra);
            }
        });
        cambiar.setOnClickListener(new View.OnClickListener() { //NO FUNCIONA
            @Override
            public void onClick(View v) {
                /** EN REVISION
                 * con el nombre que se pone en el campo al darle al boton de cambiar, se pasa
                 * el nombre escrito y cambia los datos cuyo nombre sea ese, hay que pedir
                 * todos los datos nuevamente
                 */
                Intent cambio=new Intent(MainActivity.this, cambioDatos.class);
                cambio.putExtra("nombreC",nom.getText().toString());
                startActivity(cambio);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() { //FUNCIONA
            @Override
            public void onClick(View v) {
                //borra el dato cuyo nombre sea el que se pone en el campo del nombre
                String nomB=nom.getText().toString();
                dbRef.child(nomB).removeValue();
                nom.setText("");
            }
        });
    }
}