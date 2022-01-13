package com.yjxxt.comment.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Score {
    private Integer id;

    private Integer subId;

    private Integer userId;

    private Double manner;

    private Double atmosphere;

    private Double quality;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateDate;

    private Integer isValid;

    private Integer teacherId;

    private String userName;

    private String subName;

    private double avgManner;

    private double avgAtmosphere;

    private double avgQuality;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getManner() {
        return manner;
    }

    public void setManner(Double manner) {
        this.manner = manner;
    }

    public Double getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Double atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public double getAvgManner() {
        return avgManner;
    }

    public void setAvgManner(double avgManner) {
        this.avgManner = avgManner;
    }

    public double getAvgAtmosphere() {
        return avgAtmosphere;
    }

    public void setAvgAtmosphere(double avgAtmosphere) {
        this.avgAtmosphere = avgAtmosphere;
    }

    public double getAvgQuality() {
        return avgQuality;
    }

    public void setAvgQuality(double avgQuality) {
        this.avgQuality = avgQuality;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", subId=" + subId +
                ", userId=" + userId +
                ", manner=" + manner +
                ", atmosphere=" + atmosphere +
                ", quality=" + quality +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isValid=" + isValid +
                ", teacherId=" + teacherId +
                ", userName='" + userName + '\'' +
                ", subName='" + subName + '\'' +
                ", avgManner=" + avgManner +
                ", avgAtmosphere=" + avgAtmosphere +
                ", avgQuality=" + avgQuality +
                '}';
    }
}