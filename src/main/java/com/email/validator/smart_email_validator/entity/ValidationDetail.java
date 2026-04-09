package com.email.validator.smart_email_validator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "validation_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationDetail {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "check_name")
    private String checkName;

    @Column(name = "is_passed")
    private Boolean isPassed;

    @Column(name = "message")
    private String message;

    @Column(name = "impact_score")
    private Double impactScore;

    @Column(name = "checked_at")
    private Instant checkedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_record_id", nullable = false)
    private EmailRecord emailRecord;

    @Override
    public String toString() {
        return "ValidationDetail{" +
                "id=" + id +
                ", checkName='" + checkName + '\'' +
                ", isPassed=" + isPassed +
                ", message='" + message + '\'' +
                ", impactScore=" + impactScore +
                ", checkedAt=" + checkedAt +
                '}';
    }

}