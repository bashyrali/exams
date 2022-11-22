SELECT products.product, SUM(qty) FROM history
	INNER JOIN 
	products ON history.product_id = products.id
	WHERE contragent_id =(SELECT id FROM contragents WHERE contragent LIKE 'Kair')
	AND store_id = (SELECT id FROM stores WHERE store LIKE 'Almaty')
	AND action_id = (SELECT id FROM actions WHERE action LIKE 'from store to contragent')
	AND action_date BETWEEN '2021/01/01' AND '2021/09/25'
GROUP BY products.product
ORDER BY products.product;