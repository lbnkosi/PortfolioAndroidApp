package za.co.lbnkosi.portfolio.data.source.remote.firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

object Extensions {

    fun FirebaseFirestore.path(uid: String): DocumentReference {
        return this.collection("chat").document(uid)
    }

}