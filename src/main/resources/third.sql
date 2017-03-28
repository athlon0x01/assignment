-- We do have 2 tables in a relational database:
-- • Clients (client_id(PK), client_name)
-- • Orders (order_id(PK), client_id, order_sum, order_date)
-- Please write the following SQL queries:
--   a- list of clients, which have an order with order_sum > 50
--   b- clients, whose total sum of orders exceeds 100

SELECT client_id, client_name
FROM Clients
WHERE EXISTS (SELECT order_id FROM Orders WHERE client_id = Clients.client_id AND order_sum > 50);


SELECT c.client_id, c.client_name, SUM(o.order_sum)
FROM Orders o JOIN Clients c ON o.client_id = c.client_id
GROUP BY c.client_id
HAVING SUM(o.order_sum) > 100
ORDER BY SUM(o.order_sum);