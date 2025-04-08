package org.jmt.jpa01.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;       //현재페이지
    private int size =3;    //블록크기
    private int total;      //전체 레코드 수
    private int start;      //시작페이지 번호
    private int end;        //끝페이지 번호
    private boolean prev;   //이전페이지 존재 여부
    private boolean next;   //다음페이지 존재 여부
    private List<E> dtoList; //페이징할 dto 리스트

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        if(total <=0){
            return;
        }
        this.page = pageRequestDTO.getPage();
        this.total = total;
        this.dtoList = dtoList;
        this.end=(int)(Math.ceil(this.page/(double)size)*size);
        this.start=this.end-this.size+1;
        int last = (int)(Math.ceil(total/(double)pageRequestDTO.getSize()));   // 총 페이지 수
        this.end = end>last?last:end;
        this.prev = this.start>1;
        this.next = total > (this.end * pageRequestDTO.getSize());
    }


}
