-- 2022년 10월 16일에 대여 중인 자동차인 경우 '대여중', 대여 중이지 않은 경우 '대여 가능'을 표시
-- 반납 날짜가 2022년 10월 16일인 경우에도 '대여중'으로 표시
-- 자동차 ID를 기준으로 내림차순 정렬

-- 조건1. 반납 날짜가 2022년 10월 16일이거나 이후 경우의 데이터만 조회
-- 조건2. 시작 날짜가 2022년 10월 16일 이전이면 대여중
SELECT 
    CAR_ID,
    CASE
        WHEN 
            DATE_FORMAT(START_DATE,'%Y-%m-%d') <= '2022-10-16' 
        THEN '대여중'  
        ELSE '대여 가능'        
    END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE DATE_FORMAT(END_DATE,'%Y-%m-%d') >= '2022-10-16'
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;
