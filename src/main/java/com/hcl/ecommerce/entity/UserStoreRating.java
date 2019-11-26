
package com.hcl.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_store_rating")
@Getter
@Setter
@NoArgsConstructor
public class UserStoreRating {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userStoreRatingId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "store_Id", nullable = false)
	private Store storeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_Id", nullable = false)
	private User userId;

	private float rating;
}
