package com.example.rxjavademo.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;

import com.example.rxjavademo.databinding.ActivityCellInfoBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * Reference: https://gist.github.com/eslamfaisal/e440c0b0bc0b92311b89d37582218102
 */
public class CellInfoActivity extends AppCompatActivity {

    private ActivityCellInfoBinding binding;
    private LocationManager locationManager;
    private TelephonyManager telePhonyManager;
    private final int LOCATION_PERMISSION_CODE = 121;

    private final LocationListener locationListener = location -> {
        if (location != null) {
            System.out.println("MainActivity :: Location Listener :: longitude " + location.getLongitude());
            System.out.println("MainActivity :: Location Listener :: latitude " + location.getLatitude());
        }
    };

    private final String[] locationPermissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCellInfoBinding.inflate(LayoutInflater.from(CellInfoActivity.this));
        setContentView(binding.getRoot());
    }


    @Override
    protected void onStart() {
        super.onStart();
        final boolean locationPermissionCondition = ActivityCompat.checkSelfPermission(CellInfoActivity.this, locationPermissions[0]) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(CellInfoActivity.this, locationPermissions[1]) == PackageManager.PERMISSION_GRANTED;

        if (locationPermissionCondition) {
            initiateServices();
        } else {
            ActivityCompat.requestPermissions(this, locationPermissions, LOCATION_PERMISSION_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            initiateServices();
        }
    }

    @SuppressLint("MissingPermission")
    private void initiateServices() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = LocationManager.GPS_PROVIDER;
        locationManager.requestLocationUpdates(provider, 1000, 1, locationListener);
        telePhonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        showCellInfo(telePhonyManager);
    }

    private void showCellInfo(TelephonyManager telephonyManager) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        List<String> cellInformationList = new ArrayList<>();
        List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
        if (cellInfoList != null) {
            if (!cellInfoList.isEmpty()) {
                for (CellInfo cellInfo : cellInfoList) {
                    StringBuilder cellInfoValue = new StringBuilder("Some Error Occurred");
                    if (cellInfo instanceof CellInfoCdma) {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoCdma.getCellSignalStrength() + "\n\n"
                                    + "Connection Status : " + cellInfoCdma.getCellConnectionStatus() + "\n\n"
                                    + "Longitude : " + cellInfoCdma.getCellIdentity().getLongitude() + "\n\n"
                                    + "Latitude : " + cellInfoCdma.getCellIdentity().getLatitude() + "\n\n"
                                    + "Base Station Id : " + cellInfoCdma.getCellIdentity().getBasestationId() + "\n\n"
                                    + "Network Id : " + cellInfoCdma.getCellIdentity().getNetworkId() + "\n\n"
                                    + "System Id : " + cellInfoCdma.getCellIdentity().getSystemId() + "\n"
                            );
                        } else {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoCdma.getCellSignalStrength() + "\n\n"
                                    + "Longitude : " + cellInfoCdma.getCellIdentity().getLongitude() + "\n\n"
                                    + "Latitude : " + cellInfoCdma.getCellIdentity().getLatitude() + "\n\n"
                                    + "Base Station Id : " + cellInfoCdma.getCellIdentity().getBasestationId() + "\n\n"
                                    + "Network Id : " + cellInfoCdma.getCellIdentity().getNetworkId() + "\n\n"
                                    + "System Id : " + cellInfoCdma.getCellIdentity().getSystemId() + "\n"
                            );
                        }
                    } else if (cellInfo instanceof CellInfoGsm) {
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoGsm.getCellSignalStrength() + "\n\n"
                                    + "Connection Status : " + cellInfoGsm.getCellConnectionStatus() + "\n\n"
                                    + "Mobile Network Operator Name : " + cellInfoGsm.getCellIdentity().getOperatorAlphaShort() + "\n\n"
                                    + "Mobile Network Operator : " + cellInfoGsm.getCellIdentity().getMobileNetworkOperator() + "\n\n"
                                    + "Mobile Country Code : " + cellInfoGsm.getCellIdentity().getMccString() + "\n\n"
                                    + "Mobile Network Code: " + cellInfoGsm.getCellIdentity().getMncString() + "\n\n"
                                    + "Location Area Code : " + cellInfoGsm.getCellIdentity().getLac() + "\n\n"
                                    + "GSM Cell Identity : " + cellInfoGsm.getCellIdentity().getCid() + "\n\n"
                                    + "Base Station Identity Code : " + cellInfoGsm.getCellIdentity().getBsic() + "\n\n"
                                    + "Absolute RF Channel Number : " + cellInfoGsm.getCellIdentity().getArfcn() +
                                    "\n"
                            );
                        } else {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoGsm.getCellSignalStrength() + "\n\n"
                                    + "Location Area Code : " + cellInfoGsm.getCellIdentity().getLac() + "\n\n"
                                    + "GSM Cell Identity : " + cellInfoGsm.getCellIdentity().getCid() + "\n"
                            );
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            for (String info : cellInfoGsm.getCellIdentity().getAdditionalPlmns()) {
                                cellInfoValue.append("Additional PLMN IDs : ").append(info);
                            }
                        }
                    } else if (cellInfo instanceof CellInfoWcdma) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoWcdma.getCellSignalStrength() + "\n\n"
                                    + "Connection Status : " + cellInfoWcdma.getCellConnectionStatus() + "\n\n"
                                    + "Mobile Network Operator Name : " + cellInfoWcdma.getCellIdentity().getOperatorAlphaShort() + "\n\n"
                                    + "Mobile Network Operator : " + cellInfoWcdma.getCellIdentity().getMobileNetworkOperator() + "\n\n"
                                    + "Mobile Country Code : " + cellInfoWcdma.getCellIdentity().getMccString() + "\n\n"
                                    + "Mobile Network Code: " + cellInfoWcdma.getCellIdentity().getMncString() + "\n\n"
                                    + "Location Area Code : " + cellInfoWcdma.getCellIdentity().getLac() + "\n\n"
                                    + "GSM Cell Identity : " + cellInfoWcdma.getCellIdentity().getCid() + "\n\n"
                                    + "UMTS Absolute RF Channel Number : " + cellInfoWcdma.getCellIdentity().getUarfcn() + "\n\n"
                                    + "Primary Scrambling Code : " + cellInfoWcdma.getCellIdentity().getPsc() +
                                    "\n"
                            );
                        } else {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoWcdma.getCellSignalStrength() + "\n\n"
                                    + "Location Area Code : " + cellInfoWcdma.getCellIdentity().getLac() + "\n\n"
                                    + "GSM Cell Identity : " + cellInfoWcdma.getCellIdentity().getCid() + "\n\n"
                                    + "Primary Scrambling Code : " + cellInfoWcdma.getCellIdentity().getPsc() +
                                    "\n"
                            );
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            for (String info : cellInfoWcdma.getCellIdentity().getAdditionalPlmns()) {
                                cellInfoValue.append("Additional PLMN IDs : ").append(info);
                            }
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoLte.getCellSignalStrength() + "\n\n"
                                    + "Connection Status : " + cellInfoLte.getCellConnectionStatus() + "\n\n"
                                    + "Mobile Network Operator Name : " + cellInfoLte.getCellIdentity().getOperatorAlphaShort() + "\n\n"
                                    + "Mobile Network Operator : " + cellInfoLte.getCellIdentity().getMobileNetworkOperator() + "\n\n"
                                    + "Mobile Country Code : " + cellInfoLte.getCellIdentity().getMccString() + "\n\n"
                                    + "Mobile Network Code: " + cellInfoLte.getCellIdentity().getMncString() + "\n\n"
                                    + "Bandwidth : " + cellInfoLte.getCellIdentity().getBandwidth() + "\n\n"
                                    + "Cell Identity : " + cellInfoLte.getCellIdentity().getCi() + "\n\n"
                                    + "Absolute RF Channel Number : " + cellInfoLte.getCellIdentity().getEarfcn() + "\n\n"
                                    + "Primary Scrambling Code : " + cellInfoLte.getCellIdentity().getPci() + "\n\n"
                                    + "Tracking Area Code : " + cellInfoLte.getCellIdentity().getTac() +
                                    "\n"
                            );
                        } else {
                            cellInfoValue = new StringBuilder("Signal Strength : " + cellInfoLte.getCellSignalStrength() + "\n\n"
                                    + "Cell Identity : " + cellInfoLte.getCellIdentity().getCi() + "\n\n"
                                    + "Primary Scrambling Code : " + cellInfoLte.getCellIdentity().getPci() + "\n\n"
                                    + "Tracking Area Code : " + cellInfoLte.getCellIdentity().getTac() +
                                    "\n"
                            );
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            for (String info : cellInfoLte.getCellIdentity().getAdditionalPlmns()) {
                                cellInfoValue.append("Additional PLMN IDs : ").append(info);
                            }
                        }
                    }
                    cellInformationList.add(cellInfoValue.toString());
                }
                Observable.fromArray(cellInformationList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onNext -> {
                            binding.rvCellInfo.setLayoutManager(new LinearLayoutManager(CellInfoActivity.this));
                            binding.rvCellInfo.setAdapter(new CellInfoAdapter(onNext));
                        });
            } else {
                Snackbar.make(binding.coordinatorCellInfo, "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }
}