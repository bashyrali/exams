SELECT products.product,SUM(qty) as total FROM history
	INNER JOIN
	products on history.product_id = products.id
	WHERE store_id = (SELECT id FROM stores
					 WHERE store LIKE 'Semey') 
					 AND (action_id = (SELECT id FROM actions WHERE action LIKE 'from store to contragent')  )
					 AND action_date BETWEEN '2021/01/01' AND '2021/09/25'
GROUP BY products.product
ORDER BY products.product;
