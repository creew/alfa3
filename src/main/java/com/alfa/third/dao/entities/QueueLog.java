package com.alfa.third.dao.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "QUEUE_LOG")
public class QueueLog {

    @Id
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date data;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_time_of_wait")
    private Date startWait;

    @Temporal(TemporalType.TIME)
    @Column(name = "end_time_of_wait")
    private Date endWait;

    @Temporal(TemporalType.TIME)
    @Column(name = "end_time_of_service")
    private Date endService;

    @ManyToOne
    @JoinColumn(name = "branches_id")
    private Office office;

    public Integer getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Date getStartWait() {
        return startWait;
    }

    public Date getEndWait() {
        return endWait;
    }

    public Date getEndService() {
        return endService;
    }

    public Office getOffice() {
        return office;
    }
}
