package sad.ru.testros.fragments.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_map.*
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment
import sad.ru.testros.base.Consts


class MapFragment : BaseFragment(), MapView, OnMapReadyCallback {
    override fun layoutId(): Int = R.layout.fragment_map

    private lateinit var map: GoogleMap

    private lateinit var circle: Circle
    private lateinit var marker: Marker

    private var mainLocation: Location? = null

    @InjectPresenter
    lateinit var presenter: MapPresenter

    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationFusedLocationProviderClient: FusedLocationProviderClient

    override fun isShowBottomMenu(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMap()
    }

    override fun onMapReady(gMap: GoogleMap?) {
        setMap(gMap)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != Consts.LOCATION_REQUEST_CODE) {
            return
        }

        if (ContextCompat.checkSelfPermission(
                this.requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            enableMyLocation()
        }
    }

    private fun initCircleAndMarker() {
        val icon = getBitmapDescriptor(R.drawable.ic_gps_marker_black)
        val mp = MarkerOptions()
            .icon(icon)
            .visible(false)
            .position(LatLng(0.0, 0.0))

        val circleOptions = CircleOptions()
        circleOptions.center(LatLng(0.0, 0.0))
        circleOptions.radius(0.0)
        circleOptions.fillColor(0x15808080)
        circleOptions.strokeWidth(2f)

        circle = map.addCircle(circleOptions)
        marker = map.addMarker(mp)
    }

    private fun getBitmapDescriptor(@DrawableRes id: Int): BitmapDescriptor? {
        val vectorDrawable = context!!.getDrawable(id)
        val h = convertDpToPixel(38f, this.requireContext()).toInt()
        val w = convertDpToPixel(38f, this.requireContext()).toInt()

        vectorDrawable!!.setBounds(0, 0, w, h)

        val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)

        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bm)
    }

    private fun initLocationClient() {
        locationFusedLocationProviderClient = FusedLocationProviderClient(this.requireActivity())
        locationFusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun initLocationButton() {
        fab.setOnClickListener {
            moveCamera()
        }
    }

    private fun moveCamera() {
        if (mainLocation != null) {
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        mainLocation!!.latitude,
                        mainLocation!!.longitude
                    ), 16f
                )
            )
        }
    }

    private fun initLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(res: LocationResult?) {
                super.onLocationResult(res)
                mainLocation = res?.lastLocation
                setMarkerPosition(res?.lastLocation)
            }
        }
    }

    private fun initLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
    }

    private fun setMarkerPosition(location: Location?) {
        if (location != null) {
            marker.position = LatLng(location.latitude, location.longitude)
            marker.isVisible = true
            circle.center = LatLng(location.latitude, location.longitude)
            circle.radius = location.accuracy.toDouble()
        }
    }

    private fun setMap(gMap: GoogleMap?) {
        map = gMap ?: return
        gMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        gMap.uiSettings.isZoomGesturesEnabled = true
        gMap.uiSettings.isRotateGesturesEnabled = true
        gMap.uiSettings.isMyLocationButtonEnabled = false
        gMap.uiSettings.isCompassEnabled = false

        initCircleAndMarker()

        enableMyLocation()
    }

    private fun initMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (ContextCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = false

            initLocationCallback()
            initLocationRequest()
            initLocationClient()
            initLocationButton()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), Consts.LOCATION_REQUEST_CODE
            )
        }
    }
}