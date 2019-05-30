package utils;

import android.app.Application;

public class MyApp extends Application {
    String Ip;
    String maxCo2;  //co2浓度 最大最小
    String minCo2;
    String maxLight;//灯光 最大最小
    String minLight;
    String maxSoilHumidity;  //土壤湿度 最大最小
    String minSoilHumidity;
    String maxAirHumidity;  //空气湿度  最大最小
    String minAirHumidity;
    String maxAirTemperature; //空气温度  最大最小
    String minAirTemperature;
    String maxSoilTemperature; //土壤温度 最大最小
    String minSoilTemperature;


    int controlAuto;       //控制开关
    int WaterPump;       //水泵控制器开关
    int Blower;       //风扇控制器开关
    int Roadlamp;       //灯光控制器开关
    int Buzzer;       //蜂鸣器控制开关



    public String getMaxCo2() {
        return maxCo2;
    }

    public int getControlAuto() {
        return controlAuto;
    }

    public int getWaterPump() {
        return WaterPump;
    }

    public void setWaterPump(int waterPump) {
        WaterPump = waterPump;
    }

    public int getBlower() {
        return Blower;
    }

    public void setBlower(int blower) {
        Blower = blower;
    }

    public int getRoadlamp() {
        return Roadlamp;
    }

    public void setRoadlamp(int roadlamp) {
        Roadlamp = roadlamp;
    }

    public int getBuzzer() {
        return Buzzer;
    }

    public void setBuzzer(int buzzer) {
        Buzzer = buzzer;
    }

    public void setControlAuto(int controlAuto) {
        this.controlAuto = controlAuto;
    }


    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public void setMaxCo2(String maxCo2) {
        this.maxCo2 = maxCo2;
    }

    public String getMinCo2() {
        return minCo2;
    }

    public void setMinCo2(String minCo2) {
        this.minCo2 = minCo2;
    }

    public String getMaxLight() {
        return maxLight;
    }

    public void setMaxLight(String maxLight) {
        this.maxLight = maxLight;
    }

    public String getMinLight() {
        return minLight;
    }

    public void setMinLight(String minLight) {
        this.minLight = minLight;
    }

    public String getMaxSoilHumidity() {
        return maxSoilHumidity;
    }

    public void setMaxSoilHumidity(String maxSoilHumidity) {
        this.maxSoilHumidity = maxSoilHumidity;
    }

    public String getMinSoilHumidity() {
        return minSoilHumidity;
    }

    public void setMinSoilHumidity(String minSoilHumidity) {
        this.minSoilHumidity = minSoilHumidity;
    }

    public String getMaxAirHumidity() {
        return maxAirHumidity;
    }

    public void setMaxAirHumidity(String maxAirHumidity) {
        this.maxAirHumidity = maxAirHumidity;
    }

    public String getMinAirHumidity() {
        return minAirHumidity;
    }

    public void setMinAirHumidity(String minAirHumidity) {
        this.minAirHumidity = minAirHumidity;
    }

    public String getMaxAirTemperature() {
        return maxAirTemperature;
    }

    public void setMaxAirTemperature(String maxAirTemperature) {
        this.maxAirTemperature = maxAirTemperature;
    }

    public String getMinAirTemperature() {
        return minAirTemperature;
    }

    public void setMinAirTemperature(String minAirTemperature) {
        this.minAirTemperature = minAirTemperature;
    }

    public String getMaxSoilTemperature() {
        return maxSoilTemperature;
    }

    public void setMaxSoilTemperature(String maxSoilTemperature) {
        this.maxSoilTemperature = maxSoilTemperature;
    }

    public String getMinSoilTemperature() {
        return minSoilTemperature;
    }

    public void setMinSoilTemperature(String minSoilTemperature) {
        this.minSoilTemperature = minSoilTemperature;
    }
}
