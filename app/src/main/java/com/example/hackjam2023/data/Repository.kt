package com.example.hackjam2023.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.hackjam2023.model.auth.UserModel
import com.example.hackjam2023.model.category.CategoryModel
import com.example.hackjam2023.model.merchant.MerchantModel
import com.example.hackjam2023.model.product.ProductModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val dataStore: DataStore<Preferences>,
    private val httpClient: HttpClient
) {
    fun register(
        email: String,
        password: String,
        name: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(
            email, password
        ).addOnSuccessListener { user ->
            if (user.user == null) {
                onFailed("Terjadi kesalahan saat registrasi")
                return@addOnSuccessListener
            }

            firestore
                .collection("user")
                .document(user.user?.uid ?: "")
                .set(
                    UserModel(
                        uid = user.user?.uid,
                        email = email,
                        name = name
                    )
                )
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { err ->
                    user
                        .user
                        ?.delete()
                        ?.addOnSuccessListener {
                            onFailed(err.message ?: "Terjadi kesalahan saat registrasi")
                        }
                }
        }.addOnFailureListener {
            onFailed(it.message ?: "Terjadi kesalahan saat registrasi")
        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailed(it.message ?: "Terjadi kesalahan saat berusaha login")
            }
    }

    fun isLogin() = auth.currentUser != null

    fun getAllCategory(
        onSuccess: (List<CategoryModel>) -> Unit,
        onFailed: (String) -> Unit
    ) {
        firestore
            .collection("category")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error.message ?: "Terjadi kesalahan saat mendapatkan kategori")
                    return@addSnapshotListener
                }

                value?.let {
                    onSuccess(
                        it.documents.map {
                            CategoryModel(
                                category_id = it["category_id"] as String,
                                word = it["word"] as String
                            )
                        }
                    )
                }
            }
    }

    fun getOwnUserInfo(
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {

    }

    fun devGetAllMerchant(
        onSuccess: (List<MerchantModel>) -> Unit,
        onFailed: (String) -> Unit
    ) {
        firestore
            .collection("merchant")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error.message ?: "Terjadi kesalahan saat mendapatkan merchant")
                    return@addSnapshotListener
                }

                value?.let {
                    onSuccess(
                        it.documents.map {
                            MerchantModel(
                                merchant_id = it["merchant_id"] as String,
                                thumbnail = it["thumbnail"] as String,
                                banner = it["banner"] as String,
                                name = it["name"] as String,
                                lat = it["lat"] as Double,
                                long = it["long"] as Double
                            )
                        }
                    )
                }
            }
    }

    fun devGetAllProduct(
        onSuccess: (List<ProductModel>) -> Unit,
        onFailed: (String) -> Unit
    ) {
        firestore
            .collection("product")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error.message ?: "Terjadi kesalahan saat mendapatkan produk")
                    return@addSnapshotListener
                }

                value?.let {
                    onSuccess(
                        it.documents.map {
                            ProductModel(
                                product_id = it["product_id"] as String,
                                merchant_id = it["merchant_id"] as String,
                                thumbnail = it["thumbnail"] as String,
                                title = it["title"] as String,
                                price = it["price"] as Long
                            )
                        }
                    )
                }
            }
    }

    suspend fun devGetMerchantByLongLat(
        long: Double,
        lat: Double
    ) = httpClient.get(
        "https://api.mapbox.com/search/geocode/v6/reverse" +
                "?language=id" +
                "&longitude=$long" +
                "&latitude=$lat" +
                "&access_token=pk.eyJ1IjoiZmFobWlndXRhd2FuIiwiYSI6ImNsbHZpc3RjNzA5YTAzZm85N2xhN3JtZXcifQ.qY4U9J90ri_t5pooxSgHFA"
    ) {
        contentType(ContentType.Application.Json)
    }

    fun devGetAllProductByMerchantId(
        merchant_id:String,
        onSuccess: (List<ProductModel>) -> Unit,
        onFailed: (String) -> Unit
    ){
        firestore
            .collection("product")
            .whereEqualTo("merchant_id", merchant_id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error.message ?: "Terjadi kesalahan saat mendapatkan produk")
                    return@addSnapshotListener
                }

                value?.let {
                    onSuccess(
                        it.documents.map {
                            ProductModel(
                                product_id = it["product_id"] as String,
                                merchant_id = it["merchant_id"] as String,
                                thumbnail = it["thumbnail"] as String,
                                title = it["title"] as String,
                                price = it["price"] as Long
                            )
                        }
                    )
                }
            }
    }

    fun devGetMerchantByMerchantId(
        merchant_id: String,
        onSuccess: (MerchantModel) -> Unit,
        onFailed: (String) -> Unit
    ){
        firestore
            .collection("merchant")
            .document(merchant_id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error.message ?: "Terjadi kesalahan saat mendapatkan produk")
                    return@addSnapshotListener
                }

                value?.let {
                    onSuccess(
                        MerchantModel(
                            merchant_id = it["merchant_id"] as String,
                            thumbnail = it["thumbnail"] as String,
                            banner = it["banner"] as String,
                            name = it["name"] as String,
                            lat = it["lat"] as Double,
                            long = it["long"] as Double
                        )
                    )
                }
            }
    }
}