package com.example.apppiedrapapeltijera
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class Login : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Obtengo la instancia del servicio de autenticación de Firebase
        auth = FirebaseAuth.getInstance()

        //Genero un GoogleSignInOptions que contendra lo que necesita mi login, un correo,
        //la id del cliente
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        //Genero el cliente del login con el options ya definido
        googleSignInClient = GoogleSignIn.getClient(this , gso)

        //Agrego un evento onClick al boton iniciar del Login
        findViewById<Button>(R.id.btnIniciar).setOnClickListener {
            signInGoogle()
        }
    }



    /**
     * Metodo que se lanza al pulsar el boton Iniciar, este metodo se encargara de lanzar
     * el login de googgle
     */
    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        //Ejecuto el intent del login con una variable launcher
        launcher.launch(signInIntent)
    }

    /**
     * Variable launcher que lanzara el login, recoge el resultado y si es válido lanzará el metodo
     * handleResult
     */
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){
            //Almaceno el resultado en una constante para mandarsela al handleResult
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    /**
     * Metodo que gestiona el dato recogido del login y si es valido actualizara la UI,
     * en nuestro caso, mandara al usuario a la pagina del juego
     */
    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            //Almaceno el resultado de la variable task en una constante de GoogleSignInAccount
            val account : GoogleSignInAccount? = task.result
            //Si la cuenta es valida, la mando para que "actualize" la interfaz de usuario, es decir
            //que mande al usuario al juego
            if (account != null){
                updateUI(account)
            }
        }
        //Si la cuenta no es valida, notifico con un toast al usuario del error
        else{
            Toast.makeText(this, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Metodo que se encargara de realizar los cambios oportunos con los datos obtenidos
     * del login de google, en nuestro caso, manda al usuario a la pagina del juego recogiendo
     * su nombre
     */
    private fun updateUI(account: GoogleSignInAccount) {
        //Credenciales con los datos necesarios
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            //Si se obtienen los credenciales de manera exitosa
            if (it.isSuccessful){
                //Mando al usuario a la pagina del juego pasando como parametro sus datos
                val intent = Intent(this , MainActivity::class.java)
                intent.putExtra("name" , account.displayName)
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()
            }
        }
    }
    }
