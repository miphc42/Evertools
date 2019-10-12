package everything.philipchoi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCode extends Fragment {
    private EditText etInput;
    private Button btnCreateQR, btnScanQR;
    private ImageView imageView;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        (getActivity()).setTitle("QR Code");
        view = inflater.inflate(R.layout.qrgenerate, container, false);
        btnCreateQR = view.findViewById(R.id.btnCreate);
        btnScanQR = view.findViewById(R.id.cameraQR);
        imageView = view.findViewById(R.id.ImageView);
        etInput = view.findViewById(R.id.etInput);
        btnCreateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= etInput.getText().toString();
                if(!text.equals("")){
                    MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix=multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScanQR.class));
            }
        });
        return view;
    }
}
