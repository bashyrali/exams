SELECT categories.category, sum(qty) FROM history
	INNER JOIN
	products on history.product_id = products.id
	INNER JOIN 
	categories on products.category_id = categories.id
	WHERE store_id = (SELECT id FROM stores WHERE store LIKE 'Almaty')
	AND action_date BETWEEN '2021/01/01' AND '2021/09/25'
GROUP BY categories.category
ORDER BY categories.category;