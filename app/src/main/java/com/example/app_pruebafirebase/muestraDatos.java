package com.example.app_pruebafirebase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class muestraDatos extends AppCompatActivity {
    DatabaseReference dbRef;
    DatosUsuario datosUsu;
    TextView n,a,e,t;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_datos);
        n=findViewById(R.id.nomD);
        a=findViewById(R.id.apellD);
        e=findViewById(R.id.edadD);
        t=findViewById(R.id.telefD);

        dbRef= FirebaseDatabase.getInstance().getReference().child("datosUsu");
        datosUsu=new DatosUsuario();

        String datoNom=getIntent().getStringExtra("nombreM");
        String edad=getIntent().getStringExtra("edadM");
        int eM=Integer.parseInt(edad);
        Query consulta=dbRef.orderByChild("edad").equalTo(eM);

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    DatosUsuario du=ds.getValue(DatosUsuario.class);
                    String nombre=du.getNombre();
                    String apell=du.getApellidos();
                    int edad=du.getEdad();
                    int telef=du.getTelefono();
                    //n.setText(nombre);
                    n.append(nombre+"\t");
                    n.append(apell+" ");
                    n.append(String.valueOf(edad)+" ");
                    n.append(String.valueOf(telef)+"\n");
                    /*a.setText(apell);
                    e.setText(String.valueOf(edad));
                    t.setText(String.valueOf(telef));*/
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}