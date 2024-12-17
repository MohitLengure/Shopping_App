package com.example.shoppingapp_user.data.repoimpl

import android.os.Build.PRODUCT
import com.example.shoppingapp_user.CATEGORY
import com.example.shoppingapp_user.ResultState
import com.example.shoppingapp_user.domain.Repo.Repo
import com.example.shoppingapp_user.domain.models.Category
import com.example.shoppingapp_user.domain.models.ProductModels
import com.example.shoppingapp_user.domain.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class repoimpl @Inject constructor(private val Firebasefirestore: FirebaseFirestore, private val firebaseAuth: FirebaseAuth) : Repo {

    override suspend fun getAllCategory(): Flow<ResultState<List<Category>>> = callbackFlow {
        trySend(ResultState.Loading)

        Firebasefirestore.collection(CATEGORY).get().addOnSuccessListener {
            val categoryData = it.documents.mapNotNull {
                it.toObject(Category::class.java)
            }
            trySend(ResultState.Success(categoryData))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }
    }

    override suspend fun addCategories(): Flow<ResultState<List<Category>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addCategory(productModels: ProductModels): Flow<ResultState<List<Category>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProducts(): Flow<ResultState<List<ProductModels>>> = callbackFlow {

        trySend(ResultState.Loading)

        Firebasefirestore.collection(PRODUCT).get().addOnSuccessListener {

            val productData = it.documents.mapNotNull {
                it.toObject(ProductModels::class.java)
            }
            trySend(ResultState.Success(productData))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }
    }

    override suspend fun registerUser(UserData: UserData): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseAuth.createUserWithEmailAndPassword(UserData.email, UserData.password).addOnSuccessListener {
            Firebasefirestore.collection("USERS").document(it.user?.uid.toString()).set(UserData).addOnSuccessListener {
                trySend(ResultState.Success("User Registered Successfully"))
            }.addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }

    }

    override suspend fun loginwithemailpassword(
        userEmail: String,
        userPassword: String
    ): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnSuccessListener {
            trySend(ResultState.Success("Login Successfully"))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
    }
        awaitClose {
            close()
        }
    }

    override suspend fun getProductById(productId: String): Flow<ResultState<ProductModels>> = callbackFlow {

        trySend(ResultState.Loading)
        Firebasefirestore.collection(PRODUCT).document(productId).get().addOnSuccessListener {
            val product = it.toObject(ProductModels::class.java)
            trySend(ResultState.Success(product!!))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
        }
        awaitClose {
            close()
        }

    }

}
