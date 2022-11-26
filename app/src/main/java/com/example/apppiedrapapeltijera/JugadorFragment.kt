package com.example.apppiedrapapeltijera

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class JugadorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var listener: Controlador?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jugador, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Controlador){
            listener=context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Ajusto la visibilidad y paso al main la opcion elegida
        //Elección piedra
        view.findViewById<ImageButton>(R.id.imgPiedra).setOnClickListener {
            listener?.obtenerResultado(0)
            view.findViewById<ImageView>(R.id.imgVistaRoca).visibility=View.VISIBLE
            view.findViewById<ImageView>(R.id.imgVistaTijeras).visibility=View.INVISIBLE
            view.findViewById<ImageView>(R.id.imgVistaPapel).visibility=View.INVISIBLE

        }
        //Elección papel
        view.findViewById<ImageButton>(R.id.imgPapel).setOnClickListener {
            listener?.obtenerResultado(1)
            view.findViewById<ImageView>(R.id.imgVistaPapel).visibility=View.VISIBLE
            view.findViewById<ImageView>(R.id.imgVistaRoca).visibility=View.INVISIBLE
            view.findViewById<ImageView>(R.id.imgVistaTijeras).visibility=View.INVISIBLE

        }
        //Eleccion tijeras
        view.findViewById<ImageButton>(R.id.imgTijeras).setOnClickListener {
            listener?.obtenerResultado(2)
            view.findViewById<ImageView>(R.id.imgVistaTijeras).visibility=View.VISIBLE
            view.findViewById<ImageView>(R.id.imgVistaRoca).visibility=View.INVISIBLE
            view.findViewById<ImageView>(R.id.imgVistaPapel).visibility=View.INVISIBLE

        }

    }
    companion object {
        fun newInstance(param1: String, param2: String) =
            JugadorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}