SELECT contragents.contragent as agent,SUM(qty)as total FROM history
	INNER JOIN
	contragents ON history.contragent_id = contragents.id 
	WHERE product_id = (SELECT id FROM products WHERE product LIKE 'apple')
	AND action_id = (SELECT id FROM actions WHERE action LIKE 'from contragent to store')
	AND action_date BETWEEN '2021/01/01' AND '2021/09/25'
	GROUP BY agent
	ORDER BY total 