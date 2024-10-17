-- 조회수가 가장 높은 중고거래 게시물에 대한 첨부파일 경로를 조회
-- 첨부파일 경로는 FILE ID를 기준으로 내림차순
-- /home/grep/src/게시글 ID/파일 ID + 파일 이름 + 파일 확장자
SELECT 
    CONCAT('/home/grep/src/',ugf.BOARD_ID,'/',ugf.FILE_ID,ugf.FILE_NAME,ugf.FILE_EXT) FILE_PATH
FROM USED_GOODS_BOARD ugb
INNER JOIN USED_GOODS_FILE ugf
ON ugb.BOARD_ID = ugf.BOARD_ID
WHERE ugb.VIEWS = (SELECT MAX(VIEWS) FROM USED_GOODS_BOARD)
ORDER BY ugf.FILE_ID DESC;
