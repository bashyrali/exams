SELECT brands.brand, sum(qty) FROM history
	INNER JOIN
	products on history.product_id = products.id
	INNER JOIN 
	brands on products.brand_id = brands.id
	WHERE store_id = (SELECT id FROM stores WHERE store LIKE 'Almaty')
	AND action_date BETWEEN '2021/01/01' AND '2021/09/25'
GROUP BY brands.brand
ORDER BY brands.brand;