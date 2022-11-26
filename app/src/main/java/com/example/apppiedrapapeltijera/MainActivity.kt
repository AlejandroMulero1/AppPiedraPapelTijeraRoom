package com.example.apppiedrapapeltijera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), Controlador {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     * Metodo que gestiona todo el juego
     * @param eleccionJugador:Parametro obtenido de "JugadorFragment" que contiene la opcion
     * elegida por el jugador
     */
    override fun obtenerResultado(eleccionJugador: Int) {
        super.obtenerResultado(eleccionJugador)
        val estado:String
        val numRandom = (0..2).random()
            mostrarImagen(numRandom)
            estado=comparar(eleccionJugador, numRandom)
            mostrarMensaje(estado)
        //AjustarVariables
    }

    /**
     *Metodo que compara la eleccion del usuario y la generada por la maquina
     * @param eleccionJugador:Parametro obtenido de "obtenerResultado" que contiene la opcion
     * elegida por el jugador
     * @param numRandom:Parametro generado por la maquina que contiene la opcion "elegida"
     * por la maquina
     * @return: una cadena que contiene el resultado de la comparacion
     */
    fun comparar(eleccionJugador: Int, numRandom: Int): String{
        var estado=""
        //Caso empate
        if (eleccionJugador==numRandom){
            estado="empate"
        }
        //Caso piedra
        else if(eleccionJugador==0){
            if (numRandom==1) estado="derrota" //Piedra vs Papel
            if (numRandom==2) estado="victoria" //Piedra vs Tijeras
        }
        //Caso papel
        else if(eleccionJugador==1){
            if (numRandom==0) estado="victoria" //Papel vs Piedra
            if (numRandom==2) estado="derrota" //Papel vs Tijeras
        }
        //Caso tijeras
        else if(eleccionJugador==2){
            if (numRandom==0) estado="derrota" //Tijeras vs Piedra
            if (numRandom==1) estado="victoria" //Tijeras vs Papel
        }
        return estado
    }

    /**
     * Metodo que muestra el mensaje por pantalla correspondiente en funcion a la elección del jugador
     * y la maquina
     * @param estado:Parametro obtenido del metodo "comparar" que contiene el resultado
     * de la comprobacion
     */
    fun mostrarMensaje(estado:String){
        val builder = AlertDialog.Builder(this)
        var titulo=""
        var mensaje=""
        if (estado=="empate"){
            titulo="Empate"
            mensaje="Al menos no has perdido"
        }
        else if (estado=="derrota"){
            titulo="Derrota"
            mensaje="Has perdido, eres malo"
        }
        else if (estado=="victoria"){
            titulo="Victoria"
            mensaje="Qpro"
        }
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.show()
    }

    /**
     * Metodo que muestra por pantalla la imagen correspondiente en el fragmento de la maquina
     * @param: Numero generado aleatoriamente que contiene la "elección" de la maquina
     */
    fun mostrarImagen(numRandom:Int){
        //Caso Piedra
        if (numRandom==0){
            findViewById<ImageView>(R.id.imgVistaRocaMaquina).visibility=View.VISIBLE
            findViewById<ImageView>(R.id.imgVistaTijerasMaquina).visibility=View.INVISIBLE
            findViewById<ImageView>(R.id.imgVistaPapelMaquina).visibility=View.INVISIBLE
        }
        //Caso Papel
        else if(numRandom==1){
            findViewById<ImageView>(R.id.imgVistaRocaMaquina).visibility=View.INVISIBLE
            findViewById<ImageView>(R.id.imgVistaTijerasMaquina).visibility=View.INVISIBLE
            findViewById<ImageView>(R.id.imgVistaPapelMaquina).visibility=View.VISIBLE
        }
        //Caso Tijeras
        else if(numRandom==2){
            findViewById<ImageView>(R.id.imgVistaRocaMaquina).visibility=View.INVISIBLE
            findViewById<ImageView>(R.id.imgVistaTijerasMaquina).visibility=View.VISIBLE
            findViewById<ImageView>(R.id.imgVistaPapelMaquina).visibility=View.INVISIBLE
        }
    }
}