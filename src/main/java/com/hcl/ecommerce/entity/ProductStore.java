
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
@Table(name = "product_store")
@Getter
@Setter
@NoArgsConstructor
public class ProductStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productStoreId;
	private float productDiscount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "store_Id", nullable = false)
	private Store storeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_Id", nullable = false)
	private Product productId;

}
