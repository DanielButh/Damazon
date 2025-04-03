package com.example.damazon.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel: ViewModel() {
    private val _loaderState = MutableLiveData<Boolean>()
    // MutableLiveData: tipo de dato reactivo que va a poder cambiar su valor
    // Los () lo inicializan
    // Publisher
    val loaderState: LiveData<Boolean>
    // LiveData: este tipo de dato no va a cambiar
        get() = _loaderState
    private val firebase = FirebaseAuth.getInstance()

    fun requestSignUp(email: String, password: String) {
        _loaderState.value = true
        viewModelScope.launch { //lanzar la corrutina
            val result = firebase.createUserWithEmailAndPassword(email, password).await() //await(): suspender la acción
            // No se va a ejecutar hasta que la función de arriba obtenga un resultado
            //result se convierte en un objeto de firebase
            _loaderState.value = false
            result.user?.let { // Si puedo acceder al valor de lo que hay en esa propiedad, significa que la propiedad no está vacía
                // Entonces, ejecuta esto
                Log.i("Firebase", "Se pudo crear el usuario")
            } ?: run {
                // Sino, mostrarle un error
                Log.i("Firebase", "Ocurrio un problema")
            }
        }
    }
}
