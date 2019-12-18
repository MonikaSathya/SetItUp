package com.example.nikethana.setitup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import com.google.android.gms.maps.model.LatLng;

public class Project extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Enter_destination, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        Spinner spinner1 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Enter_transport_mode, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
    }
    public void Open(View v)
    {
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String s = adapterView.getItemAtPosition(i).toString();
        Destination(s);

        Toast toast = Toast.makeText(adapterView.getContext(), " " + s, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void Destination(String s)
    {
        double latitude = 0, longitude = 0;
        Log.d("HELLOOOOOOOOOO", "Value: " + (s));
        switch (s) {
            case "Ambattur":

                latitude = 13.1143;
                longitude = 80.1481;
                LatLng latLng1 = new LatLng(latitude, longitude);          //ambattur
                Intent intent = new Intent(this, MapsActivity.class);
                intent.putExtra("dest", latLng1);
                startActivity(intent);
                break;
            case "Valasaravakkam":
                latitude = 13.0485;
                longitude = 80.1775;
                LatLng latLng2 = new LatLng(latitude, longitude);
                Intent intent1 = new Intent(this, MapsActivity.class);
                intent1.putExtra("dest", latLng2);
                startActivity(intent1);                                     //valasaravakkam
                break;
            case "Ashok Nagar":
                latitude = 13.0374;
                longitude = 80.2123;
                LatLng latLng3 = new LatLng(latitude, longitude);
                Intent intent2 = new Intent(this, MapsActivity.class);
                intent2.putExtra("dest", latLng3);
                startActivity(intent2);
                //ashok nagar
                break;
            case "Purasaiwalkam":
                latitude = 13.0897; //13.0410;
                longitude =80.2541;// 80.1994;
                LatLng latLng4 = new LatLng(latitude, longitude);
                Intent intent3 = new Intent(this, MapsActivity.class);
                intent3.putExtra("dest", latLng4);
                startActivity(intent3);                                          //purasai
                break;
            case "Virugambakkam":
                latitude = 13.0531;
                longitude = 80.1931;
                LatLng latLng5 = new LatLng(latitude, longitude);          //virugambakkam
                Intent intent4 = new Intent(this, MapsActivity.class);
                intent4.putExtra("dest", latLng5);
                startActivity(intent4);
                break;
            case "Coimbatore":
                latitude = 11.0168;
                longitude = 76.9558;
                LatLng latLng6 = new LatLng(latitude, longitude);          //coimbatore
                Intent intent5 = new Intent(this, MapsActivity.class);
                intent5.putExtra("dest", latLng6);
                startActivity(intent5);
                break;
            case "Surapet":
                latitude = 13.1454;
                longitude = 80.1838;
                LatLng latLng7 = new LatLng(latitude, longitude);          //surapet
                Intent intent6 = new Intent(this, MapsActivity.class);
                intent6.putExtra("dest", latLng7);
                startActivity(intent6);
            default:
                break;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast toast = Toast.makeText(getApplicationContext(), "Enter something", Toast.LENGTH_SHORT);
        toast.show();
    }

}
