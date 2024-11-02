-- 중고 거래 게시물을 3건 이상 등록한 사용자의 사용자 ID, 닉네임, 전체주소, 전화번호를 조회
-- 전체 주소는 시, 도로명 주소, 상세 주소
-- 전화번호  xxx-xxxx-xxxx 형태
SELECT 
    ugu.USER_ID, 
    ugu.NICKNAME, 
    CONCAT(ugu.CITY," ",ugu.STREET_ADDRESS1," ",ugu.STREET_ADDRESS2) '전체주소',
    CONCAT(LEFT(ugu.TLNO, 3), '-', MID(ugu.TLNO, 4, 4), '-', RIGHT(ugu.TLNO, 4)) '전화번호'
FROM USED_GOODS_USER ugu
-- WHERE USER_ID IN 
INNER JOIN
(
    SELECT WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING COUNT(WRITER_ID) > 2
) sub
ON ugu.USER_ID = sub.WRITER_ID
ORDER BY USER_ID DESC;