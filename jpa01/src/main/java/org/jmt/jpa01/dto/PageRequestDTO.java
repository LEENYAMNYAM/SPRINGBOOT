package org.jmt.jpa01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.net.URLEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page=1;
    @Builder.Default
    private int size=5;
    private String link;
    private String type;
    private String keyword;

    public String[] getTypes(){
        if(type==null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    public Pageable getPageable(String...props) {
        /* 파라미터 : String 형 배열이 됨. 몇개 올 지 모르므로 이렇게 씀 */
        return PageRequest.of(this.page-1, this.size, Sort.by(props).descending());
    }

    public String getLink() {
        if(link==null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if(type!=null && type.length()>0){
                builder.append("&type=" + type);
            }
            if(keyword!=null){
                builder.append("&keyword=" + URLEncoder.encode(keyword));
            }
            link = builder.toString();
        }
        return link;
    }

}
