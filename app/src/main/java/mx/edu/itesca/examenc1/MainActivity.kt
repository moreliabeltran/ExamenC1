package mx.edu.itesca.examenc1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //val vehiculo = findViewById<EditText>(R.id.etNombre)
        val precio1y = findViewById<TextView>(R.id.tvPrecioYear)
        val years = findViewById<EditText>(R.id.etYears)
        val totalFinal = findViewById<TextView>(R.id.tvTotal)
        val calcular = findViewById<Button>(R.id.btnCalcular)
        val spinner = findViewById<Spinner>(R.id.spTipo)

        // Precios pal spinner
        val valores = listOf(500, 1200, 700)

        // adaptador del spinner para tomar los valores q están en la lista de strings
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_lista,
            android.R.layout.simple_spinner_item
        )

        //Esto es para q las opciones caigan para aabajo
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //el listener pa saber q seleccionó
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?, //cual fué
                selectedItemView: View?, //la vista
                position: Int, //posición
                id: Long //id pues es la posición
            ) {
                // precio de la opción con base en la posición del desplegable
                val valorSeleccionado = valores[position]
                precio1y.setText(valorSeleccionado.toString())
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // no hace nada
            }
        }

        // Configurar el botón Calcular
        calcular.setOnClickListener {
            val yearsIngresados = years.text.toString().toIntOrNull() ?: 0 // Se convierte en entero o 0 si no pusieron nada

            val precioUnitario =
                precio1y.text.toString().toInt() // si el textview está vacío es cero

            // calcular total
            val total = precioUnitario * yearsIngresados
            totalFinal.setText(total.toString())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}