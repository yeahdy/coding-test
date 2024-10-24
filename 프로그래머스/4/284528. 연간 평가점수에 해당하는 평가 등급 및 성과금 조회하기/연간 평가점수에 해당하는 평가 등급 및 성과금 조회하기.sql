-- 평가 점수별 등급과 등급에 따른 사번, 성명, 평가 등급, 성과금을 조회
-- 평가등급의 컬럼명은 GRADE로, 성과금의 컬럼명은 BONUS
-- 사번 기준으로 오름차순 정렬
SELECT
    hem.EMP_NO,
    hem.EMP_NAME,
    CASE 
        WHEN sub.average >= 96 THEN 'S'
        WHEN sub.average >= 90 THEN 'A'
        WHEN sub.average >= 80 THEN 'B'
        ELSE 'C'
    END AS GRADE,
    CASE 
        WHEN sub.average >= 96 THEN hem.SAL*0.2
        WHEN sub.average >= 90 THEN hem.SAL*0.15
        WHEN sub.average >= 80 THEN hem.SAL*0.1
        ELSE 0
    END AS BONUS
FROM HR_EMPLOYEES hem
INNER JOIN (
    SELECT 
        hg.EMP_NO,
        SUM(hg.SCORE)/2 AS average
    FROM HR_GRADE hg
    INNER JOIN HR_EMPLOYEES he
    ON hg.EMP_NO = he.EMP_NO
    GROUP BY hg.EMP_NO
) sub
ON hem.EMP_NO = sub.EMP_NO
