package com.yjxxt.comment.dto;

public class TreeDto {
    private Integer id;
    private String name;
    private String pId;

    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public TreeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pId='" + pId + '\'' +
                '}';
    }
}
