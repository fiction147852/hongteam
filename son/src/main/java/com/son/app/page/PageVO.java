package com.son.app.page;

import lombok.Data;

@Data
public class PageVO {
    private int page; // 현재 페이지
    private int startPage, endPage, realEnd; // 페이지 그룹의 시작, 끝, 실제 끝 페이지
    private boolean prev, next; // 이전, 다음 페이지 존재 여부
    private int pageSize = 5; // 한 페이지에 표시할 항목 수
    private int pageGroupSize = 5; // 페이지 그룹 크기

    public PageVO(int page, int totalItems, int pageSize, int pageGroupSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.pageGroupSize = pageGroupSize;
        // 총 페이지 수 계산
        this.realEnd = (int) Math.ceil(totalItems / (double) pageSize);
        // 페이지 그룹의 끝 페이지 계산
        this.endPage = (int) Math.ceil(page / (double) pageGroupSize) * pageGroupSize;
        // 페이지 그룹의 시작 페이지 계산
        this.startPage = this.endPage - (pageGroupSize - 1);
        // 실제 끝 페이지보다 큰 경우 조정
        if (this.endPage > realEnd) {
            this.endPage = realEnd;
        }
        // 이전 페이지 그룹 존재 여부
        this.prev = this.startPage > 1;
        // 다음 페이지 그룹 존재 여부
        this.next = this.endPage < realEnd;
    }
}