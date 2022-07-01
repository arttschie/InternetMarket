package com.epam.internetMarket.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Order implements Serializable {
    private long id;
    private long userId;
    private long statusId;
    private BigDecimal totalCost;
    private Date dateStart;
    private Date dateFinish;

    public Order() {
    }

    public Order(long userId, long statusId, BigDecimal totalCost, Date dateStart, Date dateFinish) {
        this.userId = userId;
        this.statusId = statusId;
        this.totalCost = totalCost;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public Order(long id, long userId, long statusId, BigDecimal totalCost, Date dateStart, Date dateFinish) {
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.totalCost = totalCost;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", statusId=" + statusId +
                ", totalCost=" + totalCost +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateFinish +
                '}';
    }
}
