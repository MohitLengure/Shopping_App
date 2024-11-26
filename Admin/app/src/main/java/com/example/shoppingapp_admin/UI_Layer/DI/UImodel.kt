package com.example.shoppingapp_admin.UI_Layer.DI

import com.example.shoppingapp_admin.data.Repoimal.repoimpal
import com.example.shoppingapp_admin.domain.repo.Repo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UImodel{

    @Provides
    fun provideRepo(
        firestore: FirebaseFirestore,
        firebaseStorage: FirebaseStorage
    ):Repo{
        return repoimpal(
            firestore
            ,firebaseStorage)
    }


}
