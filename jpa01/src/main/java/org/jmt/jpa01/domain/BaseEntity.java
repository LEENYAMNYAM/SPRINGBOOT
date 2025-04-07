package org.jmt.jpa01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value=AuditingEntityListener.class)
/* 이떤 이벤트를 실제로 실행하는 함수를 Listener라고 함 */
@Getter
abstract public class BaseEntity {
    @CreationTimestamp
    @Column(name= "regdate", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime regDate;
    @UpdateTimestamp
    @Column(name = "updatedate")
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime updateDate;

}
