-- 자동차 종류가 '트럭'인 자동차의 대여 기록에 대해서 대여 기록 별로 대여 금액(컬럼명: FEE)을 구하여 대여 기록 ID와 대여 금액 리스트를 출력
# SELECT
#     cc.CAR_ID, cc.DAILY_FEE, ch.*
# FROM CAR_RENTAL_COMPANY_CAR cc
# INNER JOIN (
#     SELECT HISTORY_ID, CAR_ID, TIMESTAMPDIFF(DAY,START_DATE,END_DATE) FEE
#     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# ) ch
# ON cc.CAR_ID = ch.CAR_ID
# WHERE cc.CAR_TYPE = '트럭'

        # cc.daily_fee * ch.FEE '일수',
        # 1-MAX(cp.DISCOUNT_RATE)/100
        # IF(1-MAX(DISCOUNT_RATE)/100 IS NULL, 
        #    (daily_fee * FEE), 
        #    FLOOR((daily_fee * FEE)*(1-MAX(DISCOUNT_RATE)/100))) FEE

SELECT
    HISTORY_ID,
    IF(DISCOUNT_RATE IS NULL, 
       daily_fee * FEE, 
       FLOOR((daily_fee * FEE)*(1-(DISCOUNT_RATE/100)))) FEE
FROM (
    SELECT 
        ch.HISTORY_ID,
        cc.daily_fee,
        ch.FEE,
        MAX(cp.DISCOUNT_RATE) DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_CAR cc
    INNER JOIN (
        SELECT HISTORY_ID, CAR_ID, TIMESTAMPDIFF(DAY,START_DATE,END_DATE)+1 FEE
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    ) ch
    ON cc.CAR_ID = ch.CAR_ID
    LEFT JOIN (
        SELECT SUBSTRING_INDEX(DURATION_TYPE,'일',1) DAY,
                SUBSTRING_INDEX(DISCOUNT_RATE,'%',1) DISCOUNT_RATE
        FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
        WHERE CAR_TYPE = '트럭'
    ) cp
    ON (CASE 
            WHEN ch.FEE BETWEEN 7 AND 30 THEN 7
            WHEN ch.FEE BETWEEN 30 AND 90 THEN 30
            WHEN ch.FEE >= 90 THEN 90
        END) = cp.DAY
    WHERE cc.CAR_TYPE = '트럭'
    GROUP BY ch.HISTORY_ID, ch.FEE, cc.DAILY_FEE
) SUB
ORDER BY FEE DESC, HISTORY_ID DESC;

