package com.example.app_pruebafirebase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;
public class cambioDatos extends AppCompatActivity {
    Button cambiarD;
    EditText campo,valor;
    DatabaseReference dbRef;
    DatosUsuario datosUsu;
    View ventana;
    int edadCamb,telefCamb;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_datos);

        cambiarD=findViewById(R.id.btnCambiarDatos);
        campo=findViewById(R.id.datoCambio);
        valor=findViewById(R.id.valorCampo);
        ventana=findViewById(R.id.ventana);

        String nombreID=getIntent().getStringExtra("nombreC");

        dbRef= FirebaseDatabase.getInstance().getReference().child("datosUsu");
        datosUsu=new DatosUsuario();
        String campotxt=campo.getText().toString();
        String valortxt=valor.getText().toString();
        /*if(!(edad.getText().toString()).equals("")){ //si no es nulo
            edadCamb= Integer.parseInt(String.valueOf(edad.getText()));
        }
        if(!(telef.getText().toString()).equals("")){
            telefCamb=Integer.parseInt(String.valueOf(telef.getText()));
        }*/
        cambiarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> datosC=new HashMap<>();
                if(campotxt.equals("nombre")){
                    datosC.put("nombre",valortxt);
                    dbRef.child(nombreID).updateChildren(datosC);
                }else if(campotxt.equals("apellidos")){
                    datosC.put("apellidos",valortxt);
                    dbRef.child(nombreID).updateChildren(datosC);
                }else if(campotxt.equals("edad")){
                    datosC.put("edad",valortxt);
                    dbRef.child(nombreID).updateChildren(datosC);
                }else if(campotxt.equals("telefono")){
                    datosC.put("telefono",valortxt);
                    dbRef.child(nombreID).updateChildren(datosC);
                }
                campo.setText("");
                valor.setText("");

            Intent volver=new Intent(cambioDatos.this,MainActivity.class);
            startActivity(volver);
            }
        });
    }
}