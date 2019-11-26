package vehiclentpartner.com.vehiclent.chatAcitvity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.util.Constant;
import vehiclentpartner.com.vehiclent.util.SavePref;

public class ChatActivity extends BaseClass implements View.OnClickListener {

    ChatActivity context;

    @BindView(R.id.img_back_chat)
    ImageView img_back_chat;

    @BindView(R.id.img_messageSend)
    ImageView img_messageSend;

    @BindView(R.id.edit_messageType)
    EditText edit_messageType;

    @BindView(R.id.layout2)
    RelativeLayout layout2;

    @BindView(R.id.layout1)
    LinearLayout layout1;

    @BindView(R.id.scroll_view)
    ScrollView scroll_view;

    Firebase referencefirebase1, referencefirebase2;

    String user_name = "",firebaseChatWith="",partner_id="";
    SavePref savePref;

    String message="",userName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        context = ChatActivity.this;
        savePref=new SavePref(this);
        Firebase.setAndroidContext(this);

        Initialization();
        EventListners();

    }

    private void Initialization() {


        user_name = getIntent().getStringExtra("user_name");
        firebaseChatWith = getIntent().getStringExtra("firebaseChatWith");
        partner_id = getIntent().getStringExtra("partner_id");
        firebaseUsername = getIntent().getStringExtra("partner_id");

        referencefirebase1 = new Firebase("https://vehiclent.firebaseio.com/messages/" +  firebaseUsername + "_" + firebaseChatWith);
        referencefirebase2 = new Firebase("https://vehiclent.firebaseio.com/messages/" + firebaseChatWith + "_" + firebaseUsername);

    }

    private void EventListners() {

        img_back_chat.setOnClickListener(this);
        img_messageSend.setOnClickListener(this);

        referencefirebase1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();


                if (userName.equals(firebaseUsername)) {
                    addMessageBox("You\n" + message, 1);
                } else {
                   // addMessageBox(firebaseChatWith + ":-\n" + message, 2);
                    addMessageBox(user_name+ "\n" + message, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, int type) {
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        /*if (type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_in);
        } else {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_out);
        }*/

        if (type == 1) {

            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_out);


        } else {

            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_in);
        }
        textView.setLayoutParams(lp2);
        layout1.addView(textView);
        scroll_view.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_chat:
                finish();
                break;
            case R.id.img_messageSend:
                String messageText = edit_messageType.getText().toString();

                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", firebaseUsername);
                    referencefirebase1.push().setValue(map);
                    referencefirebase2.push().setValue(map);
                    edit_messageType.setText("");
                }
                break;
        }
    }
}
