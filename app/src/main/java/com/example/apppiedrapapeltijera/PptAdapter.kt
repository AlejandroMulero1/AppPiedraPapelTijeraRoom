package com.example.apppiedrapapeltijera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PptAdapter (
        val tasks: List<PPT>,                   // Objeto Lista de tareas
        val checkTask: (PPT) -> Unit,            // chequeo de tarea
        val deleteTask: (PPT) -> Unit            // borrado de tarea
    ) : RecyclerView.Adapter< PptAdapter.ViewHolder>() {    // Devuelve la vista

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {     // Muestra la vista (holder) y cada tarea de la lista (position)
            val item = tasks[position]                                         // Extrae la tarea de la lista
            holder.bind(item, checkTask)                           // Muestra el item en la vista (ver más adelante)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {    // Contenedor de la vista (holder) y la posición de la tarea en la lista (position)
            val layoutInflater = LayoutInflater.from(parent.context)                       // Se instancia el Layout para la vista
            return ViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))   // Devuelve la vista inflando el layout del item
        }

        override fun getItemCount(): Int {
            return tasks.size     // Devuelve el número de tareas de la lista
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {     // Clase con la vista
            val tvTask = view.findViewById<TextView>(R.id.tvTask)         // instancia del Textview de la vista

            fun bind(                                   // función que une los elementos en la vista y prepara los listeners
                task: PPT,
                deleteTask: (PPT) -> Unit
            ) {
                tvTask.text = task.nick
                itemView.setOnClickListener { deleteTask(task) }
            }
        }
    }
