package vehiclentpartner.com.vehiclent.home.fragment;

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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.presenter.IPProfileFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PProfileFragment;
import vehiclentpartner.com.vehiclent.login.LoginActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;
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

    @BindView(R.id.edit_user_name)
    EditText edit_user_name;

    @BindView(R.id.edit_user_email)
    EditText edit_user_email;

    @BindView(R.id.edit_user_mobile)
    EditText edit_user_mobile;

    @BindView(R.id.edit_user_address)
    EditText edit_user_address;

    @BindView(R.id.edit_user_gender)
    EditText edit_user_gender;

    @BindView(R.id.img_edit)
    ImageView img_edit;

    @BindView(R.id.img_done)
    ImageView img_done;

    @BindView(R.id.img_userprofile)
    ImageView img_userprofile;

    @BindView(R.id.img_opncamera)
    ImageView img_opncamera;

    Context context;
    IPProfileFragment ipProfileFragment;
    ProgressDialog progressDialog;
    SavePref savePref;
    String getUser_Id;
    TextView upload_image, open_camera, cancel;
    private static final int SELECT_FILE = 200;
    Uri selectedImageUri;
    MediaType MEDIA_TYPE;
    Bitmap bitmap;
    private String image_path = "";
    String getimage = "";
    private Uri fileUri;
    File file;
    private int MEDIA_TYPE_CAPTURE = 2;
    String selectedImagePath;

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
        edit_user_name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_mobile.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_address.setTypeface(Utility.typeFaceForRegulerText(getActivity()));

        edit_user_name.setEnabled(false);
        edit_user_email.setEnabled(false);
        edit_user_mobile.setEnabled(false);
        edit_user_address.setEnabled(false);
        edit_user_gender.setEnabled(false);


        EventListner();

    }

    private void EventListner() {

        img_edit.setOnClickListener(this);
        img_done.setOnClickListener(this);
        img_opncamera.setOnClickListener(this);
        edit_user_gender.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_FILE) {
                if (data != null) {
                    selectedImageUri = data.getData();
                    setProfileImage(selectedImageUri);
                }
            } else if (requestCode == MEDIA_TYPE_CAPTURE) {
                if (resultCode == RESULT_OK) {
                    Uri uri = Uri.fromFile(file);
                    image_path = Utility.getPath(context, uri);
                    bitmap(uri);
                    Glide.with(getContext()).load(image_path).into(img_userprofile);

                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(context, "Cancelled",
                            Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void bitmap(Uri resultUri) {
        bitmap = null;

        try {
            bitmap = (MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), resultUri));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    private void setProfileImage(Uri resultUri) {

        image_path = Utility.getPath(getContext(), resultUri);
        bitmap(resultUri);
       /* Glide.with(context)
                .load(resultUri).dontAnimate().diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(img_userprofile);
*/
        Glide.with(context).load(resultUri)
                .into(img_userprofile);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_edit:

                img_edit.setVisibility(View.GONE);
                img_done.setVisibility(View.VISIBLE);
                img_opncamera.setVisibility(View.VISIBLE);


                edit_user_name.setEnabled(false);
                edit_user_email.setEnabled(false);
                edit_user_mobile.setEnabled(true);
                edit_user_address.setEnabled(true);
                edit_user_gender.setEnabled(true);


                break;
            case R.id.img_done:

                img_done.setVisibility(View.GONE);
                img_opncamera.setVisibility(View.GONE);
                img_edit.setVisibility(View.VISIBLE);

                edit_user_name.setEnabled(false);
                edit_user_email.setEnabled(false);
                edit_user_mobile.setEnabled(false);
                edit_user_address.setEnabled(false);
                edit_user_gender.setEnabled(false);

                if (Utility.isNetworkConnected(getActivity())) {
                    /*if (edit_user_mobile.getText().toString().length() > 0 && edit_user_address.getText().toString().length() > 0) {
                        progressDialog = Utility.showLoader(getActivity());
                        ipProfileFragment.doUpdateUserProfile(getUser_Id, "", "",  edit_user_gender.getText().toString().trim(), edit_user_address.getText().toString().trim(),
                                edit_user_mobile.getText().toString().trim());
                    } else {
                        edit_user_mobile.setError("Please enter phone number");
                        edit_user_address.setError("Please enter address");
                    }*/

                    if (edit_user_mobile.getText().toString().trim().isEmpty()) {
                        edit_user_mobile.setError("Please enter gender");
                    } else if (edit_user_gender.getText().toString().trim().isEmpty()) {
                        edit_user_gender.setError("Please enter gender");
                    } else if (edit_user_address.getText().toString().trim().isEmpty()) {
                        edit_user_address.setError("Please enter address");
                    } else {
                        progressDialog = Utility.showLoader(getActivity());
                        ipProfileFragment.doUpdateUserProfile(getUser_Id, "", "", edit_user_gender.getText().toString().trim(), edit_user_address.getText().toString().trim(),
                                edit_user_mobile.getText().toString().trim());
                    }


                } else {
                    Toast.makeText(getActivity(), "Check your internet connection.", Toast.LENGTH_SHORT).show();

                }
                break;


            case R.id.img_opncamera:

                OpenMediaAlertDialogBox();

                break;
            case R.id.edit_user_gender:

                OpenGenderAlertBox();

                break;
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
                    edit_user_gender.setText("Male");
                } else if (item == 1) {
                    edit_user_gender.setText("Female");
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

                cameraIntent(); // cameraIntent() method call on  open_camera click
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
        file = new File(Environment.getExternalStorageDirectory() +
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
            edit_user_name.setText(getUserProfileResponseModel.getData().get(0).getUserName());
        } else {
            edit_user_name.setText("");
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
            edit_user_address.setText(getUserProfileResponseModel.getData().get(0).getAddress());
        } else {
            edit_user_address.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            tv_username.setText(getUserProfileResponseModel.getData().get(0).getUserName());
        } else {
            tv_username.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            edit_user_gender.setText(getUserProfileResponseModel.getData().get(0).getGender());
        } else {
            edit_user_gender.setText("");
        }

        if (getUserProfileResponseModel.getData().get(0) != null) {
            Glide.with(context).load(getUserProfileResponseModel.getData().get(0)
                    .getYourProfile()).centerCrop()
                    .placeholder(R.drawable.user_profile)
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

    }

    @Override
    public void onUpdateUserProfileFromPresenter(UpdateUserProfileResponseModel updateUserProfileResponseModel) {

        progressDialog.dismiss();
        Toast.makeText(context, "Profile update Successfully!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpdateProfileSFailedFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();


    }


}
