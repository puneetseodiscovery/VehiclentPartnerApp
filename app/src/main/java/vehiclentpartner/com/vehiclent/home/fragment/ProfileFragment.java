package vehiclentpartner.com.vehiclent.home.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.presenter.IPProfileFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PProfileFragment;
import vehiclentpartner.com.vehiclent.login.LoginActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.util.CommonMethods;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment implements View.OnClickListener, IProfileFragment {

    View view;

    @BindView(R.id.tv_userprofile)
    TextView tv_userprofile;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.mobile)
    TextView mobile;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.gender)
    TextView gender;

    @BindView(R.id.tv_reviewcount)
    TextView tv_reviewcount;

    @BindView(R.id.edit_user_first_name)
    EditText edit_user_first_name;

    @BindView(R.id.edit_user_last_name)
    EditText edit_user_last_name;

    @BindView(R.id.edit_user_email)
    EditText edit_user_email;

    @BindView(R.id.edit_user_mobile)
    EditText edit_user_mobile;

    @BindView(R.id.tv_user_address)
    TextView tv_user_address;

    @BindView(R.id.tv_user_gender)
    TextView tv_user_gender;

    @BindView(R.id.img_edit)
    ImageView img_edit;

    @BindView(R.id.img_done)
    ImageView img_done;

    @BindView(R.id.img_userprofile)
    CircleImageView img_userprofile;

    @BindView(R.id.img_opncamera)
    RelativeLayout img_opncamera;

    @BindView(R.id.rating_bar)
    RatingBar rating_bar;

    Context context;
    IPProfileFragment ipProfileFragment;
    ProgressDialog progressDialog;
    SavePref savePref;
    String getUser_Id;
    TextView upload_image, open_camera, cancel;
    private static final int SELECT_FILE = 200;
    Uri selectedImageUri;
    Bitmap bitmap;
    private String image_path = "";
    String select_latitude = "", select_longitude = "",getRatingValue="";
    File file;
    private int MEDIA_TYPE_CAPTURE = 2;
    private final int PLACE_PICKER_REQUEST = 1;
    MultipartBody.Part body;
    RequestBody imgReq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);
        context = this.getContext();
        ipProfileFragment = new PProfileFragment(this);
        savePref = new SavePref(context);
        Initialization();
        return view;
    }

    private void Initialization() {

        getUser_Id = savePref.getid();

        progressDialog = Utility.showLoader(getActivity());
        if (Utility.isNetworkConnected(getActivity())) {
            ipProfileFragment.doGetUserProfile(getUser_Id);

        } else {
            Toast.makeText(getActivity(), "Check your internet connection.", Toast.LENGTH_SHORT).show();

        }

        tv_userprofile.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        tv_username.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        mobile.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        address.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_first_name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_last_name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_mobile.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_user_address.setTypeface(Utility.typeFaceForRegulerText(getActivity()));

        edit_user_first_name.setEnabled(false);
        edit_user_last_name.setEnabled(false);
        edit_user_email.setEnabled(false);
        edit_user_mobile.setEnabled(false);
        tv_user_address.setEnabled(false);
        tv_user_gender.setEnabled(false);

        rating_bar.setClickable(false);
        rating_bar.setFocusableInTouchMode(false);
        rating_bar.setFocusable(false);

        EventListner();

    }

    private void EventListner() {

        img_edit.setOnClickListener(this);
        img_done.setOnClickListener(this);
        img_opncamera.setOnClickListener(this);
        tv_user_gender.setOnClickListener(this);
        tv_user_address.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tv_user_address.setEnabled(true);


        if (requestCode == MEDIA_TYPE_CAPTURE && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img_userprofile.setImageBitmap(photo);
            body = sendImageFileToserver(photo);
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(context, data);
                Log.e("Tag", "Place: "
                        + place.getAddress()
                        + place.getPhoneNumber()
                        + place.getLatLng().latitude);
                select_latitude = String.valueOf(place.getLatLng().latitude);
                select_longitude = String.valueOf(place.getLatLng().longitude);

                tv_user_address.setText(place.getAddress().toString());
                //Completed_Address(curent_lat, current_lang);
            }
        } else if (requestCode == 2) {
            if (data != null) {
            }
        } else if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_FILE) {
                if (data != null) {
                    selectedImageUri = data.getData();
                    setProfileImage(selectedImageUri);
                }
            } else if (requestCode == MEDIA_TYPE_CAPTURE) {
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");

                    img_userprofile.setImageBitmap(photo);
                    body = sendImageFileToserver(photo);

                   /* Uri uri = Uri.fromFile(file);
                    image_path = Utility.getPath(context, uri);
                    bitmap(uri);
                    Glide.with(getContext()).load(image_path).into(img_userprofile);*/

                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(context, "Cancelled",
                            Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void bitmap(Uri resultUri) {
       /* bitmap = null;

        try {
            bitmap = (MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), resultUri));
            image = sendImageFileToserver(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        bitmap = null;

        try {
            bitmap = (MediaStore.Images.Media.getBitmap(context.getContentResolver(), resultUri));
            body = sendImageFileToserver(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    private void setProfileImage(Uri resultUri) {

        image_path = CommonMethods.getPath(context, resultUri);
        bitmap(resultUri);

        body = sendImageFileToserver(bitmap);
        Glide.with(context)
                .load(resultUri).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_userprofile);

    }

    /*public MultipartBody.Part sendImageFileToserver(Bitmap bitmap) throws IOException {
        File filesDir = getActivity().getFilesDir();
        File file = new File(filesDir, "image" + System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        imgReq = RequestBody.create(MediaType.parse("text/plain"), "image");

        return fileToUpload;

    }*/


    private MultipartBody.Part sendImageFileToserver(Bitmap bitmap) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "image" + System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        imgReq = RequestBody.create(MediaType.parse("text/plain"), "image");

        return fileToUpload;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_edit:

                img_edit.setVisibility(View.GONE);
                img_done.setVisibility(View.VISIBLE);
                img_opncamera.setVisibility(View.VISIBLE);

                edit_user_first_name.setEnabled(true);
                edit_user_last_name.setEnabled(true);
                edit_user_email.setEnabled(false);
                edit_user_mobile.setEnabled(true);
                tv_user_address.setEnabled(true);
                tv_user_gender.setEnabled(true);

                break;

            case R.id.img_done:

                img_done.setVisibility(View.GONE);
                img_opncamera.setVisibility(View.GONE);
                img_edit.setVisibility(View.VISIBLE);

                edit_user_first_name.setEnabled(false);
                edit_user_last_name.setEnabled(false);
                edit_user_email.setEnabled(false);
                edit_user_mobile.setEnabled(false);
                tv_user_address.setEnabled(false);
                tv_user_gender.setEnabled(false);

                if (Utility.isNetworkConnected(getActivity())) {

                    if (edit_user_first_name.getText().toString().isEmpty()) {
                        edit_user_first_name.setError("Please enter first name");

                    } else if (edit_user_last_name.getText().toString().isEmpty()) {
                        edit_user_last_name.setError("Please enter last name");

                    } else if (tv_user_gender.getText().toString().trim().isEmpty()) {

                        tv_user_gender.setError("Please enter gender");

                    } else if (edit_user_mobile.getText().toString().trim().isEmpty()) {

                        edit_user_mobile.setError("Please enter phone number");

                    } else if (!CommonMethods.isValidMobile(edit_user_mobile.getText().toString())) {

                        edit_user_mobile.setError("Please enter valid phone number");

                    } else if (tv_user_address.getText().toString().trim().isEmpty()) {

                        tv_user_address.setError("Please enter address");

                    } else {

                        progressDialog = Utility.showLoader(getActivity());

                        ipProfileFragment.doUpdateUserProfile(getUser_Id, edit_user_first_name.getText().toString().trim(), edit_user_last_name.getText().toString().trim(), tv_user_gender.getText().toString().trim(), tv_user_address.getText().toString().trim(),
                                edit_user_mobile.getText().toString().trim(), select_longitude, select_latitude, body, imgReq);
                    }


                } else {
                    Toast.makeText(getActivity(), "Check your internet connection.", Toast.LENGTH_SHORT).show();

                }
                break;


            case R.id.img_opncamera:

                OpenMediaAlertDialogBox();

                break;
            case R.id.tv_user_gender:

                OpenGenderAlertBox();

                break;

            case R.id.tv_user_address:

                if (Utility.isNetworkConnected(context)) {
                    locationPicker();
                    tv_user_address.setEnabled(false);
                } else {
                    networkErroDialog();
                }

                break;
        }
    }

    private void networkErroDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("<font color='#0cb059'>Oops!</font>");
        builder.setMessage("Check your internet connection !!!")
                .setCancelable(false)
                .setPositiveButton("<font color='#0cb059'>OK</font>", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void locationPicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {

            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    private void OpenGenderAlertBox() {
        CharSequence[] items = {"Male", "Female"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //  builder.setTitle("Please select gender");
        builder.setTitle(Html.fromHtml("<font color='#0cb059'>Please select your gender!</font>"));
        builder.setPositiveButton(Html.fromHtml("<font color='#0cb059'>OK</font>"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (item == 0) {
                    tv_user_gender.setText("Male");
                } else if (item == 1) {
                    tv_user_gender.setText("Female");
                }
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    private void OpenMediaAlertDialogBox() {

        final Dialog dialog;
        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_open_camera_galary);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        upload_image = (TextView) dialog.findViewById(R.id.upload_image);
        open_camera = (TextView) dialog.findViewById(R.id.open_camera);
        cancel = (TextView) dialog.findViewById(R.id.cancel);

        open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, MEDIA_TYPE_CAPTURE);
              //  cameraIntent(); // cameraIntent() method call on  open_camera click
                dialog.dismiss();
            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gallery intent to open the gallery
                Intent profile = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(profile, SELECT_FILE);
                dialog.dismiss();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // Camera Intent
    private void cameraIntent() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() +
                File.separator + "image.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, MEDIA_TYPE_CAPTURE);

    }

    @Override
    public void onGetProfileFromPresenter(int statusValue) {

    }

    @Override
    public void onGetProfileSuccessFromPresenter(GetUserProfileResponseModel getUserProfileResponseModel) {
        progressDialog.dismiss();

        if (getUserProfileResponseModel.getData().get(0) != null) {
            edit_user_first_name.setText(getUserProfileResponseModel.getData().get(0).getFirstName());
        } else {
            edit_user_first_name.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            edit_user_last_name.setText(getUserProfileResponseModel.getData().get(0).getLastName());
        } else {
            edit_user_last_name.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            edit_user_email.setText(getUserProfileResponseModel.getData().get(0).getEmail());
        } else {
            edit_user_email.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            edit_user_mobile.setText(getUserProfileResponseModel.getData().get(0).getPhoneNumber());
        } else {
            edit_user_mobile.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            tv_user_address.setText(getUserProfileResponseModel.getData().get(0).getAddress());
        } else {
            tv_user_address.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            tv_username.setText(getUserProfileResponseModel.getData().get(0).getFirstName() + " " + getUserProfileResponseModel.getData().get(0).getLastName());
        } else {
            tv_username.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            tv_user_gender.setText(getUserProfileResponseModel.getData().get(0).getGender());
        } else {
            tv_user_gender.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            if (getUserProfileResponseModel.getData().get(0).getTotalReviews().isEmpty()||getUserProfileResponseModel.getData().get(0).getTotalReviews().isEmpty()){
                tv_reviewcount.setText("("+"0"+" "+"Reviews"+")");
            }else {
                tv_reviewcount.setText("( " + getUserProfileResponseModel.getData().get(0).getTotalReviews() + " " + "Reviews" + " )");

            }
        } else {
            tv_reviewcount.setText("0");
        }

        if (getUserProfileResponseModel.getData().get(0).getRating().isEmpty() || getUserProfileResponseModel.getData().get(0).getRating().equals("")) {
            rating_bar.setRating(0.0f);
        } else {
           /* getRatingValue=getUserProfileResponseModel.getData().get(0).getTotalReviews();
            float ratingvaule=Float.parseFloat(getRatingValue);*/
            rating_bar.setRating(Float.parseFloat(getUserProfileResponseModel.getData().get(0).getRating()));
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            Glide.with(context).load(getUserProfileResponseModel.getData().get(0)
                    .getYourProfile())
                    .error(R.drawable.no_image).dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_userprofile);

        } else {
            Glide.with(context).load(R.drawable.no_image).centerCrop()
                    .placeholder(R.drawable.no_image)
                    .into(img_userprofile);
        }

    }

    @Override
    public void onGetProfileSFailedFromPresenter(String message) {

        progressDialog.dismiss();
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpdateProfileFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onUpdateUserProfileFromPresenter(UpdateUserProfileResponseModel updateUserProfileResponseModel) {

        progressDialog.dismiss();
        Toast.makeText(context, "Profile update Successfully!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpdateProfileSFailedFromPresenter(String message) {
        progressDialog.dismiss();

    }
}
