-- 우유와 요거트를 동시에 구입한 장바구니의 아이디를 조회
-- 장바구니의 아이디 순
SELECT cp.CART_ID
FROM CART_PRODUCTS cp
INNER JOIN CART_PRODUCTS sub
ON cp.CART_ID = sub.CART_ID
WHERE cp.NAME = 'Milk' AND sub.NAME = 'Yogurt'
GROUP BY cp.CART_ID, sub.CART_ID
ORDER BY cp.CART_ID;