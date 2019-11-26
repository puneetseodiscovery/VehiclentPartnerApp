package vehiclentpartner.com.vehiclent.upcommingDetails;

public interface IMUpCommingDetailsActivity {

    void onAcceptRestCall(String ids_array, String user_id, String service_id, String Message, String Latitude, String Longitude, String Partner_Id);

    void onRejectRestCall(String id,String service_id,String user_query,String latitude,String longitude);
}
