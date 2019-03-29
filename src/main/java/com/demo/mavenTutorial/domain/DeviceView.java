package com.demo.mavenTutorial.domain;/**
 * @author shaoy
 * @date 2019/3/29 14:29
 */

/**
 * @ClassName DeviceView
 * @Description TODO
 * @Version 1.0
 **/
public class DeviceView {
    private String deviceName;
    private String reportedName;
    private int reportedValue;
    private String desiredName;
    private int desiredValue;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getReportedName() {
        return reportedName;
    }

    public void setReportedName(String reportedName) {
        this.reportedName = reportedName;
    }

    public int getReportedValue() {
        return reportedValue;
    }

    public void setReportedValue(int reportedValue) {
        this.reportedValue = reportedValue;
    }

    public String getDesiredName() {
        return desiredName;
    }

    public void setDesiredName(String desiredName) {
        this.desiredName = desiredName;
    }

    public int getDesiredValue() {
        return desiredValue;
    }

    public void setDesiredValue(int desiredValue) {
        this.desiredValue = desiredValue;
    }
}
