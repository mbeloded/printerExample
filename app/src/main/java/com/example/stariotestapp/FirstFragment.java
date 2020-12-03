package com.example.stariotestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    protected static final int PRINTER_SET_REQUEST_CODE = 1;

    TextView myAwesomeTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Intent intent = new Intent(getActivity(), CommonActivity.class);

        myAwesomeTextView = (TextView)getView().findViewById(R.id.textview_first);

        updateList();

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(CommonActivity.BUNDLE_KEY_ACTIVITY_LAYOUT, R.layout.activity_printer_search);
                intent.putExtra(CommonActivity.BUNDLE_KEY_TOOLBAR_TITLE, "Search Port");
                intent.putExtra(CommonActivity.BUNDLE_KEY_SHOW_HOME_BUTTON, true);
                intent.putExtra(CommonActivity.BUNDLE_KEY_SHOW_RELOAD_BUTTON, true);
                intent.putExtra(CommonActivity.BUNDLE_KEY_PRINTER_SETTING_INDEX, 0);    // Index of the backup printer

                startActivityForResult(intent, PRINTER_SET_REQUEST_CODE);
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PRINTER_SET_REQUEST_CODE) {
            updateList();
        }
    }

    private void updateList() {
//        adapter.clear();

        PrinterSettingManager settingManager = new PrinterSettingManager(getActivity());
        PrinterSettings       settings       = settingManager.getPrinterSettings();

        boolean isDeviceSelected     = false;
        int     modelIndex           = ModelCapability.NONE;
        String  modelName            = "";
        boolean isBluetoothInterface = false;
        boolean isUsbInterface       = false;

        if (settings != null) {
            isDeviceSelected     = true;
            modelIndex           = settings.getModelIndex();
            modelName            = settings.getModelName();
            isBluetoothInterface = settings.getPortName().toUpperCase().startsWith("BT:");
            isUsbInterface       = settings.getPortName().toUpperCase().startsWith("USB:");
            myAwesomeTextView.setText("device is connected");
        } else {
            myAwesomeTextView.setText("device is not connected");
        }

//        addTitle("Destination Device");
//        addPrinterInfo(settingManager.getPrinterSettingsList());
//
//        addTitle("Printer");
//        addMenu("Sample",                     isDeviceSelected);
//        addMenu("Black Mark Sample",          isDeviceSelected && ModelCapability.canUseBlackMark(modelIndex));
//        addMenu("Black Mark Sample (Paste)",  isDeviceSelected && ModelCapability.canUseBlackMark(modelIndex));
//        addMenu("Page Mode Sample",           isDeviceSelected && ModelCapability.canUsePageMode(modelIndex));
//        addMenu("Presenter Sample",           isDeviceSelected && ModelCapability.canUsePresenter(modelIndex));
//        addMenu("LED Sample",                 isDeviceSelected && ModelCapability.canUseLed(modelIndex));
//        addMenu("Print Re-Direction Sample",  isDeviceSelected);
//        addMenu("Hold Print Sample",          isDeviceSelected && ModelCapability.canUsePaperPresentStatus(modelIndex));
//        addMenu("AutoSwitch Interface Sample",      isDeviceSelected && ModelCapability.canUseAutoSwitchInterface(modelIndex));
//
//        addTitle("Peripheral");
//        addMenu("Cash Drawer Sample",         isDeviceSelected && ModelCapability.canUseCashDrawer(modelIndex));
//        addMenu("Barcode Reader Sample",      isDeviceSelected && ModelCapability.canUseBarcodeReader(modelIndex));
//        addMenu("Display Sample",             isDeviceSelected && ModelCapability.canUseCustomerDisplay(modelIndex, modelName));
//        addMenu("Melody Speaker Sample",      isDeviceSelected && ModelCapability.canUseMelodySpeaker(modelIndex));
//
//        addTitle("Combination");
//        addMenu("StarIoExtManager Sample",    isDeviceSelected && ModelCapability.canUseBarcodeReader(modelIndex));
//
//        addTitle("API");
//        addMenu("Sample",                     isDeviceSelected);
//
//        addTitle("Star Micronics Cloud");
//        addMenu("Sample",                     isDeviceSelected && ModelCapability.canUseAllReceipt(modelIndex));
//
//        addTitle("Device Status");
//        addMenu("Sample",                     isDeviceSelected);
//        addMenu("Product Serial Number",      isDeviceSelected && ModelCapability.canGetProductSerialNumber(modelIndex, modelName, isBluetoothInterface));
//
//        addTitle("Interface");
//        addMenu("Bluetooth Setting",          isDeviceSelected && isBluetoothInterface);
//        addMenu("USB Serial Number",          isDeviceSelected && ModelCapability.settableUsbSerialNumberLength(modelIndex, modelName, isUsbInterface) != 0);
//
//        addTitle("Appendix");
//        addMenu("Library Version");
    }
}