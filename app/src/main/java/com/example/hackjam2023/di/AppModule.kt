package com.example.hackjam2023.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.hackjam2023.data.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ) = context.dataStore

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(Android){
        install(ContentNegotiation){
            gson()
        }
    }

    @Provides
    @Singleton
    fun provideRepository(
        firestore:FirebaseFirestore,
        auth:FirebaseAuth,
        dataStore: DataStore<Preferences>,
        httpClient: HttpClient
    ) = Repository(
        firestore = firestore,
        auth = auth,
        dataStore = dataStore,
        httpClient = httpClient
    )
}