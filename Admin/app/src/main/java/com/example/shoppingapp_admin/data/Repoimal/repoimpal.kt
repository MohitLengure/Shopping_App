package com.example.shoppingapp_admin.data.Repoimal

import android.net.Uri
import android.util.Log
import com.example.shoppingapp_admin.Common.CATEGORY
import com.example.shoppingapp_admin.Common.PRODUCT
import com.example.shoppingapp_admin.Common.ResultState
import com.example.shoppingapp_admin.domain.models.Category
import com.example.shoppingapp_admin.domain.models.productDataModel
import com.example.shoppingapp_admin.domain.repo.Repo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class repoimpal @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
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

        firestore.collection(PRODUCT).add(productmodels).addOnSuccessListener {

            trySend(ResultState.Success("Product Successfully Added"))

        }.addOnFailureListener {

            trySend(ResultState.Error("Error : ${it.localizedMessage!!}"))
        }
        awaitClose {
            close()
        }
    }



    override suspend fun UploadProductimage(image: Uri): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)

        storage.reference.child("Products/${System.currentTimeMillis()}")
            .putFile(image ?: Uri.EMPTY).addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener {
                    trySend(ResultState.Success(it.toString()))
                }
                if (it.error != null)
                {
                    trySend(ResultState.Error(it.error!!.message.toString()))
                }
            }
        awaitClose{
            close()
        }
    }

    override suspend fun UploadCategoryimage(image: Uri): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)

        storage.reference.child("Category/${System.currentTimeMillis()}")
            .putFile(image ?: Uri.EMPTY).addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener {
                    Log.d("TAG3", "UploadCategoryImage: $it")
                    trySend(ResultState.Success(it.toString()))
                }
                if (it.error != null)
                {
                    trySend(ResultState.Error(it.error!!.message.toString()))
                }
            }
        awaitClose{
            close()
        }
    }

}