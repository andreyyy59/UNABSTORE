package me.andreymorales.unabstore

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirestoreRepository {

    fun agregarProducto(producto: Producto) {
        val db = Firebase.firestore
        db.collection("productos")
            .add(producto)
            .addOnSuccessListener {
                println("Producto guardado exitosamente")
            }
            .addOnFailureListener {
                println("Error al guardar producto: ${it.message}")
            }
    }

    fun obtenerProductos(callback: (List<Producto>) -> Unit) {
        val db = Firebase.firestore
        db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                val productos = result.map { doc ->
                    doc.toObject(Producto::class.java).copy(id = doc.id)
                }
                callback(productos)
            }
            .addOnFailureListener {
                println("Error al obtener productos: ${it.message}")
                callback(emptyList())
            }
    }

    fun eliminarProducto(id: String) {
        val db = Firebase.firestore
        db.collection("productos").document(id)
            .delete()
            .addOnSuccessListener {
                println("🗑Producto eliminado exitosamente")
            }
            .addOnFailureListener {
                println("Error al eliminar producto: ${it.message}")
            }
    }
}
