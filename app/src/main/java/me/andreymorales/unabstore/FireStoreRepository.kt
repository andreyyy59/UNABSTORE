package me.andreymorales.unabstore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import me.andreymorales.unabstore.Producto

class FirestoreRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("productos")

    fun agregarProducto(producto: Producto) {
        val db = Firebase.firestore
        db.collection("productos")
            .add(producto)
            .addOnSuccessListener { /* manejar éxito */ }
            .addOnFailureListener { /* manejar error */ }
    }

    fun obtenerProductos(callback: (List) -> Unit) {
        val db = Firebase.firestore
        db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                val productos = result.map { doc ->
                    doc.toObject(Producto::class.java).copy(id = doc.id)
                }
                callback(productos)
            }
    }

    fun eliminarProducto(id: String) {
        val db = Firebase.firestore
        db.collection("productos").document(id)
            .delete()
    }
}
