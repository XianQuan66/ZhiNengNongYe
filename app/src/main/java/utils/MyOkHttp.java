package utils;

import android.media.MediaPlayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkHttp {

    private static final String BASE_URL = ":8890/type/jason/action/";

    private String getAbsoluteUrl(MyApp myApp, String relativeUrl) {
        return "http://" + myApp.getIp() + BASE_URL + relativeUrl;
    }

    /**
     * 连接沙盘
     *
     * @param myApp
     * @param value
     * @param action
     * @return
     * @throws IOException
     */
    public String getData(MyApp myApp, String value, String action) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("username", value).build();
        Request request = new Request.Builder().url(getAbsoluteUrl(myApp, action)).post(body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        return data;
    }

    /**
     * 获取沙盘传感器最大值和最小值
     *
     * @param myApp
     * @throws Exception
     */
    public void getConfig(MyApp myApp) throws Exception {
        String data = getData(myApp, "", "getConfig");
        JSONObject jsonObject = new JSONObject(data);
        myApp.setMinCo2(String.valueOf(jsonObject.getInt("minCo2")));//co2
        myApp.setMaxCo2(String.valueOf(jsonObject.getInt("maxCo2")));
        myApp.setMinLight(String.valueOf(jsonObject.getInt("minLight")));//灯光强度
        myApp.setMaxLight(String.valueOf(jsonObject.getInt("maxLight")));
        myApp.setMinSoilHumidity(String.valueOf(jsonObject.getInt("minSoilHumidity")));//土壤湿度
        myApp.setMaxSoilHumidity(String.valueOf(jsonObject.getInt("maxSoilHumidity")));
        myApp.setMinAirHumidity(String.valueOf(jsonObject.getInt("minAirHumidity")));//空气湿度
        myApp.setMaxAirHumidity(String.valueOf(jsonObject.getInt("maxAirHumidity")));
        myApp.setMinAirTemperature(String.valueOf(jsonObject.getInt("minAirTemperature")));//空气温度
        myApp.setMaxAirTemperature(String.valueOf(jsonObject.getInt("maxAirTemperature")));
        myApp.setMinSoilTemperature(String.valueOf(jsonObject.getInt("minSoilTemperature")));//土壤温度
        myApp.setMaxSoilTemperature(String.valueOf(jsonObject.getInt("maxSoilTemperature")));
    }

    /**
     * 获取沙盘传感器的值
     */
    public String[] getSensor(MyApp myApp) throws IOException, JSONException {
        String data = getData(myApp, "admin", "getSensor");
        JSONObject jsonObject = new JSONObject(data);
        String co2 = String.valueOf(jsonObject.getInt("co2"));  //co2
        String airHumidity = String.valueOf(jsonObject.getInt("airHumidity"));  //空气湿度
        String airTemperature = String.valueOf(jsonObject.getInt("airTemperature"));  //空气温度
        String soilTemperature = String.valueOf(jsonObject.getInt("soilTemperature"));//土壤温度
        String soilHumidity = String.valueOf(jsonObject.getInt("soilHumidity"));//土壤湿度
        String light = String.valueOf(jsonObject.getInt("light"));//灯光强度

        String [] dataAarr = {co2,light,soilTemperature,soilHumidity,airTemperature,airHumidity};
        return dataAarr;
    }

    /**
     * 获取控制器状态的协议
     */
    public int[] getContorllerStatus(MyApp myApp) throws IOException, JSONException {
        String data = getData(myApp, "", "getContorllerStatus");
        JSONObject jsonObject = new JSONObject(data);
        int waterPump = jsonObject.getInt("WaterPump"); //水泵控制器开关
        int Blower = jsonObject.getInt("Blower"); //风扇控制器开关
        int Roadlamp = jsonObject.getInt("Roadlamp"); //灯光控制器开关
        int Buzzer = jsonObject.getInt("Buzzer"); //蜂鸣器控制开关

        int[] dataArr = {waterPump,Blower,Roadlamp,Buzzer};
        return dataArr;
    }

    /**
     * 控制器协议
     * @param myApp
     * @param controlName
     * @param controlStatus
     * @throws JSONException
     * @throws IOException
     */
    public void control(MyApp myApp,String controlName,int controlStatus) throws JSONException, IOException {
        String url = getAbsoluteUrl(myApp, "control");
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(controlName,controlStatus);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), String.valueOf(jsonObject));
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).execute();
    }

}
