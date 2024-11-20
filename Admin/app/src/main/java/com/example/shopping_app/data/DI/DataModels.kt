package com.example.shopping_app.data.DI

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModels{

    @Provides
    fun provideFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }


}
