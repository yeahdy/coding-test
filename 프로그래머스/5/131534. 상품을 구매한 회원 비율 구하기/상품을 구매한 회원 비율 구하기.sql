-- 2021년에 가입한 전체 회원들 중 상품을 구매한 회원수, 상품을 구매한 회원의 비율
-- 상품을 구매한 회원의 비율은 소수점 두번째자리에서 반올림
-- 전체 결과는 년,월 기준으로 오름차순 정렬

-- 2021년에 가입한 전체 회원들 중 상품을 구매한 회원수
WITH PURCHASED_DATA AS (
    SELECT
        YEAR(os.SALES_DATE) YEAR,
        MONTH(os.SALES_DATE) MONTH,
        COUNT(DISTINCT os.USER_ID) PURCHASED_USERS	
    FROM USER_INFO ui
    JOIN ONLINE_SALE os
    ON ui.USER_ID = os.USER_ID
    WHERE YEAR(ui.JOINED) = '2021' AND os.SALES_AMOUNT > 0
    GROUP BY YEAR, MONTH
),
TOTAL_USER AS (
    SELECT USER_ID
    FROM USER_INFO 
    WHERE YEAR(JOINED) = '2021'
    GROUP BY USER_ID
)
SELECT
    pd.YEAR,
    pd.MONTH,
    pd.PURCHASED_USERS,
    ROUND(
        pd.PURCHASED_USERS / (SELECT COUNT(USER_ID) FROM TOTAL_USER)
        ,1) PUCHASED_RATIO
FROM PURCHASED_DATA pd;

# SELECT COUNT(USER_ID)
# FROM (
#     SELECT USER_ID
#     FROM USER_INFO 
#     WHERE YEAR(JOINED) = '2021'
#     GROUP BY USER_ID
# )sub;