package com.example.shopping_app.UI_Layer.DI

import com.example.shopping_app.data.Repoimal.repoimpal
import com.example.shopping_app.domain.repo.Repo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UImodel{

    @Provides
    fun provideRepo(
        firestore: FirebaseFirestore
    ):Repo{
        return repoimpal(firestore)

    }


}
