package com.email.validator.smart_email_validator.entity;


import com.email.validator.smart_email_validator.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "email_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRecord {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "domain")
    private String domain;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "is_disposable")
    private Boolean isDisposable;

    @Column(name = "is_spam")
    private Boolean isSpam;

    @Column(name = "score")
    private Double score;

    @Column(name = "created_at")
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "email_status")
    private EmailStatus emailStatus;

    @OneToMany(
            mappedBy = "emailRecord",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ValidationDetail> validationDetails = new ArrayList<>();

}
