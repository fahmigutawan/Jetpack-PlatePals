package com.example.hackjam2023.presentation.merchant_map.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hackjam2023.R
import com.example.hackjam2023.component.merchant_map.MerchantMapBottomSheet
import com.example.hackjam2023.databinding.MapLayoutBinding
import com.example.hackjam2023.databinding.MerchantContainerBinding
import com.example.hackjam2023.presentation.merchant_map.viewmodel.MerchantMapViewModel
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.Plugin
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.maps.plugin.annotation.Annotation
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createCircleAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import org.jetbrains.annotations.Async

@SuppressLint("InflateParams")
@Composable
fun MerchantMapScreen(
    navController: NavController
) {
    val map = remember { mutableStateOf<MapView?>(null) }

    val viewModel = hiltViewModel<MerchantMapViewModel>()
    val context = LocalContext.current
    val mapCameraOptions = CameraOptions
        .Builder()
        .center(
            Point.fromLngLat(
                viewModel.userLong.value,
                viewModel.userLat.value
            )
        )
    val locationProvider = LocationServices.getFusedLocationProviderClient(context)
    val locationRequest = LocationRequest.create().apply {
        interval = 3000
        fastestInterval = 2000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    val callback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            p0.lastLocation?.let {
                viewModel.userLat.value = it.latitude
                viewModel.userLong.value = it.longitude
            }
        }
    }

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }

    if (viewModel.pickedMerchant.value != null) {
        viewModel.pickedMerchant.value?.let {
            MerchantMapBottomSheet(
                item = it,
                place_name = viewModel.pickedPlaceName.value,
                onDismiss = {
                    viewModel.pickedMerchant.value = null
                    viewModel.pickedPlaceName.value = null
                }
            )
        }
    }

    if (viewModel.merchants.isNotEmpty() && map.value != null) {
        map.value?.let { mapNotNull ->
            viewModel.merchants.forEach { merchant ->
                val mgr = mapNotNull.viewAnnotationManager

                val p = mgr.addViewAnnotation(
                    R.layout.merchant_container,
                    viewAnnotationOptions {
                        geometry(
                            Point.fromLngLat(
                                merchant.long,
                                merchant.lat
                            )
                        )
                    }
                )

                p.findViewById<ComposeView>(R.id.merchant_container_compose).setContent {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clickable {
                                viewModel.pickedMerchant.value = merchant
                                viewModel.devGetMerchantPlaceName()
                            },
                        contentAlignment = Alignment.TopCenter
                    ) {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = R.drawable.tooltip_merchant,
                            contentDescription = "",
                            contentScale = ContentScale.Fit
                        )

                        AsyncImage(
                            modifier = Modifier
                                .padding(16.dp)
                                .offset(y = (-4).dp)
                                .clip(CircleShape),
                            model = merchant.thumbnail,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }

    locationProvider
        .getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
        .addOnSuccessListener {
            // UNCOMMENT WHEN PRESENTATING

//            viewModel.userLat.value = it.latitude
//            viewModel.userLong.value = it.longitude

            map.value?.camera?.flyTo(
                mapCameraOptions.build()
            )

//            locationProvider.requestLocationUpdates(
//                locationRequest,
//                callback,
//                Looper.getMainLooper()
//            )
        }

    Box {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                LayoutInflater.from(it).inflate(R.layout.map_layout, null)
            },
            update = {
                val binding = MapLayoutBinding.bind(it)
                map.value = binding.mapView

                //Marker
                map.value?.let { map ->
                    val point = Point.fromLngLat(
                        viewModel.userLong.value,
                        viewModel.userLat.value
                    )
                    val annotationApi = map.annotations
                    val circleAnnotationManager = annotationApi.createCircleAnnotationManager(map)
                    val circleAnnotationOptions: CircleAnnotationOptions = CircleAnnotationOptions()
                        .withPoint(
                            point
                        )
                        .withCircleRadius(8.0)
                        .withCircleColor("#ee4e8b")
                        .withCircleStrokeWidth(2.0)
                        .withCircleStrokeColor("#ffffff")
                    circleAnnotationManager.create(circleAnnotationOptions)
                }
            }
        )
    }
}