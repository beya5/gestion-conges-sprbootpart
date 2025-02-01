package com.wsconge.DTO;

import com.wsconge.entities.Departement;

import java.util.List;

public class ListeDepartementsDTO {
    private List<Long> departmentIds;

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }
}
