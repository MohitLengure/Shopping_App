package com.example.shopping_app.data.Repoimal

import com.example.shopping_app.Common.CATEGORY
import com.example.shopping_app.Common.ResultState
import com.example.shopping_app.domain.models.Category
import com.example.shopping_app.domain.models.productDataModel
import com.example.shopping_app.domain.repo.Repo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class repoimpal @Inject constructor(
    private val firestore: FirebaseFirestore
) : Repo {
    override fun addCategory(category: Category): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        firestore.collection(CATEGORY).add(category).addOnSuccessListener {
            trySend(ResultState.Success("Category Added Successfully"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose {
            close()
        }
    }

    //getCategories
    override suspend fun getCategories(): Flow<ResultState<List<Category>>> = callbackFlow {
        trySend(ResultState.Loading)
        firestore.collection(CATEGORY).get().addOnSuccessListener { querySnapshot ->
            val categories = querySnapshot.documents.mapNotNull { document ->
                document.toObject(Category::class.java)
            }
            trySend(ResultState.Success(categories))
        }.addOnFailureListener { exception ->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose {
            close()

        }

    }

    //addProduct

    override suspend fun addProduct(productmodels: productDataModel): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)

        firestore.collection("Products").add(productmodels).addOnSuccessListener {

            trySend(ResultState.Success("Product Successfully Added"))

        }.addOnFailureListener {

            trySend(ResultState.Error("Error : ${it.localizedMessage!!}"))
        }
        awaitClose {
            close()
        }
    }

}