package com.example.shopping_app_user.presentation


import com.example.shoppingapp_user.data.repoimpl.repoimpl
import com.example.shoppingapp_user.domain.Repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object uimodels {

    @Provides
    fun provideRepo(
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): Repo {
        return repoimpl(firestore,firebaseAuth)
    }

}