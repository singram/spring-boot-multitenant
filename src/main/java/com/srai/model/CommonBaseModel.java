package com.srai.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/** Abstract base model including primary id and
 * created_at and updated_at time fields.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonBaseModel {
  /** Primary id. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /** Model created at timestamp. */
  @CreatedDate
  private Date createdAt;

  /** Model updated at timestamp. */
  @LastModifiedDate
  private Date updatedAt;

  /** Constructor. */
  protected CommonBaseModel() {
    // Private constructor to prevent direct instantiation.
  }

  /** Primary id getter. */
  public Long getId() {
    return id;
  }

  /** Primary id setter. */
  public void setId(final Long id) {
    this.id = id;
  }

  /** Created at getter. */
  public Date getCreatedAt() {
    return (Date) createdAt.clone();
  }

  /** Created at setter. */
  public void setCreatedAt(final Date createdAt) {
    this.createdAt = (Date) createdAt.clone();
  }

  /** Updated at getter. */
  public Date getUpdatedAt() {
    return (Date) updatedAt.clone();
  }

  /** Updated at setter. */
  public void setUpdatedAt(final Date updatedAt) {
    this.updatedAt = (Date) updatedAt.clone();
  }

}
