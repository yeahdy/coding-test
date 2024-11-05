-- 2번 형질이 보유하지 않으면서 1번이나 3번 형질을 보유하고 있는 대장균 개체의 수(COUNT)를 출력
-- 10진수:2진수 > 2:10 3:11 4:100 5:101
SELECT
    COUNT(ID) COUNT
    # ID, GENOTYPE, CONV(GENOTYPE,10,2), CONV(5,10,2), GENOTYPE & 5
FROM ECOLI_DATA
WHERE (GENOTYPE & 2 != 2) AND GENOTYPE & 5 > 0

